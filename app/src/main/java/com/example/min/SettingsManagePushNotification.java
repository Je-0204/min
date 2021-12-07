package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SettingsManagePushNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_manage_push_notification);
    }

    public void hide_switch(View view){
        Switch s = findViewById(R.id.collapse_switch);

        if(s.getVisibility() == view.GONE) s.setVisibility(view.VISIBLE);
        else s.setVisibility(view.GONE);
    }

    public void back_to_settings_main(View view){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.min", "com.example.min.SettingsMain");
        intent.setComponent(componentName);
        startActivity(intent);
    }
}