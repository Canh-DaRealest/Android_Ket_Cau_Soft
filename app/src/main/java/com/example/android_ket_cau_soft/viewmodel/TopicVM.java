package com.example.android_ket_cau_soft.viewmodel;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.response.hot_courses.CourseResponse;
import com.example.android_ket_cau_soft.api.response.setting.CheckTokenResponse;
import com.example.android_ket_cau_soft.api.response.topic.GetEduTopicResponse;

public class TopicVM extends BaseVM {
    private boolean state = false;

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void getTopic() {
        getAPIService().getEduTopic().enqueue(initResponeCallback(EnumStorage.GET_TOPIC.getEnumValue()));
    }

    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);

        if (key.equals(EnumStorage.GET_TOPIC.getEnumValue())) {
            GetEduTopicResponse data = (GetEduTopicResponse) body;

            onCheckingCallback.onCallbackSuccess(key, data.getMsg(), data.getItemTopicList());

        } else if (key.equals(EnumStorage.GET_DETAIL_BY_TOPIC.getEnumValue())) {
            CourseResponse data = (CourseResponse) body;

            onCheckingCallback.onCallbackSuccess(key, data.getMsg(), data.getData());

        } else if (key.equals(EnumStorage.GET_MY_COURSE.getEnumValue())) {
            CourseResponse data = (CourseResponse) body;

            onCheckingCallback.onCallbackSuccess(key, data.getMsg(), data.getData());

        } else if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
            CheckTokenResponse data = (CheckTokenResponse) body;
            onCheckingCallback.onCallbackSuccess(key, data.getMsg(), null);

        }
    }

    @Override
    protected void handleAPIFail(String key, int code, String message) {
        super.handleAPIFail(key, code, message);
        onCheckingCallback.onCallbackError(key, code + ": " + message);

    }

    public void getCourseByTopic(int id) {
        getAPIService().getDetailCourseByTopic(id).enqueue(initResponeCallback(EnumStorage.GET_DETAIL_BY_TOPIC.getEnumValue()));

    }

    public void getMyCourse() {
        updateAccountFromDB();
        getAPIService().getMyCourse(account.getApiToken()).enqueue(initResponeCallback(EnumStorage.GET_MY_COURSE.getEnumValue()));

    }
}
