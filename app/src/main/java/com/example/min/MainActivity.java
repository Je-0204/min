//https://junyoung-developer.tistory.com/107
//https://stackoverflow.com/questions/1851633/how-to-add-a-button-dynamically-in-android/13423467
//http://sunmo.blogspot.com/2010/10/tabwidget-%EA%B0%81-%ED%8E%98%EC%9D%B4%EC%A7%80%EB%B3%84-%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0-%EB%B3%B4%EC%97%AC%EC%A3%BC%EA%B8%B0-2.html
//https://stackoverflow.com/questions/21950587/how-to-use-different-activities-with-tabhost-widget-in-android/21950690
//https://doraeul.tistory.com/21
//https://recipes4dev.tistory.com/42 - listView
//https://recipes4dev.tistory.com/45 - listview and button
//https://www.youtube.com/watch?v=aJ8pYAtMdlU - dynamically listview 유튜브
//https://kiwinam.com/posts/23/android-start-activity-for-result/ -startactivityforresult()
//https://stackoverflow.com/questions/2217753/changing-background-color-of-listview-items-on-android-리스트뷰 아이템 배경 바꾸기

package com.example.min;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.min.ChooseDictionary;
import com.example.min.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int dicCnt=0;   //추가한 단어장 개수 count

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private TextView username;

    static ListView listview;
    static ListViewAdapter adapter;
    static ArrayList<String> items;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listView1);
        items = new ArrayList<>();
        context=getApplicationContext();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        username = findViewById(R.id.username);
        FirebaseUser firebaseUser = auth.getCurrentUser();

        DocumentReference docRef = db.collection("UserInfo").document(firebaseUser.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        username.setText(document.get("Name").toString());
                    }
                    else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with", task.getException());
                }
            }
        });

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


        String text="null";
        if(requestCode==3000) {
            if (resultCode==1) {    //나만의 단어장 추가
                text="나만의 단어장";
                //Toast.makeText(MainActivity.this, Integer.toString(resultCode), Toast.LENGTH_LONG).show();

            }else if (resultCode==2) {  //min 수능 단어장 추가
                text="min 수능 단어장";
                //Toast.makeText(MainActivity.this, Integer.toString(resultCode), Toast.LENGTH_LONG).show();
            }
            else if (resultCode==3) {  //min 토익 단어장 추가
                text="min 토익 단어장";
                //Toast.makeText(MainActivity.this, Integer.toString(resultCode), Toast.LENGTH_LONG).show();
            }
            else if (resultCode==4) {  //공유된 단어장 추가
                text="공유된 단어장";
                //Toast.makeText(MainActivity.this, Integer.toString(resultCode), Toast.LENGTH_LONG).show();
            }else if (resultCode==-1) {  //취소버튼(단어장추가안해)
                //Toast.makeText(MainActivity.this, Integer.toString(resultCode), Toast.LENGTH_LONG).show();
            }
        }

        if(resultCode!=-1){
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String name=(String) listview.getItemAtPosition(i);
                    //Toast.makeText(getApplicationContext(),"ShortClick : "+name,Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    ComponentName componentName=new ComponentName("com.example.min","com.example.min.ClickDictioinary");
                    intent.setComponent(componentName);
                    intent.putExtra("dicName",name);
                    startActivity(intent);
                }
            });
            listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    String name=(String) listview.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(),"LongClick : "+name,Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            adapter = new ListViewAdapter(getApplicationContext(), items);
            listview.setAdapter(adapter);
            addItem(text);
        }
    }
    public static void removeItem(int remove){
        items.remove(remove);
        listview.setAdapter(adapter);
    }
    public static void addItem(String item){
        items.add(item);
        listview.setAdapter(adapter);
    }

}

class ListViewAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;
    public ListViewAdapter(Context context, ArrayList<String> items){
        super(context,R.layout.listview_custom,items);
        this.context=context;
        list=items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.listview_custom,null);

            TextView date=convertView.findViewById(R.id.date);

            TextView name=convertView.findViewById(R.id.dicName);
            name.setText(list.get(position));

            ImageView settingBtn=convertView.findViewById(R.id.dicSetting);

            settingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //intent for dicTheme
                    //Toast.makeText(getApplicationContext(), "click : settingBtn", Toast.LENGTH_LONG).show();

                }
            });


        }
        return convertView;
    }
}