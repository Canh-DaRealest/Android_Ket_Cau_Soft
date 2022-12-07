package com.example.android_ket_cau_soft.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CourseData implements Serializable {

//     "id": 26,
//             "name": "Workshop: Nhập môn lập trình VB.Net cho Kết Cấu #2",
//             "mentor": "Hồ Việt Hùng",
//             "image": "https://ketcausoft.com/images/upload/2/cover20211211.png",
//             "rated": 129,
//             "info": "Free"

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("mentor")
    private String mentor;

    @SerializedName("image")
    private String imageLink;

    @SerializedName("rated")
    private Integer rated;

    @SerializedName("info")
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Integer getRated() {
        return rated;
    }

    public void setRated(Integer rated) {
        this.rated = rated;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "HotCourseData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mentor='" + mentor + '\'' +
                ", image='" + imageLink + '\'' +
                ", rated=" + rated +
                ", info='" + info + '\'' +
                '}';
    }
}
