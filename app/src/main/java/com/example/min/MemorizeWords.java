package com.example.min;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MemorizeWords extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;


    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private ArrayList<Question> questionList;
    private int answerNr;


    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        questionCounter = 0;
        currentQuestion = new Question();

        Intent intent = getIntent();
        questionList = intent.getParcelableArrayListExtra("questionList");
        questionCountTotal = 10;

        setContentView(R.layout.activity_memorize_words);


        textViewQuestion = findViewById(R.id.textview_question);
        textViewQuestionCount = findViewById(R.id.progress_count);
        rbGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radiobtn1);
        rb2 = findViewById(R.id.radiobtn2);
        rb3 = findViewById(R.id.radiobtn3);
        rb4 = findViewById(R.id.radiobtn4);
        buttonConfirmNext = findViewById(R.id.btn_confirm_next);


        showNextQuestion();


        buttonConfirmNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!answered) {
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(MemorizeWords.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);
        rb4.setTextColor(Color.BLACK);
        rbGroup.clearCheck();

        if(questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            questionCounter++;
            textViewQuestionCount.setText(questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("선택");
        } else {
            finishQuiz();
        }
    }


    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        if(currentQuestion.getAnswerNr() == answerNr) {
            if(currentQuestion.getAnswerNr() == 1) {
                rb1.setTextColor(Color.GREEN);
            }
            else if(currentQuestion.getAnswerNr() == 2) {
                rb2.setTextColor(Color.GREEN);
            }
            else if(currentQuestion.getAnswerNr() == 3) {
                rb3.setTextColor(Color.GREEN);
            }
            else if(currentQuestion.getAnswerNr() == 4) {
                rb4.setTextColor(Color.GREEN);
            }
        }
        else {
            if(currentQuestion.getAnswerNr() == 1) {
                rb1.setTextColor(Color.GREEN);
            }
            else if(currentQuestion.getAnswerNr() == 2) {
                rb2.setTextColor(Color.GREEN);
            }
            else if(currentQuestion.getAnswerNr() == 3) {
                rb3.setTextColor(Color.GREEN);
            }
            else if(currentQuestion.getAnswerNr() == 4) {
                rb4.setTextColor(Color.GREEN);
            }
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("다음");
        }else {
            buttonConfirmNext.setText("끝");
        }
    }

    private void finishQuiz() {
        finish();
    }
}