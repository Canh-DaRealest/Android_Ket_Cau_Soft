package com.example.android_ket_cau_soft.api.response.họmefrgres.notification;

import com.example.android_ket_cau_soft.api.response.BaseResponse;

public class MarkAsReadedResponse extends BaseResponse {
    public MarkAsReadedResponse(Boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
