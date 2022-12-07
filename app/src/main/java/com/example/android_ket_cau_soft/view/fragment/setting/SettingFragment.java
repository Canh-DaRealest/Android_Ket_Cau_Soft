package com.example.android_ket_cau_soft.view.fragment.setting;


import android.view.LayoutInflater;
import android.view.View;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.databinding.FragmentSettingBinding;
import com.example.android_ket_cau_soft.sharepreference.CustomSharePreference;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.view.fragment.login.LoginFragment;
import com.example.android_ket_cau_soft.view.fragment.login.ResetPasswordFragment;
import com.example.android_ket_cau_soft.view.fragment.main.WebViewFragment;
import com.example.android_ket_cau_soft.viewmodel.setting.SettingFrgVM;

public class SettingFragment extends BaseFragment<FragmentSettingBinding, SettingFrgVM> {


    public static final String TAG = SettingFragment.class.getName();

    @Override
    protected Class<SettingFrgVM> getClassVM() {
        return SettingFrgVM.class;
    }

    @Override
    protected FragmentSettingBinding initViewBinding(LayoutInflater inflater) {
        return FragmentSettingBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        mViewModel.updateAccountFromDB();
        clickItems();


    }

    private void clickItems() {
        mBinding.trPersonalInfo.setOnClickListener(this);
        mBinding.trChangePassword.setOnClickListener(this);
        mBinding.trTermsPolicy.setOnClickListener(this);
        mBinding.btLogOut.setOnClickListener(this);
    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.tr_personal_info) {
            goToChangeInfoScreen();

        } else if (v.getId() == R.id.tr_change_password) {
            goToChangePasswordScreen();

        } else if (v.getId() == R.id.tr_terms_policy) {

            showTermsPolicy();

        } else if (v.getId() == R.id.bt_log_out) {

            dosignOut();
        }
    }

    private void dosignOut() {
        showProgressDialog();
        mViewModel.checkLogout();
    }

    private void goToChangePasswordScreen() {
        onParentFrgCallback.showFragmentFromMenu(ResetPasswordFragment.TAG, "Đổi mật khẩu", true);
    }

    private void goToChangeInfoScreen() {
        onParentFrgCallback.showFragmentFromMenu(ChangePersonalInfoFragment.TAG, null, true);
    }

    private void showTermsPolicy() {

        String url = "https://ketcausoft.com/cam-ket-danh-cho-khach-hang";
        onParentFrgCallback.showFragmentFromMenu(WebViewFragment.TAG, url, true);

    }

    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.LOG_OUT.getEnumValue())) {
            CustomSharePreference.getInstance().saveBooleanValue(CustomSharePreference.LOGIN_STATE, false);
            onParentFrgCallback.showFragmentFromMenu(LoginFragment.TAG, null, false);
        }
    }
}