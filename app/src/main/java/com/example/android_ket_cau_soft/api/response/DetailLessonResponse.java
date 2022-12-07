package com.example.android_ket_cau_soft.api.response;


import com.example.android_ket_cau_soft.model.DetailLessonData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailLessonResponse implements Serializable {


    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;


    @SerializedName("data")
    @Expose
    private DetailLessonData detailLessonData;

    public DetailLessonResponse(Boolean status, String msg, DetailLessonData detailLessonData) {
        this.status = status;
        this.msg = msg;
        this.detailLessonData = detailLessonData;
    }

    public DetailLessonResponse() {
    }

    public DetailLessonData getDetailLessonData() {
        return detailLessonData;
    }

    public void setDetailLessonData(DetailLessonData detailLessonData) {
        this.detailLessonData = detailLessonData;
    }



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
}
