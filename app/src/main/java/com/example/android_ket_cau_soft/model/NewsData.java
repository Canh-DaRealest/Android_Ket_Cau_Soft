package com.example.android_ket_cau_soft.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsData implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("link")
    private String link;
    @SerializedName("time")
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
