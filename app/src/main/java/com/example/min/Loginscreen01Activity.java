package com.example.min;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Loginscreen01Activity extends AppCompatActivity {

    int visitDay=0;
    private EditText etId, etPassword;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen01);

        TextView signup = findViewById(R.id.text_signup); //회원가입 textview 밑줄 긋기
        signup.setPaintFlags(signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etId = findViewById(R.id.login_id);
        etPassword = findViewById(R.id.login_password);
        Button btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 로그인 요청
                String strEmail = etId.getText().toString();
                String strPwd = etPassword.getText().toString();

                auth.signInWithEmailAndPassword(strEmail, strPwd)
                        .addOnCompleteListener(Loginscreen01Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) { // 로그인 성공
                                    visitDay++;

                                    Intent intent=new Intent();
                                    ComponentName componentName=new ComponentName("com.example.min","com.example.min.MainActivity");
                                    intent.setComponent(componentName);
                                    intent.putExtra("visitDay",visitDay);
                                    startActivity(intent);
                                    finish();
                                } else { // 로그인 실패
                                    Toast.makeText(Loginscreen01Activity.this, "아이디 혹은 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
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