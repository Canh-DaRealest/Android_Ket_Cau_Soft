package com.example.android_ket_cau_soft.viewmodel;


import com.example.android_ket_cau_soft.EnumStorage;

public class MainFragmentVM extends BaseVM {

    private String currentFragment = EnumStorage.MENU_HOME.getEnumValue();

    public void setCurrentFragment(String currentFragment) {
        this.currentFragment = currentFragment;

    }

    public String getCurrentFragment() {
        return currentFragment;
    }


}
