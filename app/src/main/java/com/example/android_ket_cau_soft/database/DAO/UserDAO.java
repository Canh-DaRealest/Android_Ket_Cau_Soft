package com.example.android_ket_cau_soft.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_ket_cau_soft.database.entities.User;


@Dao
public interface UserDAO {

    @Query("SELECT * FROM User")
    User getUser();

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);
}
