package com.example.android_ket_cau_soft.viewmodel.home.material;


import android.util.Log;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.response.material.GetLiveLoadDataResponse;
import com.example.android_ket_cau_soft.api.response.material.GetRawMaterialDataResponse;
import com.example.android_ket_cau_soft.model.lesson.LessonData;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

public class RawMaterialFrgVM extends BaseVM {



    public void getRawMaterialData() {

        getAPIService().getRawMaterialData().enqueue(initResponeCallback(EnumStorage.GET_RAW_MATERIAL.getEnumValue()));
    }


    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);

        if (key.equals(EnumStorage.GET_RAW_MATERIAL.getEnumValue())) {

            GetRawMaterialDataResponse response = (GetRawMaterialDataResponse) body;

            if (response.getStatus()) {

                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getData());
            } else {
                onCheckingCallback.onCallbackError(key, response.getMsg());

            }

        }
    }

    @Override
    protected void handleAPIFail(String key, int code, String message) {
        super.handleAPIFail(key, code, message);
        onCheckingCallback.onCallbackError(key, message);
    }


}
