package com.example.android_ket_cau_soft.viewmodel;

import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.ViewModel;

import com.example.android_ket_cau_soft.App;
import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.api.apiservice.APIService;
import com.example.android_ket_cau_soft.api.clientservice.ClientService;
import com.example.android_ket_cau_soft.api.request.CheckTokenReques;
import com.example.android_ket_cau_soft.api.request.LoginRequest;
import com.example.android_ket_cau_soft.callback.OnAPICallback;
import com.example.android_ket_cau_soft.callback.OnCheckingCallback;
import com.example.android_ket_cau_soft.database.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseVM extends ViewModel {
    public static final String TAG = BaseVM.class.getName();
    protected OnAPICallback apiCallback;
    public String inputEmail;
    public String inputPassword;
    protected User account;

    public User getAccount() {
        return account;
    }

    public void setAccount(User account) {
        this.account = account;
        App.getInstance().getAppDb().getUserDAO().updateUser(this.account);
    }

    public void updateAccountFromDB() {
        account = App.getInstance().getAppDb().getUserDAO().getUser();
    }

    public String getInputEmail() {
        return inputEmail;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

    public void setApiCallback(OnAPICallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    protected OnCheckingCallback onCheckingCallback;

    public void setOnCheckingCallback(OnCheckingCallback onCheckingCallback) {
        this.onCheckingCallback = onCheckingCallback;
    }

    protected APIService getAPIService() {

        return ClientService.getInstance().getRetrofit();
    }


    protected void getABC(String key) {


        getAPIService().login(new LoginRequest("a", "b")).enqueue(initResponeCallback(key));
    }

    protected <T> Callback<T> initResponeCallback(String key) {

        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {

                    handleAPISuccess(key, response.code() + "", response.body());

                } else {

                    handleAPIFail(key, response.code(), response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                handleAPIException(key, t.getMessage());
            }
        };

    }

    protected void handleAPISuccess(String key, String code, Object body) {
        //do nothing
    }

    protected void handleAPIException(String key, String message) {
        handleAPIFail(key, 999, message);

    }

    protected void handleAPIFail(String key, int code, String message) {
        onCheckingCallback.onCallbackError(key, code + " : " + message + ", vui lòng thử lại");
    }

    protected boolean checkValidateEmail(String textEmail) {
        return Patterns.EMAIL_ADDRESS.matcher(textEmail).matches();
    }

    //check valid email
    protected boolean checkEmail(String email) {


        boolean state = (email.trim()).endsWith(EnumStorage.EMAIL_PATH.getEnumValue());

        if (state) {

            if (!checkValidateEmail(email.trim())) {

                Log.e(TAG, "login_frg: check_valid_email:   Invalid  email1  : " + email);
                return false;

            } else {
                Log.e(TAG, "login_frg: check_valid_email:  Valid email   : " + email);
                setInputEmail(email);
                return true;

            }


        } else {


            if (!checkValidateEmail(email.trim() + EnumStorage.EMAIL_PATH.getEnumValue())) {

                Log.e(TAG, "login_frg: check_valid_email 2:  invalid  email  : " + email);

                return false;

            } else {
                setInputEmail(email.trim() + EnumStorage.EMAIL_PATH.getEnumValue());
                return true;


            }

        }
    }

    public void checkToken(String email, String token) {
        getAPIService().checkToken(new CheckTokenReques(email, token)).enqueue(initResponeCallback(EnumStorage.CHECK_TOKEN.getEnumValue()));
    }

    protected void getNotification(String token){

        getAPIService().getNotification(token).enqueue(initResponeCallback(EnumStorage.GET_NOTIFICATION.getEnumValue()));
    }


}
