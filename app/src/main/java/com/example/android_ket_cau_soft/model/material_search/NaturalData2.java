package com.example.android_ket_cau_soft.model.material_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NaturalData2 implements Serializable {

    @SerializedName("address_2")
    @Expose
    private String address_2;


    @SerializedName("data_2")
    @Expose
    private NaturalData3 data3;

    public NaturalData2(String address_2, NaturalData3 data3) {
        this.address_2 = address_2;
        this.data3 = data3;
    }

    public NaturalData3 getData3() {
        return data3;
    }

    public void setData3(NaturalData3 data3) {
        this.data3 = data3;
    }

    @Override
    public String toString() {
        return "NaturalData2{" +
                "address_2='" + address_2 + '\'' +
                ", data3=" + data3 +
                '}';
    }

    public NaturalData2() {
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

}
