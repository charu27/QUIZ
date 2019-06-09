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
    public void setQuestionstring(String questionString){
        QuestionString=questionString;
    }
    public String getanswer(){
        return Answer;}

    public void setanswer(String answer){
        Answer=answer;
    }
    private String QuestionString;
    private String Answer;
}
