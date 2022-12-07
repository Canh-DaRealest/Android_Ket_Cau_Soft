package com.example.android_ket_cau_soft.api.request;

import java.io.Serializable;

public class LoginRequest extends  BaseRequest implements Serializable {


				public LoginRequest(String email, String password) {
								this.email = email;
								this.password = password;
				}


				@Override
				public String toString() {
								return "LoginRequest{" +
												"email='" + email + '\'' +
												", password='" + password + '\'' +
												'}';
				}
}
