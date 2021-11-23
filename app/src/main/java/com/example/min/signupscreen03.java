package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class signupscreen03 extends AppCompatActivity {

    private TextView tv_result;
    private ImageView iv_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupscreen03);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String photoUrl = intent.getStringExtra("photoUrl");

        tv_result = findViewById(R.id.tv_result);
        tv_result.setText(nickName);

        iv_profile = findViewById(R.id.iv_profile);
        Glide.with(this).load(photoUrl).into(iv_profile);
    }

    public void finish(View view){
        Intent intent=new Intent();
        ComponentName componentName=new ComponentName("com.example.min","com.example.min.Loginscreen01Activity");
        intent.setComponent(componentName);
        startActivity(intent);
    }
}