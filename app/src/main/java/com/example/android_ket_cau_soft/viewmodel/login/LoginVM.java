package com.example.android_ket_cau_soft.viewmodel.login;

import android.util.Log;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.android_ket_cau_soft.App;
import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.request.LoginRequest;
import com.example.android_ket_cau_soft.api.response.loginresponse.LoginResponse;
import com.example.android_ket_cau_soft.database.entities.User;
import com.example.android_ket_cau_soft.model.UserData;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;


public class LoginVM extends BaseVM {


    public boolean checkInputPassword() {
        boolean value = true;


        if (inputPassword.isEmpty()) {

            value = false;

            return value;

        }
        return value;
    }

    //call api to login
    public void handleLogin() {

        getAPIService().login(new LoginRequest(inputEmail, inputPassword)).enqueue(initResponeCallback(EnumStorage.LOGIN_REQUEST.getEnumValue()));
    }

    public void checkInputEmailAndPassword() {
        if (inputPassword.isEmpty() || inputEmail.isEmpty()) {

            if (inputPassword.isEmpty()) {
                onCheckingCallback.onCallbackError(EnumStorage.PASSWORD_ERROR.getEnumValue(), "Mật khẩu trống!");
            }
            if (inputEmail.isEmpty()) {
                onCheckingCallback.onCallbackError(EnumStorage.EMAIL_ERROR.getEnumValue(), "Email trống!");
            }
        } else if (!checkEmail(inputEmail)) {
            onCheckingCallback.onCallbackError(EnumStorage.EMAIL_ERROR.getEnumValue(), "Email không hợp lệ!");
        } else {
            handleLogin();
        }
    }

    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);
        LoginResponse response = (LoginResponse) body;
        if (response.getStatus()) {
            onCheckingCallback.onCallbackSuccess(EnumStorage.LOGIN_REQUEST.getEnumValue(), response.getMsg(), response.getData());
        } else {
            onCheckingCallback.onCallbackError(EnumStorage.LOGIN_REQUEST.getEnumValue(), response.getMsg());
        }
    }

    @Override
    protected void handleAPIFail(String key, int code, String message) {
        super.handleAPIFail(key, code, message);
        onCheckingCallback.onCallbackError(key, code+": "+message);
    }

    public void updateUserAccount(UserData userData) {

        User newUser = new User();
        newUser.setEmail(userData.getEmail());
        newUser.setPassword(inputPassword);
        newUser.setName(userData.getName());
        newUser.setImage(userData.getImage());
        newUser.setSdt(userData.getSdt() + "");
        newUser.setBirthday(userData.getBirthday() + "");
        newUser.setVipMember(userData.getVipMember());
        newUser.setApiToken(userData.getApiToken());

        if (account == null) {
            App.getInstance().getAppDb().getUserDAO().insertUser(newUser);

        } else {
            App.getInstance().getAppDb().getUserDAO().updateUser(newUser);
        }
        //  setAccount(newUser);
        account = newUser;
    }
}
