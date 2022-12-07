package com.example.android_ket_cau_soft.api.request;

public class SignUpRequest extends BaseRequest {

				public SignUpRequest(String email, String name, String password, String password1) {
								this.email = email;
								this.name = name;
								this.password = password;
								this.password1 = password1;
				}

				@Override
				public String toString() {
								return "SignInRequest{" +
												"email='" + email + '\'' +
												", name='" + name + '\'' +
												", password='" + password + '\'' +
												", password1='" + password1 + '\'' +
												'}';
				}
}
