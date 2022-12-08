package com.example.android_ket_cau_soft.model.material_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LiveLoadData3 implements Serializable {

    @SerializedName("Toàn phần")
    @Expose
    private String toan_phan;


    @SerializedName("Dài hạn")
    @Expose
    private String  dai_han;



    public LiveLoadData3() {
    }

    public String getToan_phan() {
        return toan_phan;
    }

    public void setToan_phan(String toan_phan) {
        this.toan_phan = toan_phan;
    }

    public String getDai_han() {
        return dai_han;
    }

    public void setDai_han(String dai_han) {
        this.dai_han = dai_han;
    }

    @Override
    public String toString() {
        return
                "Toàn phần: " + toan_phan  +
                "\n Dài hạn: " + dai_han ;
    }
}
