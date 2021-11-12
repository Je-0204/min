package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class signupscreen03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupscreen03);
    }
    public void finish(View view){
        Intent intent=new Intent();
        ComponentName componentName=new ComponentName("com.example.min","com.example.min.Loginscreen01Activity");
        intent.setComponent(componentName);
        startActivity(intent);
    }
}