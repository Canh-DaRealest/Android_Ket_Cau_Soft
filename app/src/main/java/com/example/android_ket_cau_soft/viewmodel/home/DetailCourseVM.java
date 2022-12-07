package com.example.android_ket_cau_soft.viewmodel.home;


import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.model.lesson.LessonData;
import com.example.android_ket_cau_soft.model.lesson.LessonResponse;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

public class DetailCourseVM extends BaseVM {


    private LessonData lessonData;

    public LessonData getLessonData() {
        return lessonData;
    }

    public void updateCourseContent(String courseId) {
        getAPIService().getCourseContent(courseId).enqueue(initResponeCallback(EnumStorage.DETAILCOURSE_REQUEST.getEnumValue()));
    }

    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);

        if (key.equals(EnumStorage.DETAILCOURSE_REQUEST.getEnumValue())) {
            LessonResponse response = (LessonResponse) body;

            if (response.getStatus() && response.getData() != null) {

                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getData());
            }
        }
    }

    public void setLessonData(LessonData lessonData) {
        this.lessonData = lessonData;
    }
}
