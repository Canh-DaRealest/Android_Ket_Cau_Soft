package com.example.android_ket_cau_soft.viewmodel.home;

import android.util.Log;


import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.response.họmefrgres.hot_courses.CourseResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.news.HotNewsResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.notification.MarkAsReadedResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.notification.NotificationResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.setting.CheckTokenResponse;
import com.example.android_ket_cau_soft.model.CourseData;
import com.example.android_ket_cau_soft.model.NewsData;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragmentVM extends BaseVM {

    private List<NewsData> newsDataList = new ArrayList<>();
    private List<CourseData> hotCourseData = new ArrayList<>();
    private List<CourseData> newCourseData = new ArrayList<>();


    public void updateNewsDataList() {
        getAPIService().getHotNews().enqueue(initResponeCallback(EnumStorage.HOTNEWS_REQUEST.getEnumValue()));
    }


    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);

        if (key.equals(EnumStorage.HOTNEWS_REQUEST.getEnumValue())) {
            HotNewsResponse response = (HotNewsResponse) body;


            if (response.getStatus() && response.getData() != null) {

                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getData());
            }
        } else if (key.equals(EnumStorage.HOTCOURSE_REQUEST.getEnumValue())) {

            CourseResponse response = (CourseResponse) body;

            if (response.getStatus() && response.getData() != null) {
                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getData());
            }

        } else if (key.equals(EnumStorage.NEWCOURSE_REQUEST.getEnumValue())) {

            CourseResponse response = (CourseResponse) body;

            if (response.getStatus() && response.getData() != null) {
                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getData());
            }
        } else if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
            Log.i(TAG, "handleAPISuccess: CHECKTOKEN");
            CheckTokenResponse response = (CheckTokenResponse) body;

            if (response.getStatus()) {
                Log.i(TAG, "handleAPISuccess: " + response.getMsg());

                getNotification(account.getApiToken());
            }

        } else if (key.equals(EnumStorage.GET_NOTIFICATION.getEnumValue())) {
            Log.i(TAG, "handleAPISuccess: CHECKTOKEN");
            NotificationResponse response = (NotificationResponse) body;

            if (response.getStatus() && response.getNotifiDataList() != null) {
                onCheckingCallback.onCallbackSuccess(key, response.getUnread_num() + "", response.getNotifiDataList());
            }

        } else if (key.equals(EnumStorage.MARK_AS_READ.getEnumValue())) {

            MarkAsReadedResponse response = (MarkAsReadedResponse) body;

            if (response.getStatus()) {
                onCheckingCallback.onCallbackSuccess(key, null, null);
            }

        }
    }

    public List<NewsData> getNewsDataList() {
        return newsDataList;
    }


    public void updateHotCoursesList() {
        getAPIService().getHotCourses().enqueue(initResponeCallback(EnumStorage.HOTCOURSE_REQUEST.getEnumValue()));
    }

    public void updateNewCoursesList() {
        getAPIService().getNewCourses().enqueue(initResponeCallback(EnumStorage.NEWCOURSE_REQUEST.getEnumValue()));
    }


    public List<CourseData> getHotCourseData() {
        return hotCourseData;
    }

    public void setNewsDataList(NewsData[] newsDataArray) {
        this.newsDataList = new ArrayList<>();
        this.newsDataList.addAll(Arrays.asList(newsDataArray));
    }

    public List<CourseData> getNewCourseData() {
        return newCourseData;
    }

    public void setNewCourseList(List<CourseData> newsDataArray) {
        this.newCourseData = newsDataArray;

    }


    public void setHotCourseList(List<CourseData> newsDataArray) {
        this.hotCourseData = newsDataArray;
    }

    @Override
    protected void handleAPIFail(String key, int code, String message) {
        super.handleAPIFail(key, code, message);

        if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
            Log.e(TAG, "handleAPIFail: CHECKTOKEN" + message + " : " + code);
            if (code == 401) {
                onCheckingCallback.onCallbackError(key, "tài khoản này đã được đăng nhập ở nơi khác, vui lòng đăng nhập lại");
            }
        } else if (key.equals(EnumStorage.MARK_AS_READ.getEnumValue())) {

            onCheckingCallback.onCallbackError(key, message);
        }
    }


    public void markAsReaded(Integer id) {
        getAPIService().markAsReaded(id, account.getApiToken()).enqueue(initResponeCallback(EnumStorage.MARK_AS_READ.getEnumValue()));
    }
}
