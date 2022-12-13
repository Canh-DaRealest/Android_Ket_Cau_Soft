package com.example.android_ket_cau_soft.viewmodel;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.response.DetailLessonResponse;
import com.example.android_ket_cau_soft.api.response.setting.CheckTokenResponse;
import com.example.android_ket_cau_soft.model.DetailLessonData;
import com.example.android_ket_cau_soft.model.lesson.ItemLesson;


public class DetailLessonVM extends BaseVM {
    private int id;

    private DetailLessonData currentLessonData;
    private ItemLesson itemLesson;


    public ItemLesson getItemLesson() {
        return itemLesson;
    }

    public void setItemLesson(ItemLesson itemLesson) {
        this.itemLesson = itemLesson;
    }


    public DetailLessonData getCurrentLessonData() {
        return currentLessonData;
    }

    public void setCurrentLessonData(DetailLessonData currentLessonData) {
        this.currentLessonData = currentLessonData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateCourseContent(String courseId) {
        getAPIService().getCourseContent(courseId).enqueue(initResponeCallback(EnumStorage.DETAILCOURSE_REQUEST.getEnumValue()));
    }

    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);


        if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
            CheckTokenResponse response = (CheckTokenResponse) body;

            if (response.getStatus()) {
                getDetailOfLesson(itemLesson.getId(), account.getApiToken());
            }
        } else if (key.equals(EnumStorage.GET_DETAIL_LESSON.getEnumValue())) {
            DetailLessonResponse response = (DetailLessonResponse) body;

            if (response.getStatus()) {
                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getDetailLessonData());
            }else{
                onCheckingCallback.onCallbackError(key, response.getMsg());
            }
        }
    }

    @Override
    protected void handleAPIFail(String key, int code, String message) {
        super.handleAPIFail(key, code, message);
        if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())){
        }else if (key.equals(EnumStorage.GET_DETAIL_LESSON.getEnumValue())){
        }

        onCheckingCallback.onCallbackError(key, code + ": " + message);
    }

    public void getDetailOfLesson(int id, String apiToken) {

            getAPIService().getDetailOfLesson(id, apiToken).enqueue(initResponeCallback(EnumStorage.GET_DETAIL_LESSON.getEnumValue()));

    }
}
