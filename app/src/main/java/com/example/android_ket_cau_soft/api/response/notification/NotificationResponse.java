package com.example.android_ket_cau_soft.api.response.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NotificationResponse implements Serializable {


    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;


    @SerializedName("unread_num")
    @Expose
    private Integer unread_num;


    @SerializedName("data")
    @Expose
    private List<NotifiData> notifiDataList;

    public Integer getUnread_num() {
        return unread_num;
    }

    public void setUnread_num(Integer unread_num) {
        this.unread_num = unread_num;
    }

    public List<NotifiData> getNotifiDataList() {
        return notifiDataList;
    }

    public void setNotifiDataList(List<NotifiData> notifiDataList) {
        this.notifiDataList = notifiDataList;
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
