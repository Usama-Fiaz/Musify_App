package com.ass2.f190260_i190468.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ass2.f190260_i190468.R;
import com.google.firebase.auth.FirebaseAuth;

public class SS22_Profile_Page extends AppCompatActivity {
    TextView name,email;
//    Button signup,signin;
//    TextView forget;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    String Email, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss22_profile_page);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

//        name.setText(mAuth.getCurrentUser().getPhoneNumber());
//        email.setText(mAuth.getCurrentUser().getEmail());

    }
}