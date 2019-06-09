package com.example.android2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    TextView questionlabel ,questionCountLabel ,scoreLabel;
    EditText answerEdit;
    Button submitButton;
    ProgressBar progressBar;

    ArrayList<QuestionModel>questionModelArrayList;
int currentPosition=0;
int numberofcorrectans =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionCountLabel=findViewById(R.id.noquestion);
        questionlabel=findViewById(R.id.question);
        scoreLabel=findViewById(R.id.score);
        answerEdit=findViewById(R.id.answer);
        submitButton=findViewById(R.id.submit);
        progressBar=findViewById(R.id.progress);
        questionModelArrayList=new ArrayList<>();
        setupquestion();
        setdata();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkanswer();
            }
        });

    }

    public void checkanswer(){

        String answerstring =answerEdit.getText().toString().trim();
        if(answerstring.equalsIgnoreCase(questionModelArrayList.get(currentPosition).getanswer())){
           new SweetAlertDialog(MainActivity.this,SweetAlertDialog.SUCCESS_TYPE);
              .setTiTleText("Good job");
              .setContentText("Right Answer");
              .setConfirmClickListener(new SweetAlertDialog.OnCancelListener(){

            }
        }
        else{
            Log.e("answer","Wrong");
            currentPosition++;
            setdata();
            answerEdit.setText("");
        }
        int x =((currentPosition+1)*100)/questionModelArrayList.size();
        progressBar.setProgress(x);
    }

public void setupquestion(){

    questionModelArrayList.add(new QuestionModel("What is 1+3 ?","4"));
    questionModelArrayList.add(new QuestionModel("What is 8*8 ?","64"));
    questionModelArrayList.add(new QuestionModel("What is 9-12 ?","-3"));
    questionModelArrayList.add(new QuestionModel("What is 12/3 ?","4"));
    questionModelArrayList.add(new QuestionModel( "What is my name ?","CHARU" ));

    }
    public void setdata(){
        questionlabel.setText(questionModelArrayList.get(currentPosition).getQuestionstring());
        questionCountLabel.setText("Question NO."+currentPosition+1);
        scoreLabel.setText("Score:"numberofcorrectans+"/"+questionModelArrayList.size());
    }
}