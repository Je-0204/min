package com.example.min;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class SettingsModifyUserProfile extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    AppCompatEditText change_name;
    AppCompatEditText change_affiliation;
    AppCompatButton btn_apply;
    boolean isClickChangeProfile;

    private final int GET_GALLERY_IMAGE = 200;
    private String imgName = "osz.png";
    private ImageView profile_image;
    private Button btn_change_profile_image;
    private Bitmap imgBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_modify_user_profile);

        profile_image = findViewById(R.id.profile_image);
        btn_change_profile_image = findViewById(R.id.btn_change_profile_image);
        isClickChangeProfile = false;

        try {
            String imgpath = getCacheDir() + "/" + imgName;   // 내부 저장소에 저장되어 있는 이미지 경로
            Bitmap bm = BitmapFactory.decodeFile(imgpath);
            profile_image.setImageBitmap(bm);   // 내부 저장소에 저장된 이미지를 이미지뷰에 셋
        } catch (Exception e) {

        }

        btn_change_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);

                isClickChangeProfile = true;
            }
        });

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser firebaseUser = auth.getCurrentUser();

        DocumentReference docRef = db.collection("UserInfo").document(firebaseUser.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        change_name.setHint(document.get("Name").toString());
                        change_affiliation.setHint(document.get("Affiliation").toString());
                    }
                    else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with", task.getException());
                }
            }
        });


        change_name = findViewById(R.id.change_name);
        change_affiliation = findViewById(R.id.change_affiliation);
        btn_apply = findViewById(R.id.apply);
    }

    public void back_to_settings_main(View view){
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.example.min", "com.example.min.SettingsMain");
        intent.setComponent(componentName);
        startActivity(intent);
    }

    public void apply_modification(View view){

        String name = change_name.getText().toString();
        String affiliation = change_affiliation.getText().toString();

        saveBitmapToJpeg(imgBitmap);

        UserAccount account = new UserAccount();
        account.setName(name);
        account.setAffiliation(affiliation);

        FirebaseUser firebaseUser = auth.getCurrentUser();
        DocumentReference updateRef = db.collection("UserInfo").document(firebaseUser.getUid());

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

        change_name.setHint(change_name.getText().toString());
        change_affiliation.setHint(change_affiliation.getText().toString());
        Toast.makeText(getApplicationContext(), "적용되었습니다.",Toast.LENGTH_SHORT).show();

        change_name.setText(null);
        change_affiliation.setText(null);
        isClickChangeProfile = false;
    }

    public void cancel_modification(View view){

        Toast.makeText(getApplicationContext(), "취소되었습니다.",Toast.LENGTH_SHORT).show();

        isClickChangeProfile = false;
        change_name.setText(null);
        change_affiliation.setText(null);

        Bitmap bitmap = BitmapFactory.decodeFile(getCacheDir() + "/" + imgName);
        profile_image.setImageBitmap(bitmap);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();

            ContentResolver resolver = getContentResolver();
            try {
                InputStream inputStream = resolver.openInputStream(selectedImageUri);
                imgBitmap = BitmapFactory.decodeStream(inputStream);
                profile_image.setImageBitmap(imgBitmap);
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    //가져온 비트맵을 저장
    public void saveBitmapToJpeg(Bitmap bitmap) {   // 선택한 이미지 내부 저장소에 저장
        File tempFile = new File(getCacheDir(), imgName);    // 파일 경로와 이름 넣기
        try {
            tempFile.createNewFile();   // 자동으로 빈 파일을 생성하기
            FileOutputStream out = new FileOutputStream(tempFile);  // 파일을 쓸 수 있는 스트림을 준비하기
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);   // compress 함수를 사용해 스트림에 비트맵을 저장하기
            out.close();    // 스트림 닫아주기
        } catch (Exception e) {
        }
    }
}
