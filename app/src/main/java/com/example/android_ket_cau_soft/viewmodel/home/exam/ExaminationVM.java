package com.example.android_ket_cau_soft.viewmodel.home.exam;

import android.widget.RadioButton;


import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.response.exam.GetQuestionResponse;
import com.example.android_ket_cau_soft.model.exam.QuestionData;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

import java.util.ArrayList;
import java.util.List;

public class ExaminationVM extends BaseVM {
    private List<RadioButton> radioButtonList = new ArrayList<>();

    public List<RadioButton> getRadioButtonList() {
        return radioButtonList;
    }

    public void setRadioButtonList(RadioButton radioButton) {
        this.radioButtonList.add(radioButton);
    }



    //loai bai kiem tra
    private int examType;
    //cau hoi hien tai
    private int currentQuestion = 0;

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
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

    //tong so cau hoi
    private int questionCount;

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }


    public List<QuestionData>

    getQuestionDataList() {
        return questionDataList;
    }

    public void setQuestionDataList(List<QuestionData> questionDataList) {
        this.questionDataList = questionDataList;
    }

    public int getExamType() {
        return examType;
    }

    public void setExamType(int examType) {
        this.examType = examType;
    }


    public void getExamQuestion(int examType) {
        getAPIService().getExamQuestion(examType).enqueue(initResponeCallback(EnumStorage.GET_EXAM_QUESTION.getEnumValue()));
    }

    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);

        if (key.equals(EnumStorage.GET_EXAM_QUESTION.getEnumValue())) {
            GetQuestionResponse response = (GetQuestionResponse) body;
            if (response.getStatus()) {
                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getQuestionDataList());
            } else {
                onCheckingCallback.onCallbackError(key, response.getMsg());
            }

        }
    }

    @Override
    protected void handleAPIFail(String key, int code, String message) {
        super.handleAPIFail(key, code, message);
        onCheckingCallback.onCallbackError(key, code + ": " + message);
    }

    public void setListQuestionData(List<QuestionData> listData) {
        this.questionDataList = listData;
    }




}
