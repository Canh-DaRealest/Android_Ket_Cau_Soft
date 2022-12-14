package com.example.android_ket_cau_soft.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.broadcastreceiver.MyBroadCast;
import com.example.android_ket_cau_soft.callback.IMainCallBack;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;

public abstract class BaseAct<T extends ViewBinding, M extends ViewModel> extends AppCompatActivity implements View.OnClickListener, IMainCallBack {
    protected T binding;
    protected M viewModel;
    protected FragmentTransaction fragmentTransaction;
    protected BaseFragment<?, ?> fragment;
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = intiViewBinding();
        viewModel = new ViewModelProvider(this).get(getClassVM());
        setContentView(binding.getRoot());

        intiViews();
    }

    protected abstract void intiViews();

    protected abstract Class<M> getClassVM();

    protected abstract T intiViewBinding();

    @Override
    public final void onClick(View v) {

        clickView(v);
    }

    private void clickView(View v) {

    }

    @Override
    public void showFragment(String tag, Object data, boolean isBacked) {

        try {
            Class<?> clazzName = Class.forName(tag);

         fragment = (BaseFragment<?, ?>) clazzName.newInstance();
            fragment.setmData(data);
            fragment.setMainCallBack(this);


            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentTransaction = fragmentManager.beginTransaction();


            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            fragmentTransaction.replace(R.id.fr_container_login, fragment);
//
//            if (idContainer == EnumCustom.HOME_CONTAINER.getEnumValue()) {
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//                fragmentTransaction.replace(R.id.fr_container_home, fragment);
//
//            } else if (idContainer == EnumCustom.LOGIN_CONTAINER.getEnumValue()) {
//                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_bottom, R.anim.enter_from_left, R.anim.exit_to_top);
//                fragmentTransaction.replace(R.id.fr_container_login, fragment);
//            }


            if (isBacked) {

                fragmentTransaction.addToBackStack(tag);
            }

            fragmentTransaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showMenu(String tag, Object data, boolean isBacked, String idContainer) {

    }

//    @Override
//    public void changeActivity(String key) {
//
////        if (key.equals(EnumCustom.GO_TO_MAIN.getEnumValue())) {
////            Intent intent = new Intent();
////            intent.setClass(this, MainFragment.class);
////
////            startActivity(intent);
////
////            finish();
////
////   }else
//// if (key.equals(EnumCustom.GO_TO_LOGIN.getEnumValue())){
////            Intent intent = new Intent();
////            intent.setClass(this, SplashActivity.class);
////
////            startActivity(intent);
////
////            finish();
////        }
//  }
}
