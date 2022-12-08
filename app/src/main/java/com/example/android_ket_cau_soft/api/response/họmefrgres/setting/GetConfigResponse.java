package com.example.android_ket_cau_soft.api.response.họmefrgres.setting;


import com.example.android_ket_cau_soft.api.response.BaseResponse;
import com.example.android_ket_cau_soft.model.ConfigData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetConfigResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Boolean status;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("data")
    @Expose
    private ConfigData configData;


    public GetConfigResponse(Boolean status, String msg, ConfigData configData) {
        this.status = status;
        this.msg = msg;
        this.configData = configData;
    }

    public ConfigData getConfigData() {
        return configData;
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

    public void setConfigData(ConfigData configData) {
        this.configData = configData;
    }
}
