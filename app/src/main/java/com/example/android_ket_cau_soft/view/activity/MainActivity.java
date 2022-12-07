package com.example.android_ket_cau_soft.view.activity;


import androidx.fragment.app.Fragment;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.databinding.ActivityLoginBinding;
import com.example.android_ket_cau_soft.view.fragment.SplashFragment;
import com.example.android_ket_cau_soft.view.fragment.login.LoginFragment;
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
        Fragment fragmentManager = getSupportFragmentManager().findFragmentById(R.id.fr_container_login);
        if (fragmentManager instanceof LoginFragment) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

}