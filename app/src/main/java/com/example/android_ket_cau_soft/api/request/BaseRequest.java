package com.example.android_ket_cau_soft.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseRequest implements Serializable {

				@SerializedName("email")
				@Expose
				protected String email;
				@SerializedName("name")
				@Expose
				protected String name;
				@SerializedName("password")
				@Expose
				protected String password;
				@SerializedName("password1")
				@Expose
				protected String password1;

				public String getEmail() {
								return email;
				}

				public void setEmail(String email) {
								this.email = email;
				}

				public String getName() {
								return name;
				}

				public void setName(String name) {
								this.name = name;
				}

				public String getPassword() {
								return password;
				}

				public void setPassword(String password) {
								this.password = password;
				}

				public String getPassword1() {
								return password1;
				}

				public void setPassword1(String password1) {
								this.password1 = password1;
				}

}
