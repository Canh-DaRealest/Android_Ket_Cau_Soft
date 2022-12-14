package com.example.android_ket_cau_soft.view.fragment.setting;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.database.entities.User;

import com.example.android_ket_cau_soft.databinding.FragmentConfigBinding;
import com.example.android_ket_cau_soft.model.ConfigData;
import com.example.android_ket_cau_soft.model.UserData;
import com.example.android_ket_cau_soft.sharepreference.CustomSharePreference;
import com.example.android_ket_cau_soft.view.activity.MainActivity;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.view.fragment.login.LoginFragment;
import com.example.android_ket_cau_soft.view.fragment.main.WebViewFragment;
import com.example.android_ket_cau_soft.viewmodel.setting.ChangepersonalInfoVM;
import com.example.android_ket_cau_soft.viewmodel.setting.ConfigFrgVM;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetConfigFragment extends BaseFragment<FragmentConfigBinding, ConfigFrgVM> {


    public static final String TAG = GetConfigFragment.class.getName();
    private ConfigData configData;


    @Override
    protected Class<ConfigFrgVM> getClassVM() {
        return ConfigFrgVM.class;
    }

    @Override
    protected FragmentConfigBinding initViewBinding(LayoutInflater inflater) {
        return FragmentConfigBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        mBinding.includeConfigBack.ivBack.setOnClickListener(this);
        mBinding.includeConfigBack.tvBackText.setText("Thông tin Về Chúng Tôi");
    }


    @Override
    public void onCallbackSuccess(String key, String msg, Object data) {
        super.onCallbackSuccess(key, msg, data);
        if (key.equals(EnumStorage.NETWORK_STATE.getEnumValue())) {
            mViewModel.getConfig();
        } else if (key.equals(EnumStorage.GET_CONFIG.getEnumValue())) {
            ConfigData configData = (ConfigData) data;

            updateUI(configData);

        }
    }

    private void updateUI(ConfigData configData) {

        this.configData = configData;
        mBinding.tvFanpage.setOnClickListener(this);
        mBinding.tvWebsite.setOnClickListener(this);
        mBinding.tvFacebookGroup.setOnClickListener(this);

        mBinding.tvFacebookGroup.setText(configData.getFacegroup());
        mBinding.tvWebsite.setText(configData.getWebsite());
        mBinding.tvFanpage.setText(configData.getFanpage());
        mBinding.tvZaloNum.setText(configData.getZalo());
        mBinding.tvAddress.setText(configData.getAddress());
        mBinding.tvPhoneNum.setText(configData.getPhone());

    }

    @Override
    protected void clickView(View v) {
        super.clickView(v);

        if (v.getId() == R.id.tv_website) {
            showWebView(configData.getWebsite());


        } else if (v.getId() == R.id.tv_fanpage) {
            showWebView(configData.getFanpage());

        } else if (v.getId() == R.id.tv_facebook_group) {
            showWebView(configData.getFacegroup());

        }else if (v.getId() == R.id.iv_back) {
           backToPreviousFragment();
        }
    }

    private void showWebView(String s) {
        if (s != null) {
            mainCallBack.showFragment(WebViewFragment.TAG, s, true);
        }

    }

    @Override
    public void onCallbackError(String key, String msg) {
        super.onCallbackError(key, msg);
        showSnackbar(mBinding.clConfigMain, msg, true);
    }
}
