package com.example.android_ket_cau_soft.viewmodel.home.material;


import android.util.Log;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.response.material.GetLiveLoadDataResponse;
import com.example.android_ket_cau_soft.model.lesson.LessonData;
import com.example.android_ket_cau_soft.model.lesson.LessonResponse;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

public class LiveLoadFrgVM extends BaseVM {


    private LessonData lessonData;

    public LessonData getLessonData() {
        return lessonData;
    }

    public void getLiveLoadData() {

        getAPIService().getLiveLoadData().enqueue(initResponeCallback(EnumStorage.GET_LIVE_LOAD.getEnumValue()));
    }

    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);

        if (key.equals(EnumStorage.GET_LIVE_LOAD.getEnumValue())) {
            Log.i(TAG, "handleAPISuccess: liveload ");
            GetLiveLoadDataResponse response = (GetLiveLoadDataResponse) body;

            if (response.getStatus()) {
                Log.i(TAG, "handleAPISuccess: liveload true");
                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getData());
            } else {
                onCheckingCallback.onCallbackError(key, response.getMsg());
                Log.i(TAG, "handleAPISuccess: liveload false");
            }

        }
    }

    @Override
    protected void handleAPIFail(String key, int code, String message) {
        super.handleAPIFail(key, code, message);
        onCheckingCallback.onCallbackError(key, message);
    }

    public void setLessonData(LessonData lessonData) {
        this.lessonData = lessonData;
    }
}
