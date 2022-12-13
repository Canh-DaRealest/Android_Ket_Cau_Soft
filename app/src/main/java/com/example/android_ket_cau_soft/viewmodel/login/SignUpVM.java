package com.example.android_ket_cau_soft.viewmodel.login;


import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.api.request.SignUpRequest;
import com.example.android_ket_cau_soft.api.response.loginresponse.SignUpResponse;
import com.example.android_ket_cau_soft.viewmodel.BaseVM;

public class SignUpVM extends BaseVM {


    private String email;
    private String name;
    private String password;
    private String password1;



    public void checkValidate() {
        boolean result = true;
        if (email.isEmpty()) {
            onCheckingCallback.onCallbackError(EnumStorage.EMAIL_ERROR.getEnumValue(), "Email trống!");
            result = false;
        } else if (!checkEmail(email)) {
            onCheckingCallback.onCallbackError(EnumStorage.EMAIL_ERROR.getEnumValue(), "Email không hợp lệ ");
            result = false;
        }
        if (name.isEmpty()) {
            onCheckingCallback.onCallbackError(EnumStorage.NAME_ERROR.getEnumValue(), "Tên người dùng trống");
        }
        if (password.isEmpty()) {
            onCheckingCallback.onCallbackError(EnumStorage.PASSWORD_ERROR.getEnumValue(), "Mật khẩu mới trống");
            result = false;
        }
        if (password1.isEmpty()) {
            onCheckingCallback.onCallbackError(EnumStorage.CONFIRM_PASSWORD_ERROR.getEnumValue(), "Xác nhận mật khẩu trống");
            result = false;
        }

        if (!password1.equals(password)) {
            onCheckingCallback.onCallbackError(EnumStorage.CONFIRM_PASSWORD_ERROR.getEnumValue(), "Xác nhận mật khẩu không khớp");
            result = false;
        }

        if (result) {
            onCheckingCallback.onCallbackSuccess(EnumStorage.SUCCESS.getEnumValue(), null, null);
        }

    }


    public void sendRequest() {
     getAPIService().singingUp(new SignUpRequest(email, name, password, password1)).enqueue(initResponeCallback(EnumStorage.SIGN_UP_REQUEST.getEnumValue()));
    }

    @Override
    protected void handleAPISuccess(String key,String code, Object body) {
        super.handleAPISuccess(key,code, body);
        SignUpResponse response = (SignUpResponse) body;

        if (response.getStatus()){
            onCheckingCallback.onCallbackSuccess(key, response.getMsg(), null);
        }else{
            onCheckingCallback.onCallbackError(key, response.getMsg());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getInputPassword() {
        return password;
    }

    @Override
    public void setInputPassword(String inputPassword) {
        this.password = inputPassword;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    private boolean state = false;

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
