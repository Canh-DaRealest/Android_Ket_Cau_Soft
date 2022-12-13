package com.example.android_ket_cau_soft.viewmodel.setting;


import com.example.android_ket_cau_soft.App;
import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.response.setting.GetConfigResponse;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

public class ConfigFrgVM extends BaseVM {


    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);
        if (key.equals(EnumStorage.GET_CONFIG.getEnumValue())) {
            GetConfigResponse response = (GetConfigResponse) body;


            if (response.getStatus()) {
                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), response.getConfigData());
            } else {
                onCheckingCallback.onCallbackError(key, response.getMsg());
            }

        }
    }

    @Override
    protected void handleAPIFail(String key, int code, String message) {
        super.handleAPIFail(key, code, message);
        if (key.equals(EnumStorage.GET_CONFIG.getEnumValue())) {

                onCheckingCallback.onCallbackError(key, code+": "+ message);
        }

    }

    public void deleteAccount() {

        App.getInstance().getAppDb().getUserDAO().deleteUser(account);
    }

    public void getConfig() {
        getAPIService().getConfig().enqueue(initResponeCallback(EnumStorage.GET_CONFIG.getEnumValue()));
    }
}
