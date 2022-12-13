package com.example.android_ket_cau_soft.viewmodel.setting;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.request.SaveInforRequest;
import com.example.android_ket_cau_soft.api.response.setting.CheckTokenResponse;
import com.example.android_ket_cau_soft.api.response.setting.SaveInfoResponse;
import com.example.android_ket_cau_soft.database.entities.User;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;


public class ChangepersonalInfoVM extends BaseVM {

    protected  boolean state = false;

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    public Boolean checkValidInput(String name) {
        return name.isEmpty();

    }

    public void handleSave(String email, String apiToken, String name, String sdt, String birthday, String password) {
        getAPIService().updateUser(new SaveInforRequest(email, apiToken, name, sdt, birthday, password))
                .enqueue(initResponeCallback(EnumStorage.SAVE_USER_INFO.getEnumValue()));
    }

    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);

        if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {

            CheckTokenResponse response = (CheckTokenResponse) body;

            if (response.getStatus()) {
                handleSave(account.getEmail(), account.getApiToken(), account.getName(), account.getSdt(),
                        account.getBirthday(), account.getPassword());
            }


        }else if (key.equals(EnumStorage.SAVE_USER_INFO.getEnumValue())){
            SaveInfoResponse response = (SaveInfoResponse) body;
            if (response.getStatus()) {

                onCheckingCallback.onCallbackSuccess(EnumStorage.SAVE_USER_INFO.getEnumValue(), response.getMsg(), response.getData());
            } else {
                onCheckingCallback.onCallbackError(EnumStorage.SAVE_USER_INFO.getEnumValue(), response.getMsg());

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
            if (code == 401) {
                onCheckingCallback.onCallbackError(key, "Lỗi đăng nhập");
            }
        }
    }

    public void updateCurrentAccount(User account) {
    }


}
