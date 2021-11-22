//https://www.youtube.com/watch?v=plnLs6aST1M - 단어장 수정 dialog
package com.example.min;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ClickDictioinary extends AppCompatActivity {
    // 빈 데이터 리스트 생성.
    ArrayList<String> items;
    // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
    ArrayAdapter adapter;
    // listview 생성 및 adapter 지정.
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_dictioinary);

        Intent intent=getIntent();
        String dicName = intent.getStringExtra("dicName");

        TextView textView=findViewById(R.id.name);
        textView.setText(dicName);

        // 빈 데이터 리스트 생성.
        items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items) ;
        // listview 생성 및 adapter 지정.
        listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=(String) listview.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"ShortClick : "+name,Toast.LENGTH_SHORT).show();
                //단어 수정/삭제 screen
                AlertDialog.Builder builder=new AlertDialog.Builder(ClickDictioinary.this);
                View view1=getLayoutInflater().inflate(R.layout.custom_dialog_layout01,null);
                EditText et1=view1.findViewById(R.id.engWord);
                EditText et2=view1.findViewById(R.id.engMeaning);
                builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ClickDictioinary.this,et1.getText().toString(),Toast.LENGTH_SHORT).show();
                        items.set(position,et1.getText().toString());
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setView(view1);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();


                /*Intent intent=new Intent();
                ComponentName componentName=new ComponentName("com.example.min","com.example.min.screen");
                intent.setComponent(componentName);
                startActivity(intent);*/
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Removed : "+items.get(position),Toast.LENGTH_SHORT).show();
                AlertDialog.Builder ad=new AlertDialog.Builder(ClickDictioinary.this);
                ad.setIcon(R.mipmap.ic_launcher);//삭제 이미지
                ad.setTitle("단어 삭제");
                ad.setMessage("단어를 삭제하시겠습니까?");
                ad.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        items.remove(position);
                        listview.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();

                return true;
            }
        });

    }

    public void add(View view){
        int count;
        count = adapter.getCount();
        // 아이템 추가.
        items.add("LIST" + Integer.toString(count + 1));
        // listview 갱신
        listview.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }
    public void memorize(View view){

    }
    public void review(View view){

    }
}