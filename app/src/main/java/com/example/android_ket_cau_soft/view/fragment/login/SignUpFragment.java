package com.example.android_ket_cau_soft.view.fragment.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.databinding.FragmentSignUpBinding;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.login.SignUpVM;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUpFragment extends BaseFragment<FragmentSignUpBinding, SignUpVM> {
    public static final String TAG = SignUpFragment.class.getName();

    @Override
    protected Class<SignUpVM> getClassVM() {
        return SignUpVM.class;
    }

    @Override
    protected FragmentSignUpBinding initViewBinding(LayoutInflater inflater) {
        return FragmentSignUpBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        showSoftKeyboard(mBinding.edtEmail);

        setClickView();
        handleFocus();

    }

    private void setClickView() {
        mBinding.btSignUp.setOnClickListener(this);
        mBinding.tvLogin.setOnClickListener(this);
        setOnEditorActionListener(mBinding.edtRetypePassword);

    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);
        if (v.getId() == R.id.bt_sign_up) {

            handleSigningUp();

        } else if (v.getId() == R.id.tv_login) {

            goToLoginScreen();
        }
    }

    private void goToLoginScreen() {
        backToPreviousFragment();
    }

    @Override
    protected void onAction(TextView v, int actionId) {
        super.onAction(v, actionId);

        if (v.equals(mBinding.edtRetypePassword)) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                handleSigningUp();
            }

        }
    }

    private void handleSigningUp() {
        hideSoftKeboard();

        String email = Objects.requireNonNull(mBinding.edtEmail.getText()).toString();
        String name = Objects.requireNonNull(mBinding.edtName.getText()).toString();
        String password = Objects.requireNonNull(mBinding.edtPassword.getText()).toString();
        String password1 = Objects.requireNonNull(mBinding.edtRetypePassword.getText()).toString();

        mViewModel.setInputEmail(email);
        mViewModel.setName(name);
        mViewModel.setInputPassword(password);
        mViewModel.setPassword1(password1);

        mViewModel.checkValidate();


    }

    @Override
    public void onCallbackError(String key, String msg) {
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            showSnackbar(mBinding.frSignupMain, msg, true);
            mViewModel.setState(false);

        } else if (key.equals(EnumStorage.CHECK_TOKEN.getEnumValue())) {

            super.onCallbackError(key, msg);

        } else if (key.equals(EnumStorage.EMAIL_ERROR.getEnumValue())) {

            showError(mBinding.textFieldEmail, msg);

        } else if (key.equals(EnumStorage.NAME_ERROR.getEnumValue())) {

            showError(mBinding.textFieldName, msg);

        } else if (key.equals(EnumStorage.PASSWORD_ERROR.getEnumValue())) {

            showError(mBinding.textFieldPassword, msg);

        } else if (key.equals(EnumStorage.CONFIRM_PASSWORD_ERROR.getEnumValue())) {

            showError(mBinding.textFieldRetypePassword, msg);

        } else {
            showSnackbar(mBinding.frSignupMain, msg, true);
        }
    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.SUCCESS.getEnumValue())) {
            showProgressDialog();
            if (mViewModel.getState()) {
                mViewModel.sendRequest();

            } else {
                showSnackbar(mBinding.frSignupMain, NETWORK_ER_MSG, true);
            }
        } else if (key.equals(EnumStorage.SIGN_UP_SUCCESS.getEnumValue())) {

            dismissProgressDialog();
            getNoticeDialog(mContext).setUpDialog(null, msg, "Quay lại", "Ẩn", false, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.bt_positive) {
                        //back to login screen
                        goToLoginScreen();
                        dismissNoticeDialog();
                    } else if (v.getId() == R.id.bt_negative) {
                        dismissNoticeDialog();
                    }
                }
            });
            showNoticeDialog();
        }

    }

    private void handleFocus() {
        onFocusChange(mBinding.textFieldPassword, mBinding.edtPassword, TextInputLayout.END_ICON_PASSWORD_TOGGLE, mBinding.btSignUp);
        onFocusChange(mBinding.textFieldRetypePassword, mBinding.edtRetypePassword, TextInputLayout.END_ICON_PASSWORD_TOGGLE, mBinding.btSignUp);

        onFocusChange(mBinding.textFieldEmail, mBinding.edtEmail, TextInputLayout.END_ICON_CLEAR_TEXT, mBinding.btSignUp);
        onFocusChange(mBinding.textFieldName, mBinding.edtName, TextInputLayout.END_ICON_CLEAR_TEXT, mBinding.btSignUp);
    }


}
