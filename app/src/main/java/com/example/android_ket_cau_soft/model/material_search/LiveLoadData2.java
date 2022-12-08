package com.example.android_ket_cau_soft.model.material_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LiveLoadData2 implements Serializable {

    @SerializedName("address_2")
    @Expose
    private String address_2;


    @SerializedName("data_2")
    @Expose
    private LiveLoadData3 data_2;

    public LiveLoadData2(String address_2, LiveLoadData3 data_2) {
        this.address_2 = address_2;
        this.data_2 = data_2;
    }

    public LiveLoadData2() {
    }

    @Override
    public String toString() {
        return "LiveLoadData2{" +
                "address_2='" + address_2 + '\'' +
                ", data_2=" + data_2 +
                '}';
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public LiveLoadData3 getData_2() {
        return data_2;
    }

    public void setData_2(LiveLoadData3 data_2) {
        this.data_2 = data_2;
    }
}
