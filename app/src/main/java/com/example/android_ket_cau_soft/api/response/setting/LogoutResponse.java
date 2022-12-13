package com.example.android_ket_cau_soft.api.response.setting;


import com.example.android_ket_cau_soft.api.response.BaseResponse;

public class LogoutResponse extends BaseResponse {


    public LogoutResponse(Boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
