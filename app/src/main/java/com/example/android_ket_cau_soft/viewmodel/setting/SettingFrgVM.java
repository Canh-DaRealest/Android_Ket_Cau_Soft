package com.example.android_ket_cau_soft.viewmodel.setting;


import com.example.android_ket_cau_soft.App;
import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.request.LogoutRequest;
import com.example.android_ket_cau_soft.api.response.setting.LogoutResponse;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

public class SettingFrgVM extends BaseVM {

    public void checkLogout() {

        getAPIService().logOut(new LogoutRequest(account.getEmail())).enqueue(initResponeCallback(EnumStorage.LOG_OUT.getEnumValue()));
    }


    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);
        if (key.equals(EnumStorage.LOG_OUT.getEnumValue())) {
            LogoutResponse response = (LogoutResponse) body;


            if (response.getStatus()) {
                onCheckingCallback.onCallbackSuccess(key, response.getMsg(), null);
            }

        }
    }

    public void deleteAccount() {

        App.getInstance().getAppDb().getUserDAO().deleteUser(account);
    }

    private boolean state = false;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
