package com.example.android_ket_cau_soft.view.fragment.login;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.databinding.FragmentResetPasswordBinding;
import com.example.android_ket_cau_soft.model.ObjectResult;
import com.example.android_ket_cau_soft.sharepreference.CustomSharePreference;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.viewmodel.login.ResetPasswordFragmentVM;
import com.google.android.material.textfield.TextInputLayout;

public class ResetPasswordFragment extends BaseFragment<FragmentResetPasswordBinding, ResetPasswordFragmentVM> {
    public static final String TAG = ResetPasswordFragment.class.getName();
    private ObjectResult objectResult;

    @Override
    protected Class<ResetPasswordFragmentVM> getClassVM() {
        return ResetPasswordFragmentVM.class;
    }

    @Override
    protected FragmentResetPasswordBinding initViewBinding(LayoutInflater inflater) {
        return FragmentResetPasswordBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        showSoftKeyboard(mBinding.edtForgotEmail);
        handleViews();

    }

    private void handleViews() {
        setOnEditorActionListener(mBinding.edtConfirmPassword);
        setOnEditorActionListener(mBinding.edtNewPassword);
        setOnEditorActionListener(mBinding.edtForgotEmail);


        onFocusChange(mBinding.textFieldNewPassword, mBinding.edtNewPassword, TextInputLayout.END_ICON_PASSWORD_TOGGLE,mBinding.btSend);
        onFocusChange(mBinding.textFieldConfirmPassword, mBinding.edtConfirmPassword, TextInputLayout.END_ICON_PASSWORD_TOGGLE,mBinding.btSend);
        onFocusChange(mBinding.textfieldForgotEmail, mBinding.edtForgotEmail, TextInputLayout.END_ICON_CLEAR_TEXT,mBinding.btSend);

        mBinding.btSend.setOnClickListener(this);
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.iv_back) {
            backToPreviousFragment();
        } else {
            handleEmailRequest();

        }

    }

    @Override
    protected void onAction(TextView v, int actionId) {
        super.onAction(v, actionId);

        Log.i(TAG, "onAction: " + v.toString());
        if (v.equals(mBinding.edtConfirmPassword)) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                handleEmailRequest();
            }
        } else if (v.equals(mBinding.edtNewPassword)) {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                v.clearFocus();
                mBinding.edtConfirmPassword.requestFocus();
            }
        } else if (v.equals(mBinding.edtForgotEmail)) {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                v.clearFocus();
                mBinding.edtNewPassword.requestFocus();
            }
        }
    }

    private void handleEmailRequest() {


        hideSoftKeboard();

        String email = mBinding.edtForgotEmail.getText().toString();
        String newPassword = mBinding.edtNewPassword.getText().toString();
        String confirmPassword = mBinding.edtConfirmPassword.getText().toString();

        mViewModel.setInputEmail(email);
        mViewModel.setNewPassword(newPassword);
        mViewModel.setConfirmPassword(confirmPassword);

        mViewModel.checkValidate();

    }

    @Override
    protected void returnError(String key, String msg) {
        super.returnError(key, msg);
    }


    private void goToLoginScreen() {
        CustomSharePreference.getInstance().saveBooleanValue(CustomSharePreference.LOGIN_STATE, false);
        mainCallBack.showFragment(LoginFragment.TAG, null, false);
    }

    @Override
    public void onCallbackError(String key, String msg) {
        super.onCallbackError(key, msg);
        if (key.equals(EnumStorage.RESET_PASSWORD_REQUEST.getEnumValue())) {
            dismissProgressDialog();
            showSnackbar(mBinding.lnResetMain, msg, true);
        } else if (key.equals(EnumStorage.EMAIL_ERROR.getEnumValue())) {

            showError(mBinding.textfieldForgotEmail, msg);

        } else if (key.equals(EnumStorage.PASSWORD_ERROR.getEnumValue())) {

            showError(mBinding.textFieldNewPassword, msg);

        } else if (key.equals(EnumStorage.CONFIRM_PASSWORD_ERROR.getEnumValue())) {

            showError(mBinding.textFieldConfirmPassword, msg);

        }
    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.SUCCESS.getEnumValue())) {
            showProgressDialog();
            showSnackbar(mBinding.lnResetMain, msg, false);
        } else if (key.equals(EnumStorage.RESET_PASSWORD_REQUEST.getEnumValue())) {

            dismissProgressDialog();
            getNoticeDialog(mContext).setUpDialog(null, msg + "\n\n Trở về màn hình đăng nhập?", "Có", "Không", false, new View.OnClickListener() {
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
}
