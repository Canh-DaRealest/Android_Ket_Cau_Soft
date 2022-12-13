package com.example.android_ket_cau_soft.api.response.hot_courses;

import com.example.android_ket_cau_soft.model.CourseData;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CourseResponse implements Serializable {
    @SerializedName("status")
    private boolean status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private List<CourseData> data;

    public CourseResponse(boolean status, String msg, List<CourseData> listData) {

        this.status = status;
        this.msg = msg;
        this.data = listData;
    }


    public void setData(List<CourseData> data) {
        this.data = data;
    }

    public List<CourseData> getData() {
        return data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
