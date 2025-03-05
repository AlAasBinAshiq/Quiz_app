package com.example.quiz_app;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextview;
    TextView totalquestionsTextview;
    Button AnsA,AnsB,AnsC,AnsD;
    Button Submit;

    int score;
    int TotalQuestions=QuestionAnswer.question.length;
    int currentquestionindex=0;
    String selectedAnswer="";



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        totalquestionsTextview=findViewById(R.id.Total_question);
        questionTextview=findViewById(R.id.question);
        AnsA =  findViewById(R.id.ans_a);
        AnsB =  findViewById(R.id.ans_b);
        AnsC =  findViewById(R.id.ans_c);
        AnsD =  findViewById(R.id.ans_d);
        Submit = findViewById(R.id.submit);


        AnsA.setOnClickListener(this);
        AnsB.setOnClickListener(this);
        AnsC.setOnClickListener(this);
        AnsD.setOnClickListener(this);
        Submit.setOnClickListener(this);
        totalquestionsTextview.setText("Total Questions:"+TotalQuestions);

        loadNewQuestion();
    }
    private void loadNewQuestion(){
        if(currentquestionindex==TotalQuestions){
            finishQuiz();
            return;
        }
        questionTextview.setText(QuestionAnswer.question[currentquestionindex]);
        AnsA.setText(QuestionAnswer.choices[currentquestionindex][0]);
        AnsB.setText(QuestionAnswer.choices[currentquestionindex][1]);
        AnsC.setText(QuestionAnswer.choices[currentquestionindex][2]);
        AnsD.setText(QuestionAnswer.choices[currentquestionindex][3]);

        selectedAnswer="";

    }
    private void finishQuiz(){
        String passstatus;
        if(score>=TotalQuestions+0.6){
            passstatus="Passed";
        }
        else{
            passstatus="failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passstatus)
                .setMessage("Your score is"+score+"Out of"+TotalQuestions)
                .setPositiveButton("Restart",((dialog, i) -> restartQuiz() ))
                .setCancelable(false)
                .show();
    }
    private void restartQuiz(){
        score=0;
        currentquestionindex=0;
        loadNewQuestion();
    }
    @Override
    public void onClick(View view){
        AnsA.setBackgroundColor(Color.WHITE);
        AnsB.setBackgroundColor(Color.WHITE);
        AnsC.setBackgroundColor(Color.WHITE);
        AnsD.setBackgroundColor(Color.WHITE);
        Button clickedButton=(Button) view;

        if(clickedButton.getId()==R.id.submit){
            if(!selectedAnswer.isEmpty()){
                if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentquestionindex])){
                    score++;
                }else{
                    clickedButton.setBackgroundColor(Color.MAGENTA);
                }
                currentquestionindex++;
                loadNewQuestion();
            }else {

            }
            }
        else{
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);
        }

    }

}