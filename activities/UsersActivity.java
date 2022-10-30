package com.ass2.f190260_i190468.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ass2.f190260_i190468.MemoryData;
import com.ass2.f190260_i190468.R;
import com.ass2.f190260_i190468.messages.MessagesAdapter;
import com.ass2.f190260_i190468.messages.MessagesList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersActivity extends AppCompatActivity {
    private List<MessagesList> messagesLists = new ArrayList<>();
    String phone, name, email, gender, pass, img_url;
    RecyclerView usersRecyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReferenceFromUrl("https://f190260-i190468-5d10c-default-rtdb.firebaseio.com/");
    String profilePicUrl;
    TextView side_bar_username, side_bar_edit_prof, side_bar_change_pass;
    CircleImageView userProfilePic, side_bar_dp;
    LinearLayout logout;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String lastMessage = "";
    private String chatKey = "";
    private boolean dataSet = false;
    private int unSeenMessages = 0;
    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        userProfilePic = findViewById(R.id.userProfilePic);
        usersRecyclerView = findViewById(R.id.usersRecyclerView);

        side_bar_username = findViewById(R.id.side_bar_username);
        side_bar_edit_prof = findViewById(R.id.side_bar_edit_prof);
        side_bar_change_pass = findViewById(R.id.side_bar_change_pass);
        side_bar_dp = findViewById(R.id.side_bar_dp);
        logout = findViewById(R.id.logout);

        if (!MemoryData.getName(UsersActivity.this).isEmpty()) {
            phone = MemoryData.getPhoneNumber(UsersActivity.this);
            name = MemoryData.getName(UsersActivity.this);
            email = MemoryData.getEmail(UsersActivity.this);
            gender = MemoryData.getGender(UsersActivity.this);
            pass = MemoryData.getPassword(UsersActivity.this);
            img_url = MemoryData.getPicUrl(UsersActivity.this);
        }

        Toast.makeText(this, "UserActivity : " + "\n" + phone + "\n" + name + "\n" + email, Toast.LENGTH_LONG).show();

//        Toast.makeText(this, "[][][]:"+mAuth.getCurrentUser().getEmail().toString(), Toast.LENGTH_LONG).show();

        usersRecyclerView.setHasFixedSize(true);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        messagesAdapter = new MessagesAdapter(messagesLists, UsersActivity.this);
        Toast.makeText(this, "Test1:"+messagesLists, Toast.LENGTH_LONG).show();

        LinearLayoutManager llm = new LinearLayoutManager(UsersActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        usersRecyclerView.setLayoutManager(llm);

        usersRecyclerView.setAdapter(messagesAdapter);
        Toast.makeText(this, "Test2:"+messagesLists, Toast.LENGTH_LONG).show();
        //progressDialog.dismiss();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String profilePicUrl = snapshot.child("Users").child(phone).child("profilePic").getValue(String.class);
                if(!profilePicUrl.isEmpty()){
                    Picasso.get().load(profilePicUrl).into(userProfilePic);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesLists.clear();
                unSeenMessages = 0;
                lastMessage = "";
                chatKey = "";
                String set_user_name;

                if (!MemoryData.getName(UsersActivity.this).isEmpty()) {
                    Picasso.get().load(img_url).into(userProfilePic);
                    Picasso.get().load(img_url).into(side_bar_dp);
                    side_bar_username.setText(name);
                }

//                else {
//                    set_user_name = snapshot.child(phone).child("name").getValue(String.class);
//                    side_bar_username.setText(set_user_name);
//
//                    profilePicUrl = snapshot.child(phone).child("profilePic").getValue(String.class);
//                    if (profilePicUrl != null) {
//                        Picasso.get().load(profilePicUrl).into(userProfilePic);
//                        Picasso.get().load(profilePicUrl).into(side_bar_dp);
//                    }
//                }

                for (DataSnapshot dataSnapshot : snapshot.child("Users").getChildren()) {
                    final String getMobile = dataSnapshot.getKey();
                    dataSet = false;
                    if (!getMobile.equals(phone)) {
                        Toast.makeText(UsersActivity.this, "Mobile : " + getMobile, Toast.LENGTH_SHORT).show();
                        final String getName = dataSnapshot.child("name").getValue(String.class);
                        final String getProfilePic = dataSnapshot.child("profilePic").getValue(String.class);
                        final String getNum = dataSnapshot.child("phone_Number").getValue(String.class);

                        myRef.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int getChatCounts = (int) snapshot.getChildrenCount();
                                Toast.makeText(UsersActivity.this, "Lol"+getChatCounts, Toast.LENGTH_LONG).show();
                                if (getChatCounts > 0) {
                                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                        final String getKey = dataSnapshot1.getKey();
                                        chatKey = getKey;
                                        if (dataSnapshot1.hasChild("user_1") && dataSnapshot1.hasChild("user_2") && dataSnapshot1.hasChild("messages")) {
                                            final String getUserOne = dataSnapshot1.child("user_1").getValue(String.class);
                                            final String getUserTwo = dataSnapshot1.child("user_2").getValue(String.class);

                                            if ((getUserOne.equals(getMobile) && getUserTwo.equals(phone) || getUserOne.equals(phone) && getUserTwo.equals(getMobile))) {
                                                for (DataSnapshot chatDataSnapshot : dataSnapshot1.child("messages").getChildren()) {
                                                    final long getMessagesKey = Long.parseLong(chatDataSnapshot.getKey());
                                                    final long getLastSeenMessage = Long.parseLong(MemoryData.getLastMsgTs(UsersActivity.this, getKey));
                                                    lastMessage = chatDataSnapshot.child("msg").getValue(String.class);
                                                    if (getMessagesKey > getLastSeenMessage) {
                                                        unSeenMessages++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    Toast.makeText(UsersActivity.this, "Test3:"+messagesLists, Toast.LENGTH_LONG).show();
                                }

//                                if (!dataSet) {
                                    dataSet = true;
                                    Toast.makeText(UsersActivity.this, "Im in DataSet", Toast.LENGTH_LONG).show();
                                    MessagesList messagesList = new MessagesList(getName, getNum, lastMessage, getProfilePic, unSeenMessages, chatKey);
                                    messagesLists.add(messagesList);
                                    messagesAdapter.updateData(messagesLists);
                                    Toast.makeText(UsersActivity.this, "Test4:"+messagesLists, Toast.LENGTH_LONG).show();
//                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        side_bar_edit_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UsersActivity.this, SS23_EditProfile_Page.class));
            }
        });
        side_bar_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UsersActivity.this, SS23_EditProfile_Page.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryData.saveName(null, UsersActivity.this);
                MemoryData.saveEmail(null, UsersActivity.this);
                MemoryData.savePhoneNumber(null, UsersActivity.this);
                MemoryData.savePassword(null, UsersActivity.this);
                MemoryData.saveGender(null, UsersActivity.this);
                MemoryData.savePicUrl(null, UsersActivity.this);

                mAuth.signOut();
                startActivity(new Intent(UsersActivity.this, SS3_SignIn_Page.class));
            }
        });
    }
}