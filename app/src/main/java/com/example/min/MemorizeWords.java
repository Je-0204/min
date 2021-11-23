package com.example.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MemorizeWords extends AppCompatActivity {
    //progress count in java
    int count;
    String solution;
    int index_of_solution;
    //record either multiple_choice[i] is clicked or not
    boolean[] isClicked;

    //control objects in xml file
    TextView progress_count;
    TextView problem;
    Button[] multiple_choice;

    //temporary data
    String[] problems = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    String[] solutions = {"하나", "둘", "셋", "넷", "다섯", "여섯", "일곱", "여덞", "아홉", "열"};
    boolean[] isUsedSolutions;
    String[] wrong_solutions = {"사자", "호랑이", "원숭이", "코기리", "개", "고양이", "하마", "기린", "염소", "소"}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initial settings
        count = 0;
        for(int i = 0; i < 4; ++i) {
            isClicked[i] = false;
        }
        for (int i = 0; i < 10; ++i) {
            isUsedSolutions[i] = false;
        }
        solution = solutions[count];

        progress_count = findViewById(R.id.progress_count);
        problem = findViewById(R.id.problem);
        multiple_choice[0] = findViewById(R.id.multiple_choice_0);
        multiple_choice[1] = findViewById(R.id.multiple_choice_1);
        multiple_choice[2] = findViewById(R.id.multiple_choice_2);
        multiple_choice[3] = findViewById(R.id.multiple_choice_3);

        //count == 0 일때
        progress_count.setText((count+1) + " / 10");
        problem.setText(problems[count]);

        //multiple choice 에서 어떤 버튼이 정답을 표시할지 랜덤하게 결정
        index_of_solution = (int)(Math.random() * 10) % 4;

        for(int i = 0; i < 4; ++i) {
            if(i == index_of_solution) {
                multiple_choice[i].setText(solution);
            }
            else {
                int randomIndex = (int)(Math.random() * 10) % 10;

                while(isUsedSolutions[randomIndex]) {
                    randomIndex = (int)(Math.random() * 10) % 10;
                }

                isUsedSolutions[randomIndex] = true;
                multiple_choice[i].setText(wrong_solutions[i]);
            }
        }
        setContentView(R.layout.activity_memorize_words);
    }

    public void onClickMultipleChoice0(){
        if(isClicked[0]) {
            multiple_choice[0].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
            isClicked[0] = false;
        }
        else {
            multiple_choice[0].setBackgroundColor(getResources().getColor(R.color.design_default_color_primary_variant));
            isClicked[0] = true;
        }

        multiple_choice[1].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[1] = false;
        multiple_choice[2].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[2] = false;
        multiple_choice[3].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[3] = false;
    }
    public void onClickMultipleChoice1(){
        if(isClicked[1]) {
            multiple_choice[1].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
            isClicked[1] = false;
        }
        else {
            multiple_choice[1].setBackgroundColor(getResources().getColor(R.color.design_default_color_primary_variant));
            isClicked[1] = true;
        }

        multiple_choice[0].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[0] = false;
        multiple_choice[2].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[2] = false;
        multiple_choice[3].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[3] = false;
    }
    public void onClickMultipleChoice2(View view){
        if(isClicked[2]) {
            multiple_choice[1].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
            isClicked[2] = false;
        }
        else {
            multiple_choice[2].setBackgroundColor(getResources().getColor(R.color.design_default_color_primary_variant));
            isClicked[2] = true;
        }

        multiple_choice[0].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[0] = false;
        multiple_choice[1].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[1] = false;
        multiple_choice[3].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[3] = false;
    }
    public void onClickMultipleChoice3(View view){
        if(isClicked[3]) {
            multiple_choice[3].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
            isClicked[3] = false;
        }
        else {
            multiple_choice[3].setBackgroundColor(getResources().getColor(R.color.design_default_color_primary_variant));
            isClicked[3] = true;
        }

        multiple_choice[0].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[0] = false;
        multiple_choice[1].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[1] = false;
        multiple_choice[2].setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
        isClicked[2] = false;
    }
    public void onClickSelect(View view){
        Button b = (Button)view;

        if(b.getText() == "선택"){
            b.setText("다음");

            if(isClicked[index_of_solution]) {
                multiple_choice[index_of_solution].setBackgroundColor(getResources().getColor(R.color.design_default_color_secondary_variant));
            }
            else {
                multiple_choice[index_of_solution].setBackgroundColor(getResources().getColor(R.color.design_default_color_error));
            }
        }
        else{
            //10문제가 모두 끝나
            if(count == 9){
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.example.min", "com.example.min.ClickDictionary");
                intent.setComponent(componentName);
                startActivity(intent);
            }
            else {
                b.setText("선택");
                count += 1;

                for(int i = 0; i < 4; ++i) {
                    isClicked[i] = false;
                    multiple_choice[i].setBackground(getResources().getColor(R.color.design_default_color_background);
                }
                for (int i = 0; i < 10; ++i) {
                    isUsedSolutions[i] = false;
                }

                solution = solutions[count];

                progress_count.setText((count+1) + " / 10");
                problem.setText(problems[count]);

                //multiple choice 에서 어떤 버튼이 정답을 표시할지 랜덤하게 결정
                index_of_solution = (int)(Math.random() * 10) % 4;

                for(int i = 0; i < 4; ++i) {
                    if(i == index_of_solution) {
                        multiple_choice[i].setText(solution);
                    }
                    else {
                        int randomIndex = (int)(Math.random() * 10) % 10;

                        while(isUsedSolutions[randomIndex]) {
                            randomIndex = (int)(Math.random() * 10) % 10;
                        }

                        isUsedSolutions[randomIndex] = true;
                        multiple_choice[i].setText(wrong_solutions[i]);
                    }
                }
            }
        }
    }
}