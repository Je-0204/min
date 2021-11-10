package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class signupscreen02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupscreen02);
    }

    public void next(View view){
        //signupscreen03으로 넘어가는 intent 코드
        Intent intent=new Intent();
        ComponentName componentName=new ComponentName("com.example.min","com.example.min.signupscreen03");
        intent.setComponent(componentName);
        startActivity(intent);
    }
    public void kakao(View view){
        Toast.makeText(this.getApplicationContext(), "kakao으로 회원가입", Toast.LENGTH_SHORT).show();
        //goto kakao login screen activity
    }
    public void naver(View view){
        Toast.makeText(this.getApplicationContext(), "naver으로 회원가입", Toast.LENGTH_SHORT).show();
        //goto naver login screen activity
    }
    public void google(View view){
        Toast.makeText(this.getApplicationContext(), "google으로 회원가입", Toast.LENGTH_SHORT).show();
        //goto google login screen activity
    }
}