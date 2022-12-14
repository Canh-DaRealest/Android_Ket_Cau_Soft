package com.example.android_ket_cau_soft.view.fragment.login;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;


import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.database.entities.User;
import com.example.android_ket_cau_soft.databinding.FragmentLoginBinding;
import com.example.android_ket_cau_soft.model.UserData;
import com.example.android_ket_cau_soft.sharepreference.CustomSharePreference;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.view.fragment.main.MainFragment;
import com.example.android_ket_cau_soft.viewmodel.login.LoginVM;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginVM> {
    public static final String TAG = LoginFragment.class.getName();

    @Override
    protected Class<LoginVM> getClassVM() {
        return LoginVM.class;
    }

    @Override
    protected FragmentLoginBinding initViewBinding(LayoutInflater inflater) {
        return FragmentLoginBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        hideSoftKeboard();
        mViewModel.updateAccountFromDB();

        if (mViewModel.getAccount() != null) {
            Log.i(TAG, "initView: LoginFragment: " + mViewModel.getAccount().toString());
            if (!mViewModel.getAccount().getEmail().isEmpty() && !mViewModel.getAccount().getPassword().isEmpty()) {
                setUiEdittext(mViewModel.getAccount());
            }
        }


        setClickView();
        //EnumCustom.CLEAR_TEXT.getEnumValue(), EnumCustom.PASSWORD_TEXT.getEnumValue(),
        //	onChangeEmail(mBinding.textFieldLoginEmail, mBinding.edtLoginEmail, mBinding.tvErrorLgEmail);
        onFocusChange(mBinding.textFieldLoginEmail, mBinding.edtLoginEmail, TextInputLayout.END_ICON_CLEAR_TEXT, mBinding.btLogin);
        onFocusChange(mBinding.textFieldLoginPassword, mBinding.edtLoginPassword, TextInputLayout.END_ICON_PASSWORD_TOGGLE, mBinding.btLogin);

    }

    private void setUiEdittext(User user) {
        mBinding.edtLoginEmail.setText(user.getEmail());
        mBinding.edtLoginPassword.setText(user.getPassword());
        // showSoftKeyboard(mBinding.edtLoginPassword);
    }


    private void setClickView() {
        mBinding.btLogin.setOnClickListener(this);
        mBinding.tvForgotPassword.setOnClickListener(this);
        mBinding.tvSignUp.setOnClickListener(this);


        mBinding.textFieldLoginEmail.setOnClickListener(this);
        mBinding.textFieldLoginPassword.setOnClickListener(this);

        //check keyboard action
        setOnEditorActionListener(mBinding.edtLoginEmail);
        setOnEditorActionListener(mBinding.edtLoginPassword);
    }


    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.bt_login) {
            checkLogin();
        } else if (v.getId() == R.id.tv_sign_up) {
            goToSignUpScreen();
        } else if (v.getId() == R.id.tv_forgot_password) {
            goToForgotPasswordScreen();
        }

    }

    @Override
    protected void onAction(TextView v, int actionId) {
        Log.i(TAG, "onAction: " + v.toString());
        if (v.equals(mBinding.edtLoginEmail)) {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                v.clearFocus();
                mBinding.edtLoginPassword.requestFocus();
            }

        } else if (v.equals(mBinding.edtLoginPassword)) {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                if (mViewModel.getState()) {
                    checkLogin();
                }

            }
        }
    }

    @Override
    public void onCallbackError(String key, String msg) {
        super.onCallbackError(key, msg);
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            mViewModel.setState(false);
            showSnackbar(mBinding.lnFrgLogin, msg, true);

        } else if (key.equals(EnumStorage.EMAIL_ERROR.getEnumValue())) {

            showError(mBinding.textFieldLoginEmail, msg);

        } else if (key.equals(EnumStorage.PASSWORD_ERROR.getEnumValue())) {

            showError(mBinding.textFieldLoginPassword, msg);
        } else if (key.equals(EnumStorage.LOGIN_REQUEST.getEnumValue())) {

            showSnackbar(mBinding.lnFrgLogin, msg, true);
        }

    }


    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {


            mViewModel.setState(true);
        } else {


            if (key.equals(EnumStorage.LOGIN_REQUEST.getEnumValue())) {

                //   saveToPreference(CustomSharePreference.LOGIN_STATE, true);
                UserData userData = (UserData) data;
                showMainActivity(userData);
            }
        }
    }

    private void goToForgotPasswordScreen() {
        mainCallBack.showFragment(ResetPasswordFragment.TAG, "Quên mật khẩu", true);
    }

    private void goToSignUpScreen() {
        mainCallBack.showFragment(SignUpFragment.TAG, null, true);
    }

    private void checkLogin() {
        hideSoftKeboard();


        mViewModel.setInputEmail(Objects.requireNonNull(mBinding.edtLoginEmail.getText()).toString());
        mViewModel.setInputPassword(Objects.requireNonNull(mBinding.edtLoginPassword.getText()).toString());

        showProgressDialog();
        mViewModel.checkInputEmailAndPassword();

    }


    private void showMainActivity(UserData userData) {


        mViewModel.updateUserAccount(userData);

        saveToPreference(CustomSharePreference.LOGIN_STATE, true);
        mainCallBack.showFragment(MainFragment.TAG, null, false);
        // handleActivityCallback(EnumCustom.GO_TO_MAIN.getEnumValue());
    }


}
