package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashscreen01 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();*/
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashscreen01.this, Loginscreen01Activity.class); //화면 전환
                startActivity(intent);
                finish();
            }
        }, 2000); //딜레이 타임 조절
    }
}