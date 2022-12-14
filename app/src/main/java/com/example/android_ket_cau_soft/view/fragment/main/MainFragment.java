package com.example.android_ket_cau_soft.view.fragment.main;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.example.android_ket_cau_soft.EnumStorage;
import com.example.android_ket_cau_soft.callback.OnParentFrgCallback;
import com.example.android_ket_cau_soft.callback.OnTouchListener;
import com.example.android_ket_cau_soft.databinding.FragmentMainBinding;
import com.example.android_ket_cau_soft.model.BottomNavigationBehaviour;
import com.example.android_ket_cau_soft.view.fragment.BaseFragment;
import com.example.android_ket_cau_soft.R;
import com.example.android_ket_cau_soft.view.fragment.setting.SettingFragment;
import com.example.android_ket_cau_soft.viewmodel.MainFragmentVM;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class MainFragment extends BaseFragment<FragmentMainBinding, MainFragmentVM> implements OnParentFrgCallback, OnTouchListener {

    public static final String TAG = MainFragment.class.getName();
    private BadgeDrawable badge;

//    @Override
//    public void showFragment(String tag, Object data, boolean isBacked) {
//        super.showFragment(tag, data, isBacked);
//        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//        fragmentTransaction.replace(R.id.act_home_container, fragment);
//    }

    private void cusTomBottomMenu() {

//        BottomNavigationView bottomNavigationView = ;
//      // hide and show bottom navigation view when scroll
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
//            layoutParams.setBehavior(new BottomNavigationBehaviour());

        mBinding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {

                    if (!mViewModel.getCurrentFragment().equals(EnumStorage.MENU_HOME.getEnumValue())) {
                        showHomeFragment();
                        //    currentFragment = ;
                        mViewModel.setCurrentFragment(EnumStorage.MENU_HOME.getEnumValue());
                        return true;
                    }

                } else if (item.getItemId() == R.id.menu_lesson) {

                    if (!mViewModel.getCurrentFragment().equals(EnumStorage.MENU_LESSON.getEnumValue())) {
                        showLessonFragment();
                        //    currentFragment =;
                        mViewModel.setCurrentFragment(EnumStorage.MENU_LESSON.getEnumValue());
                        return true;
                    }

                } else if (item.getItemId() == R.id.menu_notification) {

                    if (!mViewModel.getCurrentFragment().equals(EnumStorage.MENU_NOTIFICATION.getEnumValue())) {
                        showNotificationFragment();
                        //      currentFragment = ;
                        mViewModel.setCurrentFragment(EnumStorage.MENU_NOTIFICATION.getEnumValue());
                        return true;
                    }

                } else if (item.getItemId() == R.id.menu_setting) {


                    if (!mViewModel.getCurrentFragment().equals(EnumStorage.MENU_SETTING.getEnumValue())) {
                        showSettingFragment();
                        mViewModel.setCurrentFragment(EnumStorage.MENU_SETTING.getEnumValue());
                        return true;
                    }

                }
                return false;
            }
        });

    }

    private void updateNoticeCount(int count) {
        Log.i(TAG, "updateNoticeCount: " + count);
        badge = mBinding.bottomNav.getOrCreateBadge(R.id.menu_notification);
        badge.setNumber(count);
        badge.setVisible(count != 0);
    }

    @Override
    public void showFragmentFromMenu(String tag, Object data, boolean isBacked) {
        mainCallBack.showFragment(tag, data, isBacked);
    }

    private void showNotificationFragment() {
        showMenu(NotificationFragment.TAG, null);
    }

    private void showSettingFragment() {
        showMenu(SettingFragment.TAG, null);
    }

    private void showLessonFragment() {
        showMenu(TopicFragment.TAG, null);
    }

    private void showHomeFragment() {
        showMenu(HomeFragment.TAG, null);
    }


    @Override
    protected Class<MainFragmentVM> getClassVM() {
        return MainFragmentVM.class;
    }

    @Override
    protected FragmentMainBinding initViewBinding(LayoutInflater inflater) {
        return FragmentMainBinding.inflate(inflater);
    }

    @Override
    protected void initView() {


        cusTomBottomMenu();
        if (mViewModel.getCurrentFragment().equals(EnumStorage.MENU_HOME.getEnumValue())) {
            showMenu(HomeFragment.TAG, null);
        } else if (mViewModel.getCurrentFragment().equals(EnumStorage.MENU_LESSON.getEnumValue())) {
            showMenu(TopicFragment.TAG, null);
        } else if (mViewModel.getCurrentFragment().equals(EnumStorage.MENU_NOTIFICATION.getEnumValue())) {
            showMenu(NotificationFragment.TAG, null);
        } else if (mViewModel.getCurrentFragment().equals(EnumStorage.MENU_SETTING.getEnumValue())) {
            showMenu(SettingFragment.TAG, null);
        }

        //make bottomnavigation backpress perfectly
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!mViewModel.getCurrentFragment().equals(EnumStorage.MENU_HOME.getEnumValue())) {
                    showMenu(HomeFragment.TAG, null);

                    mViewModel.setCurrentFragment(EnumStorage.MENU_HOME.getEnumValue());
                    mBinding.bottomNav.getMenu().getItem(0).setChecked(true);
                } else {
                    requireActivity().onBackPressed();
                }

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


    }


    private void showMenu(String tag, Object data) {
//        super.showFragment(tag, data, isBacked);
//        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
//        fragmentTransaction.replace(R.id.fr_container_home, fragment);

        try {
            Class<?> classInstance = Class.forName(tag);

            BaseFragment<?, ?> fragment = (BaseFragment<?, ?>) classInstance.newInstance();

            fragment.setmData(data);
            fragment.setOnParentFrgCallback(this);
            fragment.setOnUpdateCountCallback(this);
            fragment.setOnTouchListener(this);

            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fr_container_home, fragment, tag);
            fragmentTransaction.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateCount(int count) {
        updateNoticeCount(count);
    }

    @Override
    public void onScroll(View view) {
        view.setOnTouchListener(new BottomNavigationBehaviour(mContext, mBinding.bottomNav));

    }
}