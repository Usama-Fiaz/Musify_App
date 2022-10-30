package com.ass2.f190260_i190468.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ass2.f190260_i190468.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        b10 = findViewById(R.id.b10);
        b11 = findViewById(R.id.b11);
        b12 = findViewById(R.id.b12);
        b13 = findViewById(R.id.b13);
        b14 = findViewById(R.id.b14);
        b15 = findViewById(R.id.b15);
        b16 = findViewById(R.id.b16);
        b17 = findViewById(R.id.b17);
        b18 = findViewById(R.id.b18);
        b19 = findViewById(R.id.b19);
        b20 = findViewById(R.id.b20);
        b21 = findViewById(R.id.b21);
        b22 = findViewById(R.id.b22);
        b23 = findViewById(R.id.b23);
        b24 = findViewById(R.id.b24);

        mAuth.signOut();
        b1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SS1_Musify_Logo_Page.class);
            startActivity(intent);
        });


        b2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SS2_CreateAccount_Page.class);
            startActivity(intent);
        });

        b3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS3_SignIn_Page.class);
            startActivity(intent);
        });
        b4.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SS4_SignUp_Page.class);
            startActivity(intent);
        });

        b5.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS5_playlist_Page.class);
            startActivity(intent);
        });

        b6.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS6_Playlist_Page2.class);
            startActivity(intent);
        });
        b7.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SS7_SongTitle_Page.class);
            startActivity(intent);
        });


        b8.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS8_Sidebar_Page.class);
            startActivity(intent);
        });

        b9.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS9_AddPlaylist_Page.class);
            startActivity(intent);
        });
        b10.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SS10_UploadMusic_Page.class);
            startActivity(intent);
        });

        b11.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SS11_Record_Page1.class);
            startActivity(intent);
        });

        b12.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS12_Record_Page2.class);
            startActivity(intent);
        });

        b13.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS13_Record_Page3.class);
            startActivity(intent);
        });
        b14.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SS14_Record_Page4.class);
            startActivity(intent);
        });


        b15.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS15_SelectPlaylist_Page.class);
            startActivity(intent);
        });

        b16.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS16_SongPlay_Page.class);
            startActivity(intent);
        });
        b17.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SS17_Song_Play_Page2.class);
            startActivity(intent);
        });


        b18.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS18_RecentSearch_Page.class);
            startActivity(intent);
        });

        b19.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS19_ListenLater_Page.class);
            startActivity(intent);
        });
        b20.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SS20_Liked_Page.class);
            startActivity(intent);
        });

        b21.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SS21_Liked_Page.class);
            startActivity(intent);
        });

        b22.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS22_Profile_Page.class);
            startActivity(intent);
        });

        b23.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,SS23_EditProfile_Page.class);
            startActivity(intent);
        });
        b24.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,UsersActivity.class);
            startActivity(intent);
        });

    }
}