package com.example.android_ket_cau_soft.model.lesson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LessonData implements Serializable {


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mentor")
    @Expose
    private String mentor;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("rated")
    @Expose
    private Integer rated;

    @SerializedName("time")
    @Expose
    private Integer time;

    @SerializedName("lesson_num")
    @Expose
    private Integer lessonNum;

    @SerializedName("info")
    @Expose
    private String info;

    @SerializedName("content")
    @Expose
    private String content;


    @SerializedName("lessons")
    @Expose
    private List<ItemLessonPath> itemLessonPathList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRated() {
        return rated;
    }

    public void setRated(Integer rated) {
        this.rated = rated;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ItemLessonPath> getLessonList() {
        return itemLessonPathList;
    }

    @Override
    public String toString() {
        return "LessonData{" +
                "name='" + name + '\'' +
                ", mentor='" + mentor + '\'' +
                ", image='" + image + '\'' +
                ", rated=" + rated +
                ", time=" + time +
                ", lessonNum=" + lessonNum +
                ", info='" + info + '\'' +
                ", content='" + content + '\'' +
                ", lessonList=" + itemLessonPathList +
                '}';
    }

    public void setLessonList(List<ItemLessonPath> itemLessonPathList) {
        this.itemLessonPathList = itemLessonPathList;
    }
}
