package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Notification extends AppCompatActivity {

    private String first_notification_content="안녕하세요, 영어단어암기앱 min 개발팀입니다. 드디어 2021년 12월 14일 오늘 저희 min 앱이 플레이 스토어에 런칭되었습니다." +
            "저희 팀의 좌우명인 적게 일하고 돈 많이 벌자처럼 앱을 사용하시는 여러분들도 저희 앱 을 통해 적은 노력으로 많은 성과 가져 가시길 바라겠습니다. 감사합니다.";
    private String second_notification_content="안녕하세요, 영어단기앱 min 개발팀입니다. 현재 저희 min 앱은 5 개의 단어장을 제공하고 있습니다." +
            "하지만 이에 멈추지 않고 더 많은 단어장 제공으로 사용자 여러분의 만족도를 높이고자 해커스 영어 단어장을 준비해보았습니다. 많은 이용 부탁드립니다. 감사합니다.";

    TextView textView_first_notification_content;
    TextView textView_second_notification_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        textView_first_notification_content=findViewById(R.id.text_first_news_content);
        textView_second_notification_content=findViewById(R.id.text_second_news_content);

        textView_first_notification_content.setText(first_notification_content);
        textView_first_notification_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Notification.this);

                builder.setTitle("min").setMessage("2021년 12월 14일 min 영어 단어장 런칭");
                builder.setMessage(first_notification_content);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        textView_second_notification_content.setText(second_notification_content);
        textView_second_notification_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Notification.this);

                builder.setTitle("min").setMessage("2021년 12월 14일 min 영어 단어장 런칭");
                builder.setMessage(second_notification_content);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}