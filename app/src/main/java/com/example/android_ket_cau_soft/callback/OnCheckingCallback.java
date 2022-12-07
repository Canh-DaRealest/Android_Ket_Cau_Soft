package com.example.android_ket_cau_soft.callback;

public interface OnCheckingCallback {
    void onCallbackError(String key, String msg);
    void onCallbackSuccess(String key, String msg,Object data);
}
