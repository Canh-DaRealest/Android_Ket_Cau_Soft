package com.example.android_ket_cau_soft.api.response.material;

import com.example.android_ket_cau_soft.model.material_search.LiveLoadData;
import com.example.android_ket_cau_soft.model.material_search.NaturalData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetLNaturalDataResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;


    @SerializedName("msg")
    @Expose
    private String msg;


    @SerializedName("data")
    @Expose
    private List<NaturalData> dataList;

    public GetLNaturalDataResponse(Boolean status, String msg, List<NaturalData> dataList) {
        this.status = status;
        this.msg = msg;
        this.dataList = dataList;
    }

    public GetLNaturalDataResponse() {
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

    public List<NaturalData> getDataList() {
        return dataList;
    }

    public void setDataList(List<NaturalData> dataList) {
        this.dataList = dataList;
    }
}
