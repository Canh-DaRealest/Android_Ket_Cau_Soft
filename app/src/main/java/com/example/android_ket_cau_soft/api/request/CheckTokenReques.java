package com.example.android_ket_cau_soft.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckTokenReques extends BaseRequest {
    @SerializedName("api_token")
    @Expose
    public String apiToken;


    public CheckTokenReques(String email, String apiToken) {
        this.apiToken = apiToken;
        this.email = email;
    }
}
