package com.ass2.f190260_i190468.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ass2.f190260_i190468.MemoryData;
import com.ass2.f190260_i190468.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat extends AppCompatActivity {

    CircleImageView profilePic;
    TextView name,status;
    RelativeLayout voice_call,video_call,send_msg;
    RecyclerView usersRecyclerView;
    ImageView backButton,camera;
    EditText type_msg;
    private String chatKey;
    String getUserMobile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toast.makeText(this, "Im in Chat Activity 1", Toast.LENGTH_LONG).show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReferenceFromUrl("https://f190260-i190468-5d10c-default-rtdb.firebaseio.com/");

        profilePic = findViewById(R.id.profilePic);
        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
        voice_call = findViewById(R.id.voice_call);
        video_call = findViewById(R.id.video_call);
        usersRecyclerView = findViewById(R.id.usersRecyclerView);
        camera = findViewById(R.id.camera);
        type_msg = findViewById(R.id.type_msg);
        send_msg = findViewById(R.id.send_msg);

        final String getName = getIntent().getStringExtra("name");
        final String getProfilePic = getIntent().getStringExtra("profile_pic");
        final String getMobile = getIntent().getStringExtra("mobile");
        chatKey = getIntent().getStringExtra("chat_key");

        Toast.makeText(this, "Im in Chat Activity 2", Toast.LENGTH_LONG).show();
        getUserMobile = MemoryData.getPhoneNumber(chat.this);
        name.setText(getName);
        Picasso.get().load(getProfilePic).into(profilePic);
        Toast.makeText(this, "Im in Chat Activity 3", Toast.LENGTH_LONG).show();

        if(chatKey.isEmpty()){
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    chatKey = "1";
                    if(snapshot.hasChild("chat")){
                        chatKey = String.valueOf(snapshot.child("chat").getChildrenCount()+1);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getTxtMsg = type_msg.getText().toString();
                final String currentTimeStamp = String.valueOf(System.currentTimeMillis()).substring(0,10);

                MemoryData.saveLastMsgTs(currentTimeStamp,chatKey,chat.this);
                myRef.child("chat").child(chatKey).child("user_1").setValue(getUserMobile);
                myRef.child("chat").child(chatKey).child("user_2").setValue(getMobile);
                myRef.child("chat").child(chatKey).child("messages").child(currentTimeStamp).child("msg").setValue(getTxtMsg);
                myRef.child("chat").child(chatKey).child("messages").child(currentTimeStamp).child("mobile").setValue(getUserMobile);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}