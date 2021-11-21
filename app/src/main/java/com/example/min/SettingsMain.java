package com.example.min;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SettingsMain extends AppCompatActivity {

    Button btn_logout_dialog;
    Button btn_allow_logout;
    Button btn_disallow_logout;
    TextView tv_logout;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SettingsMain extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private View user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_main);

        btn_logout_dialog = findViewById(R.id.logout);

        btn_logout_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
    }

    public void modify_user_profile(View view) {
        //goto modify_user_profile(View view)
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.min", "com.example.min.SettingsModifyUserProfile");
        intent.setComponent(componentName);
        startActivity(intent);
    }

    public void manage_push_notification(View view){
        //goto manage_push_notification
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.min","com.example.min.SettingsManagePushNotification");
        intent.setComponent(componentName);
        startActivity(intent);
    }

    public void manage_shared_vocabulary(View view) {
        //goto manage_shared_vocabulary
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.min", "com.example.min.SettingsManageSharedVocabulary");
        intent.setComponent(componentName);
        startActivity(intent);
    }

    public void manage_themes(View view) {
        //goto manage_themes
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.min", "com.example.min.SettingsManageThemes");
        intent.setComponent(componentName);
        startActivity(intent);
    }

    //logout
    public void logout(View view) {
        AlertDialog.Builder ad = new AlertDialog.Builder(SettingsMain.this);
        ad.setIcon(R.mipmap.ic_launcher);
        ad.setTitle("제목");
        ad.setMessage("홍드로이드");
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user_name = findViewById(R.id.user_name);

        FirebaseUser firebaseUser = auth.getCurrentUser();

        DocumentReference docRef = db.collection("UserInfo").document(firebaseUser.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        user_name.setText(document.get("Name").toString());
                    }
                    else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with", task.getException());
                }
            }
        });
    }
    //account withdrawl
}