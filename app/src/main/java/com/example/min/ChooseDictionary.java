//https://yoo-hyeok.tistory.com/55
package com.example.min;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseDictionary extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radiobtn1,radiobtn2,radiobtn3,radiobtn4;
    private EditText editTextForBtn4;
    private int chooseDic;  //선택한 단어장 정보 리턴

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dictionary);

        radiobtn1=findViewById(R.id.radiobtn1);
        radiobtn2=findViewById(R.id.radiobtn2);
        radiobtn3=findViewById(R.id.radiobtn3);
        radiobtn4=findViewById(R.id.radiobtn4);
        editTextForBtn4=findViewById(R.id.editTextForSharingDic);

        radioGroup=findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
    }
    public void choose(View view){
        //메인으로 이동
        Intent intent=new Intent();
        intent.putExtra("addDIc",chooseDic);
        setResult(chooseDic);
        finish();
    }
    public void cancel(View view){
        //메인으로 이동
        chooseDic=-1;
        Intent intent=new Intent();
        intent.putExtra("addDic",chooseDic);
        setResult(chooseDic);
        finish();
    }
    //라디오 그룹 클릭 리스너
     RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
     @Override
     public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
         if(i == R.id.radiobtn1){
             editTextForBtn4.setEnabled(false);
            //Toast.makeText(ChooseDictionary.this, "나만의 단어장", Toast.LENGTH_SHORT).show();
            chooseDic=1;
         } else if(i == R.id.radiobtn2){
             editTextForBtn4.setEnabled(false);
            //Toast.makeText(ChooseDictionary.this, "min 수능 단어장", Toast.LENGTH_SHORT).show();
             chooseDic=2;
         }else if(i == R.id.radiobtn3){
             editTextForBtn4.setEnabled(false);
             //Toast.makeText(ChooseDictionary.this, "min 토익 단어장", Toast.LENGTH_SHORT).show();
             chooseDic=3;
         }else if(i == R.id.radiobtn4){
             editTextForBtn4.setEnabled(true);
             //Toast.makeText(ChooseDictionary.this, "공유된 단어장", Toast.LENGTH_SHORT).show();
             chooseDic=4;
         }
     }
    };
}