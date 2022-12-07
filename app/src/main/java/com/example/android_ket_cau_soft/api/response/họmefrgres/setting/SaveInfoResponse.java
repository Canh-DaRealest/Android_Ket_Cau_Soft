package com.example.android_ket_cau_soft.api.response.họmefrgres.setting;

import com.example.android_ket_cau_soft.api.response.BaseResponse;
import com.example.android_ket_cau_soft.model.UserData;

public class SaveInfoResponse extends BaseResponse {


    public SaveInfoResponse(Boolean status, String msg, UserData userData) {
        this.userData = userData;
        this.status = status;
        this.msg = msg;
    }
}
