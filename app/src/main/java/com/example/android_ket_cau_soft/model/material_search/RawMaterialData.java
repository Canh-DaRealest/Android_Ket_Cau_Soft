package com.example.android_ket_cau_soft.model.material_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RawMaterialData implements Serializable {


    @SerializedName("address_1")
    @Expose
    private String address_1;

    @SerializedName("data_1")
    @Expose
    private List<RawMaterialData2> rawMaterialData2List;



    public RawMaterialData() {
    }

    @Override
    public String toString() {
        return "RawMaterialData{" +
                "address_1='" + address_1 + '\'' +
                ", rawMaterialData2List=" + rawMaterialData2List +
                '}';
    }

    public RawMaterialData(String address_1, List<RawMaterialData2> liveLoadData1List) {
        this.address_1 = address_1;
        this.rawMaterialData2List = liveLoadData1List;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public List<RawMaterialData2> getLiveLoadData1List() {
        return rawMaterialData2List;
    }

    public void setLiveLoadData1List(List<RawMaterialData2> liveLoadData1List) {
        this.rawMaterialData2List = liveLoadData1List;
    }
}
