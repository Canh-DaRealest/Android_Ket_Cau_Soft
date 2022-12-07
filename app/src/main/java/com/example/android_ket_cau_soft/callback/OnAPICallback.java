package com.example.android_ket_cau_soft.callback;

public interface OnAPICallback {

    void handleAPISuccess(String key, String msg);

    void handleAPIFail(String key,int code, String msg);

}
