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
    ArrayList<SampleData> movieDataList;

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

        /*FloatingActionButton addFab=findViewById(R.id.floatingButton_addDic);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"add Dic 클릭",Toast.LENGTH_SHORT).show();
                //Button newBtn=new Button(this);
                Intent intent=new Intent();
                ComponentName componentName=new ComponentName("com.example.min","com.example.min.ChooseDictionary");
                intent.setComponent(componentName);
                startActivity(intent);

                //추가할 단어장 정보 받기
                Intent getDicNumIntent=getIntent();
                int addDicNum=getDicNumIntent.getIntExtra("addDic",-1);
                Toast.makeText(MainActivity.this, addDicNum, Toast.LENGTH_LONG).show();

                InitializeMovieData();

                ListView listView = (ListView)findViewById(R.id.startList);
                final MyAdapter myAdapter = new MyAdapter(MainActivity.this,movieDataList);

                listView.setAdapter(myAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView parent, View v, int position, long id){
                        Toast.makeText(getApplicationContext(),
                                myAdapter.getItem(position).getDicName(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        FloatingActionButton editFab=findViewById(R.id.floatingButton_editScreen);
        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"edit Dic 클릭",Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    public void floatingButton_addDic(View view){
        Toast.makeText(getApplicationContext(),"add Dic 클릭",Toast.LENGTH_SHORT).show();
        //Button newBtn=new Button(this);
        Intent intent=new Intent();
        ComponentName componentName=new ComponentName("com.example.min","com.example.min.ChooseDictionary");
        intent.setComponent(componentName);
        startActivity(intent);

        //추가할 단어장 정보 받기
        Intent getDicNumIntent=getIntent();
        int addDicNum=getDicNumIntent.getIntExtra("addDic",-1);
        Toast.makeText(MainActivity.this, addDicNum, Toast.LENGTH_LONG).show();

        InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.startList);
        final MyAdapter myAdapter = new MyAdapter(MainActivity.this,movieDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getDicName(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    public void floatingButton_editScreen(View view){
        Toast.makeText(getApplicationContext(),"edit Dic 클릭",Toast.LENGTH_SHORT).show();
    }
    public void InitializeMovieData()
    {
        movieDataList = new ArrayList<SampleData>();

        movieDataList.add(new SampleData("단어장1", "미션임파서블","15세 이상관람가"));
        movieDataList.add(new SampleData("단어장2", "아저씨","19세 이상관람가"));
        movieDataList.add(new SampleData("단어장3", "어벤져스","12세 이상관람가"));
    }
}
//listview(추가한 단어장) 정보를 담기위한 클래스
class SampleData {
    private String dicName;
    private String date;
    private String wordCnt;

    public SampleData(String dicName, String date, String wordCnt){
        this.dicName = dicName;
        this.date = date;
        this.wordCnt = wordCnt;
    }

    public String getDicName()
    {
        return this.dicName;
    }

    public String getDate()
    {
        return this.date;
    }

    public String getWordCnt()
    {
        return this.wordCnt;
    }
}
//어댑터 구현
class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleData> sample;

    public MyAdapter(Context context, ArrayList<SampleData> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public SampleData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.listview_custom, null);

        TextView imageView = view.findViewById(R.id.dicName);
        TextView movieName = view.findViewById(R.id.date);
        TextView grade = view.findViewById(R.id.wordCnt);

        imageView.setText(sample.get(position).getDicName());
        movieName.setText(sample.get(position).getDate());
        grade.setText(sample.get(position).getWordCnt());

        return view;
    }
}