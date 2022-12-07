package com.example.android_ket_cau_soft.api.response.họmefrgres.setting;

import com.example.android_ket_cau_soft.api.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CheckTokenResponse extends BaseResponse {

    @SerializedName("code")
    @Expose
    public Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CheckTokenResponse(Boolean status, String msg) {
        this.status = status;
        this.msg = msg;

    }

    public CheckTokenResponse(Boolean status, String msg, Integer code) {
        this.status = status;
        this.msg = msg;
        this.code = code;
    }
}
