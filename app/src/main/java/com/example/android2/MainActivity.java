package com.example.android2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    TextView questionlabel, questionCountLabel, scoreLabel;
    EditText answerEdit;
    Button submitButton;
    ProgressBar progressBar;

    ArrayList<QuestionModel> questionModelArrayList;
    int currentPosition = 0;
    int numberofcorrectans = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionCountLabel = findViewById(R.id.noquestion);
        questionlabel = findViewById(R.id.question);
        scoreLabel = findViewById(R.id.score);
        answerEdit = findViewById(R.id.answer);
        submitButton = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progress);
        questionModelArrayList = new ArrayList<>();
        setupquestion();
        setdata();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkanswer();
            }
        });
        answerEdit.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                Log.e("event.getAction()", event.getAction() + "");
                Log.e("event.keyCode()", keyCode + "");
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    checkanswer();
                    return true;
                }
                return false;

            }
        });
    }

    public void checkanswer() {
        String answerstring = answerEdit.getText().toString().trim();

        if (answerstring.equalsIgnoreCase(questionModelArrayList.get(currentPosition).getanswer())) {
            numberofcorrectans++;


            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("Right Asswer")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            currentPosition++;

                            setdata();
                            answerEdit.setText("");
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();


        } else {

            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Wrong Answer")
                    .setContentText("The right answer is : " + questionModelArrayList.get(currentPosition).getanswer())
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();

                            currentPosition++;

                            setdata();
                            answerEdit.setText("");
                        }
                    })
                    .show();
            int x = ((currentPosition + 1) * 100) / questionModelArrayList.size();
            progressBar.setProgress(x);
        }
    }

    public void setupquestion() {

        questionModelArrayList.add(new QuestionModel("What is 1+3 ?", "4"));
        questionModelArrayList.add(new QuestionModel("What is 8*8 ?", "64"));
        questionModelArrayList.add(new QuestionModel("What is 9-12 ?", "-3"));
        questionModelArrayList.add(new QuestionModel("What is 12/3 ?", "4"));
        questionModelArrayList.add(new QuestionModel("What is my name ?", "CHARU"));

    }

    public void setdata() {


        if (questionModelArrayList.size() > currentPosition) {

            questionlabel.setText(questionModelArrayList.get(currentPosition).getQuestionstring());

            scoreLabel.setText("Score" + numberofcorrectans + "/" + questionModelArrayList.size());
            questionCountLabel.setText("Question No : " + (currentPosition + 1));


        } else {


            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("You have successfully completed the quiz")
                    .setContentText("Your score is : " + numberofcorrectans + "/" + questionModelArrayList.size())
                    .setConfirmText("Restart")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            sDialog.dismissWithAnimation();
                            currentPosition = 0;
                            numberofcorrectans = 0;
                            progressBar.setProgress(0);
                            setdata();
                        }
                    })
                    .setCancelText("Close")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            sDialog.dismissWithAnimation();
                            finish();
                        }
                    })
                    .show();

        }
    }

}