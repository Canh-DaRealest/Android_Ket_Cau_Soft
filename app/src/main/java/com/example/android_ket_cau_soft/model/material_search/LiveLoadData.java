package com.example.android_ket_cau_soft.model.material_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LiveLoadData implements Serializable {


    @SerializedName("address_1")
    @Expose
    private String address_1;

    @SerializedName("data_1")
    @Expose
    private List<LiveLoadData2> liveLoadData2List;

    @Override
    public String toString() {
        return "LiveLoadData{" +
                "address_1='" + address_1 + '\'' +
                ", liveLoadData2List=" + liveLoadData2List +
                '}';
    }

    public LiveLoadData() {
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public LiveLoadData(String address_1, List<LiveLoadData2> liveLoadData2List) {
        this.address_1 = address_1;
        this.liveLoadData2List = liveLoadData2List;
    }

    public List<LiveLoadData2> getLiveLoadData2List() {
        return liveLoadData2List;
    }

    public void setLiveLoadData2List(List<LiveLoadData2> liveLoadData2List) {
        this.liveLoadData2List = liveLoadData2List;
    }
}
