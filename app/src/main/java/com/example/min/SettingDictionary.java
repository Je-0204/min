//https://stackoverflow.com/questions/50294528/onclick-to-change-the-button-border-color/50294908 - android add button border when click button
//https://stackoverflow.com/questions/15187609/android-button-border-dynamically/15215858 - android button border dynamically

//https://gogorchg.tistory.com/entry/Android-Textview-Copy-Clipboardmanager - textview copy/paste

package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SettingDictionary extends AppCompatActivity {
    Intent intent;
    String putExtraColorValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_dictionary);
        intent=new Intent();
    }
    public void yellow(View view){
        putExtraColorValue="YELLOW";
        Toast.makeText(SettingDictionary.this, putExtraColorValue, Toast.LENGTH_SHORT).show();
    }
    public void pink(View view){
        putExtraColorValue="PINK";
        Toast.makeText(SettingDictionary.this, putExtraColorValue, Toast.LENGTH_SHORT).show();
    }
    public void green(View view){
        putExtraColorValue="GREEN";
        Toast.makeText(SettingDictionary.this, putExtraColorValue, Toast.LENGTH_SHORT).show();
    }
    public void blue(View view){
        putExtraColorValue="BLUE";
        Toast.makeText(SettingDictionary.this, putExtraColorValue, Toast.LENGTH_SHORT).show();
    }
    public void purple(View view){
        putExtraColorValue="PURPLE";
        Toast.makeText(SettingDictionary.this, putExtraColorValue, Toast.LENGTH_SHORT).show();
    }
    public void gray(View view){
        putExtraColorValue="GRAY";
        Toast.makeText(SettingDictionary.this, putExtraColorValue, Toast.LENGTH_SHORT).show();
    }
    public void OK(View view){
        CheckBox checkBox = (CheckBox) findViewById(R.id.alarmCheckBox);
        if (checkBox.isChecked()) {
            // TODO : CheckBox is checked.
        } else {
            // TODO : CheckBox is unchecked.
        }

        intent.putExtra("color",putExtraColorValue);
        setResult(RESULT_OK,intent);
        finish();
    }
}