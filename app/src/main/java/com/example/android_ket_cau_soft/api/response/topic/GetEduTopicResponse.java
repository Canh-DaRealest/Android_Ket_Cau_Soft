package com.example.android_ket_cau_soft.api.response.topic;


import com.example.android_ket_cau_soft.model.ItemTopic;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetEduTopicResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("data")
    @Expose
    private List<ItemTopic> itemTopicList;

    public GetEduTopicResponse(Boolean status, String msg, List<ItemTopic> itemTopicList) {
        this.status = status;
        this.msg = msg;
        this.itemTopicList = itemTopicList;
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

    public List<ItemTopic> getItemTopicList() {
        return itemTopicList;
    }

    public void setItemTopicList(List<ItemTopic> itemTopicList) {
        this.itemTopicList = itemTopicList;
    }
}
