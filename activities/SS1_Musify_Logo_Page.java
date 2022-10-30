package com.ass2.f190260_i190468.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.ass2.f190260_i190468.R;

public class SS1_Musify_Logo_Page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss1_musify_logo_page);



        new CountDownTimer(2000, 1000) {
            public void onFinish() {
                Intent startActivity = new Intent(SS1_Musify_Logo_Page.this,SS3_SignIn_Page.class);
                startActivity(startActivity);

                finish();
            }

            public void onTick(long millisUntilFinished) {
            }

        }.start();
    }
}