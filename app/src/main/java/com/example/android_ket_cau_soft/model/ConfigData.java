package com.example.android_ket_cau_soft.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConfigData implements Serializable {


    @SerializedName("zalo")
    @Expose
    private String zalo;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("facegroup")
    @Expose
    private String facegroup;
    @SerializedName("fanpage")
    @Expose
    private String fanpage;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("main_cover")
    @Expose
    private String mainCover;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("vip_cost")
    @Expose
    private Integer vipCost;
    @SerializedName("app_in_review")
    @Expose
    private Boolean appInReview;

    public String getZalo() {
        return zalo;
    }

    public void setZalo(String zalo) {
        this.zalo = zalo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacegroup() {
        return facegroup;
    }

    public void setFacegroup(String facegroup) {
        this.facegroup = facegroup;
    }

    public String getFanpage() {
        return fanpage;
    }

    public void setFanpage(String fanpage) {
        this.fanpage = fanpage;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMainCover() {
        return mainCover;
    }

    public void setMainCover(String mainCover) {
        this.mainCover = mainCover;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getVipCost() {
        return vipCost;
    }

    public void setVipCost(Integer vipCost) {
        this.vipCost = vipCost;
    }

    public Boolean getAppInReview() {
        return appInReview;
    }

    public void setAppInReview(Boolean appInReview) {
        this.appInReview = appInReview;
    }
}
