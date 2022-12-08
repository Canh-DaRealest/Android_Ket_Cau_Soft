package com.example.android_ket_cau_soft.model.material_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RawMaterialData3 implements Serializable {

    @SerializedName("Rb")
    @Expose
    private String rb;

    @SerializedName("Rbt")
    @Expose
    private String rbt;

    @SerializedName("E")
    @Expose
    private String e;


    public RawMaterialData3(String rb, String rbt, String e) {
        this.rb = rb;
        this.rbt = rbt;
        this.e = e;
    }

    @Override
    public String toString() {
        return
                "Rb: " + rb +
                        "\nRbt: " + rbt +
                        "\nE: " + e;
    }

    public RawMaterialData3() {
    }

    public String getRb() {
        return rb;
    }

    public void setRb(String rb) {
        this.rb = rb;
    }

    public String getRbt() {
        return rbt;
    }

    public void setRbt(String rbt) {
        this.rbt = rbt;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}
