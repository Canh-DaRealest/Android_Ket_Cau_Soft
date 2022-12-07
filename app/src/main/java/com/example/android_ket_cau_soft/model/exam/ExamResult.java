package com.example.android_ket_cau_soft.model.exam;

import java.util.List;

public class ExamResult {

    private List<Integer> chooseAnswerList ;
    private List<QuestionData> questionDataList;

    public ExamResult(List<Integer> chooseAnswerList, List<QuestionData> questionDataList) {
        this.chooseAnswerList = chooseAnswerList;
        this.questionDataList = questionDataList;
    }

    public List<Integer> getChooseAnswerList() {
        return chooseAnswerList;
    }

    public void setChooseAnswerList(List<Integer> chooseAnswerList) {
        this.chooseAnswerList = chooseAnswerList;
    }

    public List<QuestionData> getQuestionDataList() {
        return questionDataList;
    }

    public void setQuestionDataList(List<QuestionData> questionDataList) {
        this.questionDataList = questionDataList;
    }
}
