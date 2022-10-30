package com.ass2.f190260_i190468.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ass2.f190260_i190468.R;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class SS4_SignUp_Page extends AppCompatActivity {
Button create_account,signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss4_sign_up_page);
        create_account=findViewById(R.id.create_account);
        signin=findViewById(R.id.signin);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SS4_SignUp_Page.this, SS2_CreateAccount_Page.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SS4_SignUp_Page.this, SS3_SignIn_Page.class);
                startActivity(intent);
            }
        });
    }
}