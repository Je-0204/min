package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Loginscreen01Activity extends AppCompatActivity {

    int visitDay=0;
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen01);

        TextView signup=findViewById(R.id.text_signup); //회원가입 textview 밑줄 긋기
        signup.setPaintFlags(signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
    public void login(View view){
        if(username.getText().toString().equals("test") && password.getText().toString().equals("test")){
            //correct password -> goto main screen
            visitDay++;

            Intent intent=new Intent();
            ComponentName componentName=new ComponentName("com.example.min","com.example.min.MainActivity");
            intent.setComponent(componentName);
            intent.putExtra("visitDay",visitDay);
            startActivity(intent);

        }else {
            //wrong password
            Toast.makeText(this.getApplicationContext(), "아이디 혹은 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    public void signup(View view){
        //goto signup screen activity
        Intent intent=new Intent();
        ComponentName componentName=new ComponentName("com.example.min","com.example.min.signupscreen01");
        intent.setComponent(componentName);
        startActivity(intent);
    }
    public void google(View view){
        Toast.makeText(this.getApplicationContext(), "google으로 로그인", Toast.LENGTH_SHORT).show();
        //goto google login screen activity
    }
}