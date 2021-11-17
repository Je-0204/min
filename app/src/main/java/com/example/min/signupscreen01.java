//https://h5bak.tistory.com/113
//http://dailyddubby.blogspot.com/2018/03/11-spinnercombobox-custom.html
//https://black-jin0427.tistory.com/222 - 스피너 사용법
//https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=friend3515&logNo=178965040 - 스피너 힌트
//https://stackoverflow.com/questions/37019941/how-to-add-a-hint-in-spinner-in-xml - android spinner hint

package com.example.min;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class signupscreen01 extends AppCompatActivity {

    private Spinner spinnerJob;
    private Spinner spinnerDic;

    String selectedJob;
    String selectedDic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupscreen01);

        spinnerJob = findViewById(R.id.spinner_job);
        spinnerDic = findViewById(R.id.spinner_dic);


                spinnerJob = findViewById(R.id.spinner_job);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(signupscreen01.this, R.array.job, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerJob.setAdapter(adapter);
                spinnerJob.setPrompt("직업 선택");
                spinnerJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedJob = parent.getItemAtPosition(position).toString();
                        //Toast.makeText(signupscreen01.this,selectedJob,Toast.LENGTH_SHORT).show();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                spinnerDic = findViewById(R.id.spinner_dic);
                spinnerDic.setPrompt("단어 암기 목적");
                spinnerDic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedDic = parent.getItemAtPosition(position).toString();
                        //Toast.makeText(signupscreen01.this,selectedDic,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
            });
    }
    public void next(View view){
        Intent intent=new Intent();
        ComponentName componentName=new ComponentName("com.example.min","com.example.min.signupscreen02");
        intent.setComponent(componentName);
        intent.putExtra("job",selectedJob);
        intent.putExtra("dic",selectedDic);
        startActivity(intent);
    }
}