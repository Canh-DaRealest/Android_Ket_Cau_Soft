package com.example.android_ket_cau_soft.view.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.databinding.FragmentSplashBinding;
import com.example.android_ket_cau_soft.sharepreference.CustomSharePreference;
import com.example.android_ket_cau_soft.view.fragment.login.LoginFragment;
import com.example.android_ket_cau_soft.view.fragment.main.MainFragment;
import com.example.android_ket_cau_soft.viewmodel.CommonVM;

public class SplashFragment extends BaseFragment<FragmentSplashBinding, CommonVM> {
    public static final String TAG = SplashFragment.class.getName();

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected FragmentSplashBinding initViewBinding(LayoutInflater inflater) {
        return FragmentSplashBinding.inflate(inflater);


    }

    @Override
    protected void initView() {
        //neu nguoi dung da login(chua log out ra)
        boolean isLogin = CustomSharePreference.getInstance().getBooleanValue(CustomSharePreference.LOGIN_STATE);

        //if user has been log in, come to MainAct
        if (isLogin) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, " splash- screen+ user has been login " + isLogin);

                   mainCallBack.showFragment(MainFragment.TAG, null, false);
                }
            }, 2000);

        } else {
            //else come to LoginAcitvity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mainCallBack.showFragment(LoginFragment.TAG, null, false);
                }
            }, 2000);
        }


    }


}
