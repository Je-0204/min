//https://junyoung-developer.tistory.com/107
//https://stackoverflow.com/questions/1851633/how-to-add-a-button-dynamically-in-android/13423467
//http://sunmo.blogspot.com/2010/10/tabwidget-%EA%B0%81-%ED%8E%98%EC%9D%B4%EC%A7%80%EB%B3%84-%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0-%EB%B3%B4%EC%97%AC%EC%A3%BC%EA%B8%B0-2.html
//https://stackoverflow.com/questions/21950587/how-to-use-different-activities-with-tabhost-widget-in-android/21950690
//https://doraeul.tistory.com/21
//https://recipes4dev.tistory.com/42 - listView
//https://lktprogrammer.tistory.com/163 - listview adapter android
//https://stackoverflow.com/questions/4540754/how-do-you-dynamically-add-elements-to-a-listview-on-android - dynamically listview
package com.example.min;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.min.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int dicCnt=0;   //추가한 단어장 개수 count

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost=findViewById(R.id.host);
        tabHost.setup();

        TabHost.TabSpec spec= tabHost.newTabSpec("tab1");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.mypage_icon,null));
        spec.setContent(R.id.tab1);
        tabHost.addTab(spec);


        spec= tabHost.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.notice_icon,null));
        spec.setContent(R.id.tab2); //settingmain
        tabHost.addTab(spec);

        spec= tabHost.newTabSpec("tab3");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.setting_icon,null));
        spec.setContent(R.id.tab3); //공지사항
        tabHost.addTab(spec);


    }
    public void floatingButton_addDic(View view){
        //Toast.makeText(getApplicationContext(),"add Dic 클릭",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this, ChooseDictionary.class);
        startActivityForResult(intent,3000);

    }
    public void floatingButton_editScreen(View view){
        Toast.makeText(getApplicationContext(),"edit Dic 클릭",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==3000) {
            if (resultCode==1) {    //나만의 단어장 추가
                Toast.makeText(MainActivity.this, Integer.toString(resultCode), Toast.LENGTH_LONG).show();
            }else if (resultCode==2) {  //min 수능 단어장 추가
                Toast.makeText(MainActivity.this, Integer.toString(resultCode), Toast.LENGTH_LONG).show();
            }
            else if (resultCode==3) {  //min 토익 단어장 추가
                Toast.makeText(MainActivity.this, Integer.toString(resultCode), Toast.LENGTH_LONG).show();
            }
            else if (resultCode==4) {  //공유된 단어장 추가
                Toast.makeText(MainActivity.this, Integer.toString(resultCode), Toast.LENGTH_LONG).show();
            }else if (resultCode==-1) {  //취소버튼(단어장추가안해)
                Toast.makeText(MainActivity.this, Integer.toString(resultCode), Toast.LENGTH_LONG).show();
            }
        }
    }
}
