package com.example.android_ket_cau_soft.model.material_search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NaturalData3 implements Serializable {

    @SerializedName("Vùng gió")
    @Expose
    private String wind_zone;

    @SerializedName("Áp lực gió Wo")
    @Expose
    private String wind_pressure;

   @SerializedName("Gia tốc nền quy đổi")
    @Expose
    private String gia_toc_nen_quy_doi;

 @SerializedName("Gia tốc nền")
    @Expose
    private String gia_toc_nen;


    public NaturalData3(String wind_zone, String wind_pressure, String gia_toc_nen_quy_doi, String gia_toc_nen) {
        this.wind_zone = wind_zone;
        this.wind_pressure = wind_pressure;
        this.gia_toc_nen_quy_doi = gia_toc_nen_quy_doi;
        this.gia_toc_nen = gia_toc_nen;
    }

    @Override
    public String toString() {
        return
                "Vùng gió: " + wind_zone +
                "\nÁp lực gió Wo: " + wind_pressure +
                "\nGia tốc nền quy đổi: " + gia_toc_nen_quy_doi +
                "\nGia tốc nền: " + gia_toc_nen ;
    }

    public NaturalData3() {
    }

    public String getWind_zone() {
        return wind_zone;
    }

    public void setWind_zone(String wind_zone) {
        this.wind_zone = wind_zone;
    }

    public String getWind_pressure() {
        return wind_pressure;
    }

    public void setWind_pressure(String wind_pressure) {
        this.wind_pressure = wind_pressure;
    }

    public String getGia_toc_nen_quy_doi() {
        return gia_toc_nen_quy_doi;
    }

    public void setGia_toc_nen_quy_doi(String gia_toc_nen_quy_doi) {
        this.gia_toc_nen_quy_doi = gia_toc_nen_quy_doi;
    }

    public String getGia_toc_nen() {
        return gia_toc_nen;
    }

    public void setGia_toc_nen(String gia_toc_nen) {
        this.gia_toc_nen = gia_toc_nen;
    }
}
