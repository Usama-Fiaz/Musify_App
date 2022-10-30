package com.ass2.f190260_i190468.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ass2.f190260_i190468.MemoryData;
import com.ass2.f190260_i190468.R;
import com.ass2.f190260_i190468.messages.MessagesList;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SS3_SignIn_Page extends AppCompatActivity {
    EditText email, pass;
    Button signup, signin;
    TextView  forget;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss3_sign_in_page);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);
        forget = findViewById(R.id.forget);

        if (!MemoryData.getName(this).isEmpty()) {
            startActivity(new Intent(SS3_SignIn_Page.this, UsersActivity.class));
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SS3_SignIn_Page.this, SS4_SignUp_Page.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SS3_SignIn_Page.this, SS4_SignUp_Page.class);
                startActivity(intent);
            }
        });
        email.setText("Usamafiaz012@gmail.com");
        pass.setText("123456789");
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = email.getText().toString();
                Password = pass.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Users");
                mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                String ph = mAuth.getCurrentUser().getPhoneNumber();
                                Toast.makeText(SS3_SignIn_Page.this, "User : "+ph, Toast.LENGTH_LONG).show();
                                myRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                            final String getMobile = dataSnapshot.getKey();
                                            if (Email.equals(dataSnapshot.child("email").getValue(String.class))) {

                                                final String Current_User_Name = dataSnapshot.child("name").getValue(String.class);
                                                final String Current_User_PFP = dataSnapshot.child("profilePic").getValue(String.class);
                                                final String Current_User_PhNum = dataSnapshot.child("phone_Number").getValue(String.class);
                                                final String Current_User_Gender = dataSnapshot.child("gender").getValue(String.class);
                                                final String Current_User_Password = dataSnapshot.child("password").getValue(String.class);
                                                Toast.makeText(SS3_SignIn_Page.this, "Logged in successfully:"+mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

                                                MemoryData.saveName(Current_User_Name, SS3_SignIn_Page.this);
                                                MemoryData.saveEmail(Email, SS3_SignIn_Page.this);
                                                MemoryData.savePhoneNumber(Current_User_PhNum, SS3_SignIn_Page.this);
                                                MemoryData.savePassword(Current_User_Password, SS3_SignIn_Page.this);
                                                MemoryData.saveGender(Current_User_Gender, SS3_SignIn_Page.this);
                                                MemoryData.savePicUrl(Current_User_PFP, SS3_SignIn_Page.this);


                                                startActivity(new Intent(SS3_SignIn_Page.this, UsersActivity.class));

                                                break;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SS3_SignIn_Page.this,"Email or Password is Incorrect.",Toast.LENGTH_LONG).show();
                            }
                        });
//                myRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.hasChild(Email)) {
//                            String Get_Pass = snapshot.child(Email).child("password").getValue(String.class);
//                            if (Get_Pass.equals(Password)) {
//                                Toast.makeText(SS3_SignIn_Page.this, "Okkkkk", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Toast.makeText(SS3_SignIn_Page.this, "Failed", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else{
//                            Toast.makeText(SS3_SignIn_Page.this, "Wrong Pass", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
        });
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mAuth.getCurrentUser() != null) {
//            startActivity(new Intent(SS3_SignIn_Page.this, SS8_Sidebar_Page.class));
//        }
//    }
}