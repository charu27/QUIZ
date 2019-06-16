package com.example.android2;

public class QuestionModel {
    public QuestionModel(String questionString ,String answer)
    {
        QuestionString=questionString;
        Answer=answer;
    }
    public String getQuestionstring(){
        return QuestionString;
    }

    public String getanswer(){
        return Answer;}


    private String QuestionString;
    private String Answer;
}
