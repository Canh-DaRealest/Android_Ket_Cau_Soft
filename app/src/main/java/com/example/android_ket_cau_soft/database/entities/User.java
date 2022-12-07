package com.example.android_ket_cau_soft.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @ColumnInfo(name = "email")
    @PrimaryKey
    @NonNull
    public String email;

    @ColumnInfo(name = "password")

    @NonNull
    public String password;


    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "image")

    @NonNull
    public String image;

    @ColumnInfo(name = "sdt")
    public String sdt;

    @ColumnInfo(name = "birthday")
    public String birthday;

    @ColumnInfo(name = "vip_member")
    @NonNull
    public Boolean vipMember;

    @ColumnInfo(name = "api_token")
    @NonNull
    public String apiToken;


    public User(@NonNull String email, @NonNull String password, @NonNull String apiToken, @NonNull String name,
                @NonNull String image,  String sdt,  String birthday, @NonNull Boolean vipMember) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.image = image;
        this.sdt = sdt;
        this.birthday = birthday;
        this.vipMember = vipMember;
        this.apiToken = apiToken;
    }

    public User() {
    }

    @NonNull
    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(@NonNull String apiToken) {
        this.apiToken = apiToken;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    public void setImage(@NonNull String image) {
        this.image = image;
    }


    public String getSdt() {
        return sdt;
    }

    public void setSdt( String sdt) {
        this.sdt = sdt;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday( String birthday) {
        this.birthday = birthday;
    }

    @NonNull
    public Boolean getVipMember() {
        return vipMember;
    }

    public void setVipMember(@NonNull Boolean vipMember) {
        this.vipMember = vipMember;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", sdt='" + sdt + '\'' +
                ", birthday='" + birthday + '\'' +
                ", vipMember=" + vipMember +
                ", apiToken='" + apiToken + '\'' +
                '}';
    }
}
