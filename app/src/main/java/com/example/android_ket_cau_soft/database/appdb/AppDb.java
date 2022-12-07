package com.example.android_ket_cau_soft.database.appdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_ket_cau_soft.database.DAO.UserDAO;
import com.example.android_ket_cau_soft.database.entities.User;

@Database(entities = User.class, version = 1)
public abstract class AppDb extends RoomDatabase {

    public abstract UserDAO getUserDAO();
}
