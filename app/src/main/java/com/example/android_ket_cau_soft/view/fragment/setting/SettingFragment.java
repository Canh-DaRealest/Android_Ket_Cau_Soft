package com.example.android_ket_cau_soft.view.fragment.setting;


import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.OnBackPressedCallback;

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
        mBinding.includeSettingBack.tvBackText.setText("Cài Đặt");
        mBinding.includeSettingBack.ivBack.setVisibility(View.GONE);
        mViewModel.updateAccountFromDB();
        clickItems();


    }

    private void clickItems() {
        mBinding.trPersonalInfo.setOnClickListener(this);
        mBinding.trChangePassword.setOnClickListener(this);
        mBinding.trTermsPolicy.setOnClickListener(this);
        mBinding.trGetConfig.setOnClickListener(this);
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

            if (mViewModel.isState()) {
                dosignOut();
            } else {
                showSnackbar(mBinding.lnFrgSetting, NETWORK_ER_MSG, true);
            }
        } else if (v.getId() == R.id.tr_get_config) {
            showInfor();

        }
    }

    private void showInfor() {
        onParentFrgCallback.showFragmentFromMenu(GetConfigFragment.TAG, null, true);
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
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {

            mViewModel.setState(true);

        } else if (key.equals(EnumStorage.LOG_OUT.getEnumValue())) {
            CustomSharePreference.getInstance().saveBooleanValue(CustomSharePreference.LOGIN_STATE, false);
            mViewModel.deleteAccount();
            onParentFrgCallback.showFragmentFromMenu(LoginFragment.TAG, null, false);
        }
    }

    @Override
    public void onCallbackError(String key, String msg) {

        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            showSnackbar(mBinding.lnFrgSetting, msg, true);
            mViewModel.setState(false);

        } else {
            super.onCallbackError(key, msg);
        }
    }
}
