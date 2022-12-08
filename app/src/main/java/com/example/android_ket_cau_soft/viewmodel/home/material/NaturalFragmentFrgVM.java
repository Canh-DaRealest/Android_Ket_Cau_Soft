package com.example.android_ket_cau_soft.viewmodel.home.material;


import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.response.material.GetLNaturalDataResponse;
import com.example.android_ket_cau_soft.api.response.material.GetRawMaterialDataResponse;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

public class NaturalFragmentFrgVM extends BaseVM {



    public void getNaturalData() {

        getAPIService().getNaturalData().enqueue(initResponeCallback(EnumStorage.GET_NATURAL_DATA.getEnumValue()));
    }


    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);

        if (key.equals(EnumStorage.GET_NATURAL_DATA.getEnumValue())) {

            GetLNaturalDataResponse response = (GetLNaturalDataResponse) body;

            if (response.getStatus()) {

                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getDataList());
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
