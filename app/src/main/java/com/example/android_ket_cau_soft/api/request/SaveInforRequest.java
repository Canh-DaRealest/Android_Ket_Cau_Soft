package com.example.android_ket_cau_soft.api.request;


import com.example.android_ket_cau_soft.database.entities.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveInforRequest extends BaseRequest {
    @SerializedName("sdt")
    @Expose
    public String sdt;

    @SerializedName("api_token")
    @Expose
    public String api_token;

    @SerializedName("birthday")
    @Expose
    public String birthday;


    public SaveInforRequest(String email, String api_token, String name, String sdt, String birthday, String password) {
        this.email = email;
        this.api_token = api_token;
        this.name = name;
        this.sdt = sdt;
        this.birthday = birthday;
        this.password = password;

    }
    public SaveInforRequest(User user) {
        this.email = user.getEmail();
        this.api_token = user.getApiToken();
        this.name = user.getName();
        this.sdt = user.getSdt();
        this.birthday = user.getBirthday();
        this.password = user.getPassword();

    }

}
