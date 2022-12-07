package com.example.android_ket_cau_soft.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android_ket_cau_soft.App;


public class CustomSharePreference {


				public static final String USER_EMAIL = "USER_EMAIL";
				public static final String USER_PASSWORD = "USER_PASSWORD";
				private static final String CUSTOM_SHARE_PREF = "CUSTOM_SHARE_PREF";
    public static final String LOGIN_STATE = "IS_LOGIN";

    private static CustomSharePreference instance;


    private CustomSharePreference() {
    }

    public static CustomSharePreference getInstance() {
        if (instance == null) {
            instance = new CustomSharePreference();
        }
        return instance;
    }

    private SharedPreferences getSharePreference() {

        return App.getInstance().getSharedPreferences(CUSTOM_SHARE_PREF, Context.MODE_PRIVATE);

    }

    public void saveStringValue(String key, String value) {

        SharedPreferences sharedPreferences = getSharePreference();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(key, value);
        editor.apply();


    }

    public String getStringValue(String key) {
        SharedPreferences sharedPreferences = getSharePreference();
        return sharedPreferences.getString(key, null);

    }


    public void saveBooleanValue(String key, boolean value) {

        SharedPreferences sharedPreferences = getSharePreference();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(key, value);
        editor.apply();


    }

    public boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = getSharePreference();
        return sharedPreferences.getBoolean(key, false);

    }


}
