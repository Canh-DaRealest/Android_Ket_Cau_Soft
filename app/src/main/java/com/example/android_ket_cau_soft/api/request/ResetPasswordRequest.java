package com.example.android_ket_cau_soft.api.request;

import java.io.Serializable;

public class ResetPasswordRequest extends  BaseRequest implements Serializable {


				public ResetPasswordRequest(String email,String password, String password1) {
								this.email = email;
								this.password = password;
								this.password1 = password1;
				}

				@Override
				public String toString() {
								return "SignInRequest{" +
												"email='" + email + '\'' +
												", password='" + password + '\'' +
												", password1='" + password1 + '\'' +
												'}';
				}
}
