package com.example.android_ket_cau_soft.model.exam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuestionData implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer questionId;
    @SerializedName("cau_hoi")
    @Expose
    private String question;
    @SerializedName("dap_an")
    @Expose
    private List<String> answerList;
    @SerializedName("dap_an_dung")
    @Expose
    private Integer trueCase;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswer(List<String> answerList) {
        this.answerList = answerList;
    }

    public Integer getTrueCase() {
        return trueCase;
    }

    public void setTrueCase(Integer trueCase) {
        this.trueCase = trueCase;
    }
}
