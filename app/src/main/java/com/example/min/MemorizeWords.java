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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

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

    private ColorStateList textColorDefault;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private boolean answered;

    public static int vocabulary;

    public static void set_vocabulary(int voca) {
        vocabulary = voca;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorize_words);

        textViewQuestion = findViewById(R.id.textview_question);
        textViewQuestionCount = findViewById(R.id.progress_count);
        rbGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radiobtn1);
        rb2 = findViewById(R.id.radiobtn2);
        rb3 = findViewById(R.id.radiobtn3);
        rb4 = findViewById(R.id.radiobtn4);
        buttonConfirmNext = findViewById(R.id.btn_confirm_next);

        textColorDefault = rb1.getTextColors();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        questionList = new List<Question>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Question> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(Question question) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Question> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Question> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Question get(int i) {
                return null;
            }

            @Override
            public Question set(int i, Question question) {
                return null;
            }

            @Override
            public void add(int i, Question question) {

            }

            @Override
            public Question remove(int i) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Question> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Question> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Question> subList(int i, int i1) {
                return null;
            }
        };

        if(vocabulary == 1) {
            DatabaseReference dbRef = db.getReference("CSAT");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Question voca = new Question();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        voca.setQuestion(dataSnapshot.getKey());
                        voca.setOption1(dataSnapshot.child("option1").getValue().toString());
                        voca.setOption2(dataSnapshot.child("option2").getValue().toString());
                        voca.setOption3(dataSnapshot.child("option3").getValue().toString());
                        voca.setOption4(dataSnapshot.child("option4").getValue().toString());
                        voca.setAnswerNr(Integer.parseInt(dataSnapshot.child("answer_nr").getValue().toString()));
                        questionList.add(voca);
                    }
                    Log.d(TAG, "Data load success");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
        }
        else if(vocabulary == 2) {
            DatabaseReference dbRef = db.getReference("TOEIC");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Question voca = new Question();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        voca.setQuestion(dataSnapshot.getKey());
                        voca.setOption1(dataSnapshot.child("option1").getValue().toString());
                        voca.setOption2(dataSnapshot.child("option2").getValue().toString());
                        voca.setOption3(dataSnapshot.child("option3").getValue().toString());
                        voca.setOption4(dataSnapshot.child("option4").getValue().toString());
                        voca.setAnswerNr(Integer.parseInt(dataSnapshot.child("answer_nr").getValue().toString()));
                        questionList.add(voca);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
        }
        else if(vocabulary == 3) {
            DatabaseReference dbRef = db.getReference("TOEFL");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Question voca = new Question();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        voca.setQuestion(dataSnapshot.getKey());
                        voca.setOption1(dataSnapshot.child("option1").getValue().toString());
                        voca.setOption2(dataSnapshot.child("option2").getValue().toString());
                        voca.setOption3(dataSnapshot.child("option3").getValue().toString());
                        voca.setOption4(dataSnapshot.child("option4").getValue().toString());
                        voca.setAnswerNr(Integer.parseInt(dataSnapshot.child("answer_nr").getValue().toString()));
                        questionList.add(voca);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
        }
        else if(vocabulary == 4) {
            DatabaseReference dbRef = db.getReference("EleMid");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Question voca = new Question();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        voca.setQuestion(dataSnapshot.getKey());
                        voca.setOption1(dataSnapshot.child("option1").getValue().toString());
                        voca.setOption2(dataSnapshot.child("option2").getValue().toString());
                        voca.setOption3(dataSnapshot.child("option3").getValue().toString());
                        voca.setOption4(dataSnapshot.child("option4").getValue().toString());
                        voca.setAnswerNr(Integer.parseInt(dataSnapshot.child("answer_nr").getValue().toString()));
                        questionList.add(voca);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
        }
        else if(vocabulary == 5) {
            DatabaseReference dbRef = db.getReference("TEPS");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Question voca = new Question();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        voca.setQuestion(dataSnapshot.getKey());
                        voca.setOption1(dataSnapshot.child("option1").getValue().toString());
                        voca.setOption2(dataSnapshot.child("option2").getValue().toString());
                        voca.setOption3(dataSnapshot.child("option3").getValue().toString());
                        voca.setOption4(dataSnapshot.child("option4").getValue().toString());
                        voca.setAnswerNr(Integer.parseInt(dataSnapshot.child("answer_nr").getValue().toString()));
                        questionList.add(voca);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
        }

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
        rb1.setTextColor(textColorDefault);
        rb2.setTextColor(textColorDefault);
        rb3.setTextColor(textColorDefault);
        rb4.setTextColor(textColorDefault);
        rbGroup.clearCheck();

        if(questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption1());
            rb3.setText(currentQuestion.getOption1());
            rb4.setText(currentQuestion.getOption1());

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
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestionCount.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestionCount.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestionCount.setText("Answer 3 is correct");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                textViewQuestionCount.setText("Answer 4 is correct");
                break;
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