package com.example.android_ket_cau_soft.model;


import com.example.android_ket_cau_soft.model.lesson.ItemLesson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DetailLessonData implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("content")
    private String content;


    @SerializedName("clip_link")
    private String clip_link;

    @SerializedName("clip_cover")
    private String clip_cover;

    @SerializedName("clip_time")
    private Integer clip_time;

    @SerializedName("next_id")
    private Integer next_id;

    @SerializedName("pre_id")
    private Integer pre_id;

    @SerializedName("lessons")
    private List<ItemLesson> itemLessonList;


    public DetailLessonData(String name, String content, String clip_link,
                            String clip_cover, Integer clip_time, Integer next_id, Integer pre_id, List<ItemLesson> itemLessonList) {
        this.name = name;
        this.content = content;
        this.clip_link = clip_link;
        this.clip_cover = clip_cover;
        this.clip_time = clip_time;
        this.next_id = next_id;
        this.pre_id = pre_id;
        this.itemLessonList = itemLessonList;
    }

    public DetailLessonData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClip_link() {
        return clip_link;
    }

    public void setClip_link(String clip_link) {
        this.clip_link = clip_link;
    }

    public String getClip_cover() {
        return clip_cover;
    }

    public void setClip_cover(String clip_cover) {
        this.clip_cover = clip_cover;
    }

    public Integer getClip_time() {
        return clip_time;
    }

    public void setClip_time(Integer clip_time) {
        this.clip_time = clip_time;
    }

    public Integer getNext_id() {
        return next_id;
    }

    public void setNext_id(Integer next_id) {
        this.next_id = next_id;
    }

    public Integer getPre_id() {
        return pre_id;
    }

    public void setPre_id(Integer pre_id) {
        this.pre_id = pre_id;
    }

    public List<ItemLesson> getItemLessonList() {
        return itemLessonList;
    }

    public void setItemLessonList(List<ItemLesson> itemLessonList) {
        this.itemLessonList = itemLessonList;
    }

    @Override
    public String toString() {
        return "DetailLessonData{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", clip_link='" + clip_link + '\'' +
                ", clip_cover='" + clip_cover + '\'' +
                ", clip_time=" + clip_time +
                ", next_id=" + next_id +
                ", pre_id=" + pre_id +
                ", itemLessonList=" + itemLessonList +
                '}';
    }
}
