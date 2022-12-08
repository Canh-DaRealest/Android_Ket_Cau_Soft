package com.example.android_ket_cau_soft.api.response.material;

import com.example.android_ket_cau_soft.model.material_search.LiveLoadData;
import com.example.android_ket_cau_soft.model.material_search.RawMaterialData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetRawMaterialDataResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;


    @SerializedName("msg")
    @Expose
    private String msg;


    @SerializedName("data")
    @Expose
    private List<RawMaterialData> data;


    public GetRawMaterialDataResponse() {
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

    public GetRawMaterialDataResponse(Boolean status, String msg, List<RawMaterialData> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public List<RawMaterialData> getData() {
        return data;
    }

    public void setData(List<RawMaterialData> data) {
        this.data = data;
    }
}
