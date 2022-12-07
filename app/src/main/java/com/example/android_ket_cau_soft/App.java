package com.example.android_ket_cau_soft;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.android_ket_cau_soft.database.appdb.AppDb;


public class App extends Application {

    private static App instance;
    private AppDb appDb;
    private Storage storage;
    private Context context;

    public Storage getStorage() {
        return storage;
    }

    public AppDb getAppDb() {
        return appDb;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appDb = Room.databaseBuilder(this, AppDb.class, "accountData").allowMainThreadQueries().build();
        instance = this;
        storage = new Storage();
    }

    public Context getContext() {
        return context;
    }
}
