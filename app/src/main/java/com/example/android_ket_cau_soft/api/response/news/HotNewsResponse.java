package com.example.android_ket_cau_soft.api.response.news;


import com.example.android_ket_cau_soft.model.NewsData;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HotNewsResponse implements Serializable {
    @SerializedName("status")
    private boolean status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private NewsData[] data;

    public HotNewsResponse(boolean status, String msg, NewsData[] data) {

        this.status = status;
        this.msg = msg;
        this.data = data;
    }


    public void setData(NewsData[] data) {
        this.data = data;
    }

    public NewsData[] getData() {
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
