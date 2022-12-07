package com.example.android_ket_cau_soft.model.lesson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ItemLessonPath implements Serializable {

    @SerializedName("part_name")
    @Expose
    private String partName;

    @SerializedName("lesson_list")
    @Expose
    private List<ItemLesson> itemLessons;

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public List<ItemLesson> getLessonItem() {
        return itemLessons;
    }

    public void setLessonList(List<ItemLesson> itemLessons) {
        this.itemLessons = itemLessons;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "partName='" + partName + '\'' +
                ", lessonItems=" + itemLessons +
                '}';
    }
}
