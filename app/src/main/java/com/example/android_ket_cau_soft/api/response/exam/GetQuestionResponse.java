package com.example.android_ket_cau_soft.api.response.exam;


import com.example.android_ket_cau_soft.model.exam.QuestionData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetQuestionResponse implements Serializable {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<QuestionData> questionDataList;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<QuestionData> getQuestionDataList() {
        return questionDataList;
    }

    public void setQuestionDataList(List<QuestionData> questionDataList) {
        this.questionDataList = questionDataList;
    }

}
