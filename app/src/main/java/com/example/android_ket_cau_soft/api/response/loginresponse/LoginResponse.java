package com.example.android_ket_cau_soft.api.response.loginresponse;



import com.example.android_ket_cau_soft.api.response.BaseResponse;
import com.example.android_ket_cau_soft.model.UserData;

import java.io.Serializable;

public class LoginResponse extends BaseResponse implements Serializable {


    public LoginResponse(Boolean status, String msg) {
        this.status = status;
        this.msg = msg;

    }

    public LoginResponse(Boolean status, UserData userData) {
        this.status = status;
        this.userData = userData;
    }

    public UserData getData() {
        return   userData;
    }

}
