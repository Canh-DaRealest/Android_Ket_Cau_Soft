package com.example.android_ket_cau_soft.api.response.material;

import com.example.android_ket_cau_soft.model.material_search.LiveLoadData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetLiveLoadDataResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;


    @SerializedName("msg")
    @Expose
    private String msg;


    @SerializedName("data")
    @Expose
    private List<LiveLoadData> liveLoadDataList;

    public GetLiveLoadDataResponse(Boolean status, String msg,  List<LiveLoadData> dataList) {
        this.status = status;
        this.msg = msg;
        this.liveLoadDataList = dataList;
    }

    public GetLiveLoadDataResponse() {
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

    public  List<LiveLoadData> getData() {
        return liveLoadDataList;
    }

    public void setData( List<LiveLoadData> dataList) {
        this.liveLoadDataList = dataList;
    }
}
