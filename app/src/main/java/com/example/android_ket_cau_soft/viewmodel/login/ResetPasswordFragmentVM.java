package com.example.android_ket_cau_soft.viewmodel.login;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.api.request.ResetPasswordRequest;
import com.example.android_ket_cau_soft.api.response.loginresponse.ResetPasswordResponse;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

public class ResetPasswordFragmentVM extends BaseVM {


    private String email;
    private String newPassword;
    private String confirmPassword;


    public void checkValidate() {
        boolean result = true;
        if (email.isEmpty()) {
            onCheckingCallback.onCallbackError(EnumStorage.EMAIL_ERROR.getEnumValue(), "Email trống!");
            result = false;
        } else if (!checkEmail(email)) {
            onCheckingCallback.onCallbackError(EnumStorage.EMAIL_ERROR.getEnumValue(), "Email không hợp lệ ");
            result = false;
        }

        if (newPassword.isEmpty()) {
            onCheckingCallback.onCallbackError(EnumStorage.PASSWORD_ERROR.getEnumValue(), "Mật khẩu mới trống");
            result = false;
        }
        if (confirmPassword.isEmpty()) {
            onCheckingCallback.onCallbackError(EnumStorage.CONFIRM_PASSWORD_ERROR.getEnumValue(), "Xác nhận mật khẩu trống");
            result = false;
        }

        if (!confirmPassword.equals(newPassword)) {
            onCheckingCallback.onCallbackError(EnumStorage.CONFIRM_PASSWORD_ERROR.getEnumValue(), "Xác nhận mật khẩu không khớp");
            result = false;
        }

        if (result) {
            onCheckingCallback.onCallbackSuccess(EnumStorage.SUCCESS.getEnumValue(), null, null);

        }

    }


    public void sendRequest() {
        getAPIService().resetPassWord(new ResetPasswordRequest(email, newPassword, confirmPassword)).enqueue(initResponeCallback(EnumStorage.RESET_PASSWORD_REQUEST.getEnumValue()));
    }

    @Override
    protected void handleAPISuccess(String key, String code, Object body) {
        super.handleAPISuccess(key, code, body);
        ResetPasswordResponse response = (ResetPasswordResponse) body;

        if (response.getStatus()) {
            onCheckingCallback.onCallbackSuccess(EnumStorage.RESET_PASSWORD_REQUEST.getEnumValue(), response.getMsg(), null);
        } else {
            onCheckingCallback.onCallbackError(EnumStorage.RESET_PASSWORD_REQUEST.getEnumValue(), response.getMsg());
        }

    }


    @Override
    public String getInputEmail() {
        return email;
    }

    @Override
    public void setInputEmail(String inputEmail) {
        this.email = inputEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    private boolean state = false;

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
