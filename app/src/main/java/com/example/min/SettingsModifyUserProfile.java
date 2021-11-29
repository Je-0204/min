package com.example.min;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class SettingsModifyUserProfile extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    AppCompatEditText change_name;
    AppCompatEditText change_affiliation;
    AppCompatButton btn_apply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_modify_user_profile);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        change_name = findViewById(R.id.change_name);
        change_affiliation = findViewById(R.id.change_affiliation);
        btn_apply = findViewById(R.id.apply);

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = change_name.getText().toString();
                String affiliation = change_affiliation.getText().toString();

                UserAccount account = new UserAccount();
                account.setName(name);
                account.setAffiliation(affiliation);

                DocumentReference updateRef = db.collection("UserInfo").document(account.getIdToken());

                updateRef
                        .update("Name", account.getName(), "Affiliation", account.getAffiliation())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error Updating document", e);
                            }
                        });
            }
        });
    }

    public void back_to_settings_main(View view){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.min", "com.example.min.SettingsMain");
        intent.setComponent(componentName);
        startActivity(intent);
    }

    public void apply_modification(View view){
        Toast.makeText(getApplicationContext(), "변경되었습니다.",Toast.LENGTH_SHORT).show();
    }

    public void cancel_modification(View view){
        Toast.makeText(getApplicationContext(), "취소되었습니다.",Toast.LENGTH_SHORT).show();
    }
}