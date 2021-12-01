package com.example.min;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    Button btn_withdrawal;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private TextView user_name;
    private TextView affiliation;

    private ImageView profile_image;
    private final String imgName = "osz.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_main);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        user_name = findViewById(R.id.user_name);
        affiliation = findViewById(R.id.affiliation);
        btn_logout_dialog = findViewById(R.id.logout);
        btn_withdrawal = findViewById(R.id.withdrawal);

        FirebaseUser firebaseUser = auth.getCurrentUser();

        profile_image = findViewById(R.id.profile_image);

        try {
            String imgpath = getCacheDir() + "/" + imgName;   // 내부 저장소에 저장되어 있는 이미지 경로
            Bitmap bm = BitmapFactory.decodeFile(imgpath);
            profile_image.setImageBitmap(bm);   // 내부 저장소에 저장된 이미지를 이미지뷰에 셋
            Toast.makeText(getApplicationContext(), "파일 로드 성공", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "파일 로드 실패", Toast.LENGTH_SHORT).show();
        }

        /*DocumentReference docRef = db.collection("UserInfo").document(firebaseUser.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        user_name.setText(document.get("Name").toString());
                        affiliation.setText(document.get("Affiliation").toString());
                    }
                    else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with", task.getException());
                }
            }
        });*/

        btn_logout_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsMain.this);

                builder.setTitle("min").setMessage("로그아웃 하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        auth.signOut();
                        Intent intent = new Intent(SettingsMain.this, Loginscreen01Activity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "로그아웃되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btn_withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsMain.this);

                builder.setTitle("min").setMessage("회원 탈퇴하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        auth.getCurrentUser().delete();
                        Intent intent = new Intent(SettingsMain.this, Loginscreen01Activity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "회원 탈퇴되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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

//    public void manage_shared_vocabulary(View view) {
//        //goto manage_shared_vocabulary
//        Intent intent = new Intent();
//        ComponentName componentName = new ComponentName("com.example.min", "com.example.min.SettingsManageSharedVocabulary");
//        intent.setComponent(componentName);
//        startActivity(intent);
//    }
//
//    public void manage_themes(View view) {
//        //goto manage_themes
//        Intent intent = new Intent();
//        ComponentName componentName = new ComponentName("com.example.min", "com.example.min.SettingsManageThemes");
//        intent.setComponent(componentName);
//        startActivity(intent);
//    }

     public void hide_statistical_data(View view){
        TableLayout table = findViewById(R.id.statistical_data);
        View bar = findViewById(R.id.bar_under_statistical_data);
        if(table.getVisibility() == view.GONE){
            table.setVisibility(view.VISIBLE);
            bar.setVisibility(view.VISIBLE);
        }
        else{
            table.setVisibility(view.GONE);
            bar.setVisibility(view.GONE);
        }
     }
}