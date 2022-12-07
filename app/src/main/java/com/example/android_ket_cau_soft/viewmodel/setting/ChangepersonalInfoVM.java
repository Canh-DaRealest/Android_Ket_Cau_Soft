package com.example.android_ket_cau_soft.viewmodel.setting;

import android.util.Log;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.request.SaveInforRequest;
import com.example.android_ket_cau_soft.api.response.họmefrgres.setting.CheckTokenResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.setting.SaveInfoResponse;
import com.example.android_ket_cau_soft.database.entities.User;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;


public class ChangepersonalInfoVM extends BaseVM {


    public Boolean checkValidInput(String name) {
        return name.isEmpty();

    }

    public void handleSave(String email, String apiToken, String name, String sdt, String birthday, String password) {
        Log.i(TAG, "handleSave: " + email + " : " + apiToken + " : " + name + " : " + sdt + " : " + birthday + " : " + password);
        getAPIService().updateUser(new SaveInforRequest(email, apiToken, name, sdt, birthday, password))
                .enqueue(initResponeCallback(EnumStorage.SAVE_USER_INFO.getEnumValue()));
    }

    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);

        if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
            Log.i(TAG, "handleAPISuccess: CHECKTOKEN");
            CheckTokenResponse response = (CheckTokenResponse) body;

            if (response.getStatus()) {
                Log.i(TAG, "handleAPISuccess: " + response.getMsg());

                handleSave(account.getEmail(), account.getApiToken(), account.getName(), account.getSdt(),
                        account.getBirthday(), account.getPassword());
            }


        }else if (key.equals(EnumStorage.SAVE_USER_INFO.getEnumValue())){
            Log.i(TAG, "handleAPISuccess: SAVE_USER_INFO");
            SaveInfoResponse response = (SaveInfoResponse) body;
            if (response.getStatus()) {

                onCheckingCallback.onCallbackSuccess(EnumStorage.SAVE_USER_INFO.getEnumValue(), response.getMsg(), response.getData());
                Log.i(TAG, "handleAPISuccess: ChangePersonalViewModel: " + code + " : " + response.getMsg() + " : " + response.getData().toString());
            } else {
                onCheckingCallback.onCallbackError(EnumStorage.SAVE_USER_INFO.getEnumValue(), response.getMsg());
                Log.i(TAG, "handleAPISuccess: ChangePersonalViewModel: " + code + " : " + response.getMsg());

            }

        }

    }

    public void checkUserToken(String email, String apiToken) {
        checkToken(email, apiToken);
    }

    @Override
    protected void handleAPIFail(String key, int code, String message) {
        super.handleAPIFail(key, code, message);

        if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {
            Log.e(TAG, "handleAPIFail: CHECKTOKEN" + message + " : " + code);
            if (code == 401) {
                onCheckingCallback.onCallbackError(key, "Lỗi đăng nhập");
            }
        }
    }

    public void updateCurrentAccount(User account) {
    }
}
