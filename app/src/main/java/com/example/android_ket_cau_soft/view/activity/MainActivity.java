package com.example.android_ket_cau_soft.view.activity;


import androidx.fragment.app.Fragment;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.databinding.ActivityLoginBinding;
import com.example.android_ket_cau_soft.view.fragment.SplashFragment;
import com.example.android_ket_cau_soft.view.fragment.login.LoginFragment;
import com.example.android_ket_cau_soft.view.fragment.main.HomeFragment;
import com.example.android_ket_cau_soft.view.fragment.main.NotificationFragment;
import com.example.android_ket_cau_soft.view.fragment.main.TopicFragment;
import com.example.android_ket_cau_soft.view.fragment.setting.SettingFragment;
import com.example.android_ket_cau_soft.viewmodel.CommonVM;

public class MainActivity extends BaseAct<ActivityLoginBinding, CommonVM> {


    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void intiViews() {
        showFragment(SplashFragment.TAG, null, false);

    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected ActivityLoginBinding intiViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}