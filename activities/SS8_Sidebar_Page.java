package com.ass2.f190260_i190468.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ass2.f190260_i190468.R;
import com.google.firebase.auth.FirebaseAuth;

public class SS8_Sidebar_Page extends AppCompatActivity {
    TextView username;
    LinearLayout logout;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss8_sidebar_page);

        username = findViewById(R.id.username);
        logout = findViewById(R.id.logout);

        if (mAuth.getCurrentUser() != null)
            username.setText(mAuth.getCurrentUser().getEmail());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(SS8_Sidebar_Page.this, "Signed Out", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SS8_Sidebar_Page.this, SS3_SignIn_Page.class));
            }
        });
    }
}