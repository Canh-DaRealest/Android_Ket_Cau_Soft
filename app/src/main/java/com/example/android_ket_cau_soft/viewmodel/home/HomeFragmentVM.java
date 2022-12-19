package com.example.android_ket_cau_soft.viewmodel.home;


import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.response.hot_courses.CourseResponse;
import com.example.android_ket_cau_soft.api.response.news.HotNewsResponse;
import com.example.android_ket_cau_soft.api.response.notification.MarkAsReadedResponse;
import com.example.android_ket_cau_soft.api.response.notification.NotificationResponse;
import com.example.android_ket_cau_soft.api.response.setting.CheckTokenResponse;
import com.example.android_ket_cau_soft.model.CourseData;
import com.example.android_ket_cau_soft.model.NewsData;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragmentVM extends BaseVM {

    public  static List<NewsData> newsDataList = new ArrayList<>();
    public static List<CourseData> hotCourseData = new ArrayList<>();
    public static List<CourseData> newCourseData = new ArrayList<>();

    public static boolean isNull = false;
    public  static int unReadNotify;
    public static String newCourseTitle;
    public  static String hotCourseTitle;

    public void updateNewsDataList() {
        getAPIService().getHotNews().enqueue(initResponeCallback(EnumStorage.HOTNEWS_REQUEST.getEnumValue()));
    }


    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);

        if (key.equals(EnumStorage.HOTNEWS_REQUEST.getEnumValue())) {
            HotNewsResponse response = (HotNewsResponse) body;


            if (response.getStatus()) {
                NewsData[] mData = (NewsData[]) response.getData();
                setNewsDataList(mData);

                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getData());
            } else {
                onCheckingCallback.onCallbackError(key, response.getMsg());

            }
        } else if (key.equals(EnumStorage.HOTCOURSE_REQUEST.getEnumValue())) {

            CourseResponse response = (CourseResponse) body;

            if (response.getStatus()) {
                List<CourseData> mData = (List<CourseData>) response.getData();
                setHotCourseList(mData);
                hotCourseTitle =  response.getMsg();
                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getData());
            } else {
                onCheckingCallback.onCallbackError(key, response.getMsg());

            }

        } else if (key.equals(EnumStorage.NEWCOURSE_REQUEST.getEnumValue())) {

            CourseResponse response = (CourseResponse) body;

            if (response.getStatus() && response.getData() != null) {

                List<CourseData> mData = (List<CourseData>) response.getData();
                setNewCourseList(mData);
                newCourseTitle =  response.getMsg();
                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getData());
            }

        } else if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
            isNull= true;
            CheckTokenResponse response = (CheckTokenResponse) body;

            if (response.getStatus()) {

                getNotification(account.getApiToken());
            } else {
                onCheckingCallback.onCallbackError(key, response.getMsg());

            }

        } else if (key.equals(EnumStorage.GET_NOTIFICATION.getEnumValue())) {
            NotificationResponse response = (NotificationResponse) body;

            if (response.getStatus()) {
                unReadNotify = response.getUnread_num();
                onCheckingCallback.onCallbackSuccess(key, response.getUnread_num() + "", response.getNotifiDataList());
            } else {
                onCheckingCallback.onCallbackError(key, response.getMsg());

            }

        } else if (key.equals(EnumStorage.MARK_AS_READ.getEnumValue())) {

            MarkAsReadedResponse response = (MarkAsReadedResponse) body;

            if (response.getStatus()) {
                onCheckingCallback.onCallbackSuccess(key, null, null);
            } else {
                onCheckingCallback.onCallbackError(key, response.getMsg());

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


//        if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
//            Log.e(TAG, "handleAPIFail: CHECKTOKEN" + message + " : " + code);
//            if (code == 401) {
//                Log.e(TAG, "handleAPIFail: code "+code );
//                onCheckingCallback.onCallbackError(key, "tài khoản này đã được đăng nhập ở nơi khác, vui lòng đăng nhập lại");
//            }
//        } else {
        if (!key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {

            onCheckingCallback.onCallbackError(key, message);
        } else {

            super.handleAPIFail(key, code, message);
//
//        }
        }
    }


    public void markAsReaded(Integer id) {
        getAPIService().markAsReaded(id, account.getApiToken()).enqueue(initResponeCallback(EnumStorage.MARK_AS_READ.getEnumValue()));
    }


}
