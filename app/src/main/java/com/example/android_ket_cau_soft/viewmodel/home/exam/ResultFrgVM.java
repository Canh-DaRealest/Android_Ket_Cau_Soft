package com.example.android_ket_cau_soft.viewmodel.home.exam;

import com.example.android_ket_cau_soft.model.exam.QuestionData;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

import java.util.ArrayList;
import java.util.List;

public class ResultFrgVM extends BaseVM {

    private int score = 0;
    private int totalScore = 0;

    private List<QuestionData> falseQuestionList =new ArrayList<>();

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public List<QuestionData> getFalseQuestionList() {
        return falseQuestionList;
    }

    public void setFalseQuestionList(List<QuestionData> falseQuestionList) {
        this.falseQuestionList = falseQuestionList;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    //danh sach cau hoi
    private List<QuestionData> questionDataList;

    //list dap an nguoi dung chon
    private List<Integer> chooseAnswerList = new ArrayList<>();

    public List<Integer> getChooseAnswerList() {
        return chooseAnswerList;
    }

    public void setChooseAnswerList(List<Integer> chooseAnswerList) {
        this.chooseAnswerList = chooseAnswerList;
    }

    public void updateChooseAnswerList(int answerPosition) {
        this.chooseAnswerList.add(answerPosition);
    }


    public List<QuestionData> getQuestionDataList() {
        return questionDataList;
    }

    public void setQuestionDataList(List<QuestionData> questionDataList) {
        this.questionDataList = questionDataList;
    }

    public void setListQuestionData(List<QuestionData> listData) {
        this.questionDataList = listData;
    }

    public void updateScore() {
        totalScore = questionDataList.size() * 4;

        for (int i = 0; i < chooseAnswerList.size(); i++) {
            if (chooseAnswerList.get(i) == questionDataList.get(i).getTrueCase()) {
                score += 4;
            } else {
                falseQuestionList.add(questionDataList.get(i));
            }
        }

    }

}
