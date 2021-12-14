//https://www.youtube.com/watch?v=plnLs6aST1M - 단어장 수정 dialog
package com.example.min;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ClickDictioinary extends AppCompatActivity {
    private static final String TAG = "DB";
    // 빈 데이터 리스트 생성.
    ArrayList<String> items;
    ArrayList<String> items2;
    // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
    ArrayAdapter adapter;
    ArrayAdapter adapter2;
    // listview 생성 및 adapter 지정.
    ListView listview;
    ListView listview2;
    //단어 뜻 저장하는 리스트, 데이터리스트와 짝꿍
    ArrayList<String> itemMeanings;
    ArrayList<String> itemMeanings2;
    public int voca;
    private ArrayList<Question> questionList0;
    private ArrayList<Question> questionList1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_dictioinary);

        Intent intent=getIntent();
        String dicName = intent.getStringExtra("dicName");
        String dicColor=intent.getStringExtra("dicColor");
        voca = intent.getIntExtra("voca", 0);

        items = new ArrayList<String>();
        itemMeanings = new ArrayList<String>();

        items2 = new ArrayList<String>();
        itemMeanings2 = new ArrayList<String>();

        FirebaseDatabase db = FirebaseDatabase.getInstance();

        questionList0 = new ArrayList<>();
        questionList1 = new ArrayList<>();

        if(voca == 1) {
            DatabaseReference dbRef = db.getReference("CSAT");
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String question = dataSnapshot.getKey();
                        String option1 = dataSnapshot.child("option1").getValue().toString();
                        String option2 = dataSnapshot.child("option2").getValue().toString();
                        String option3 = dataSnapshot.child("option3").getValue().toString();
                        String option4 = dataSnapshot.child("option4").getValue().toString();
                        String answer_nr = dataSnapshot.child("answer_nr").getValue().toString();
                        Question vocab = new Question(question, option1, option2, option3, option4, Integer.parseInt(answer_nr));
                        if(dataSnapshot.child("is_memorized").getValue().toString().equals("0")) {
                            items.add(dataSnapshot.getKey());
                            itemMeanings.add(dataSnapshot.child("뜻").getValue().toString());
                            questionList0.add(vocab);
                        }
                        else if(dataSnapshot.child("is_memorized").getValue().toString().equals("1")) {
                            items2.add(dataSnapshot.getKey());
                            itemMeanings2.add(dataSnapshot.child("뜻").getValue().toString());
                            questionList1.add(vocab);
                        }
                    }
                    Log.d(TAG, "Data load success");
                    adapter = new ArrayAdapter(ClickDictioinary.this, android.R.layout.simple_list_item_1, items) ;
                    adapter2 = new ArrayAdapter(ClickDictioinary.this, android.R.layout.simple_list_item_1, items2);
                    // listview 생성 및 adapter 지정.
                    listview = (ListView) findViewById(R.id.listview1);
                    listview.setAdapter(adapter);
                    listview2 = (ListView) findViewById(R.id.listview2);
                    listview2.setAdapter(adapter2);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w(TAG, "Data load failed");
                }
            });
            Collections.shuffle(questionList0);
            Collections.shuffle(questionList1);
        }

        if(voca == 2) {
            DatabaseReference dbRef = db.getReference("TOEIC");
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String question = dataSnapshot.getKey();
                        String option1 = dataSnapshot.child("option1").getValue().toString();
                        String option2 = dataSnapshot.child("option2").getValue().toString();
                        String option3 = dataSnapshot.child("option3").getValue().toString();
                        String option4 = dataSnapshot.child("option4").getValue().toString();
                        String answer_nr = dataSnapshot.child("answer_nr").getValue().toString();
                        Question vocab = new Question(question, option1, option2, option3, option4, Integer.parseInt(answer_nr));
                        if(dataSnapshot.child("is_memorized").getValue().toString().equals("0")) {
                            items.add(dataSnapshot.getKey());
                            itemMeanings.add(dataSnapshot.child("뜻").getValue().toString());
                            questionList0.add(vocab);
                        }
                        else if(dataSnapshot.child("is_memorized").getValue().toString().equals("1")) {
                            items2.add(dataSnapshot.getKey());
                            itemMeanings2.add(dataSnapshot.child("뜻").getValue().toString());
                            questionList1.add(vocab);
                        }
                    }
                    Log.d(TAG, "Data load success");
                    adapter = new ArrayAdapter(ClickDictioinary.this, android.R.layout.simple_list_item_1, items) ;
                    adapter2 = new ArrayAdapter(ClickDictioinary.this, android.R.layout.simple_list_item_1, items2);
                    // listview 생성 및 adapter 지정.
                    listview = (ListView) findViewById(R.id.listview1);
                    listview.setAdapter(adapter);
                    listview2 = (ListView) findViewById(R.id.listview2);
                    listview2.setAdapter(adapter2);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w(TAG, "Data load failed");
                }
            });
            Collections.shuffle(questionList0);
            Collections.shuffle(questionList1);
        }

        TextView textView=findViewById(R.id.name);
        textView.setText(dicName);
        textView.setBackgroundColor(ItemColor(dicColor));//Integer.toString(value,16)

        //Toast.makeText(ClickDictioinary.this,""+ItemColor(dicColor),Toast.LENGTH_SHORT).show();

        // 빈 데이터 리스트 생성.
        items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.

        adapter = new ArrayAdapter(ClickDictioinary.this, android.R.layout.simple_list_item_1, items) ;
        // listview 생성 및 adapter 지정.
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        adapter2 = new ArrayAdapter(ClickDictioinary.this, android.R.layout.simple_list_item_1, items2);
        listview2 = (ListView) findViewById(R.id.listview2);
        listview2.setAdapter(adapter2);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=(String) listview.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"ShortClick : "+name,Toast.LENGTH_SHORT).show();
                //단어 수정/삭제 screen
                AlertDialog.Builder builder=new AlertDialog.Builder(ClickDictioinary.this);
                View view1=getLayoutInflater().inflate(R.layout.custom_dialog_layout01,null);
                EditText et1=view1.findViewById(R.id.engWord);
                et1.setText(items.get(position));
                EditText et2=view1.findViewById(R.id.engMeaning);
                et2.setText(itemMeanings.get(position));
                builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ClickDictioinary.this,et1.getText().toString(),Toast.LENGTH_SHORT).show();
                        items.set(position,et1.getText().toString());
                        itemMeanings.set(position,et2.getText().toString());
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
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),"Removed : "+items.get(position),Toast.LENGTH_SHORT).show();
                AlertDialog.Builder ad=new AlertDialog.Builder(ClickDictioinary.this);
                ad.setIcon(R.mipmap.ic_launcher);//삭제 이미지
                ad.setTitle("단어 삭제");
                ad.setMessage("단어를 삭제하시겠습니까?");
                ad.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        items.remove(position);
                        itemMeanings.remove(position);
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
        // listview 갱신
        listview.setAdapter(adapter);

        AlertDialog.Builder builder=new AlertDialog.Builder(ClickDictioinary.this);
        View view1=getLayoutInflater().inflate(R.layout.custom_dialog_layout02,null);//add word custom dialog
        EditText et1=view1.findViewById(R.id.engWord);
        EditText et2=view1.findViewById(R.id.engMeaning);
        builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(ClickDictioinary.this,et1.getText().toString(),Toast.LENGTH_SHORT).show();
                // 아이템 추가.
                items.add(et1.getText().toString());
                itemMeanings.add(et2.getText().toString());
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

    }
    public void memorize(View view){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.min","com.example.min.MemorizeWords");
        intent.setComponent(componentName);
        intent.putParcelableArrayListExtra("questionList", questionList0);
        intent.putExtra("voca", voca);
        startActivity(intent);
    }
    public void review(View view){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.min","com.example.min.MemorizeWords");
        intent.setComponent(componentName);
        intent.putParcelableArrayListExtra("questionList", questionList1);
        intent.putExtra("voca", voca);
        startActivity(intent);
    }
    public int ItemColor(String color){
        switch (color){
            case "YELLOW":
                return ContextCompat.getColor(getApplicationContext(),R.color.pastel_yellow);
            case "PINK":
                return ContextCompat.getColor(getApplicationContext(),R.color.pastel_pink);
            case "GREEN":
                return ContextCompat.getColor(getApplicationContext(),R.color.pastel_green);
            case "BLUE":
                return ContextCompat.getColor(getApplicationContext(),R.color.pastel_blue);
            case "PURPLE":
                return ContextCompat.getColor(getApplicationContext(),R.color.pastel_purple);
            case "GRAY":
                return ContextCompat.getColor(getApplicationContext(),R.color.pastel_gray);
            default:
                return ContextCompat.getColor(getApplicationContext(),R.color.white);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}