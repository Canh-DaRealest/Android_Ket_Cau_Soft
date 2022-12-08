package com.example.android_ket_cau_soft.model.material_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NaturalData implements Serializable {


    @SerializedName("address_1")
    @Expose
    private String address_1;

    @SerializedName("data_1")
    @Expose
    private List<NaturalData2> naturalData2List;

    @Override
    public String toString() {
        return "NaturalData{" +
                "address_1='" + address_1 + '\'' +
                ", naturalData2List=" + naturalData2List +
                '}';
    }

    public NaturalData() {
    }

    public NaturalData(String address_1, List<NaturalData2> naturalData2List) {
        this.address_1 = address_1;
        this.naturalData2List = naturalData2List;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public List<NaturalData2> getNaturalData1List() {
        return naturalData2List;
    }

    public void setNaturalData1List(List<NaturalData2> naturalData2List) {
        this.naturalData2List = naturalData2List;
    }
}
