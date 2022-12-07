package com.example.android_ket_cau_soft.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserData implements Serializable {

				@SerializedName("name")
				@Expose
				private String name;
				@SerializedName("email")
				@Expose
				private String email;
				@SerializedName("image")
				@Expose
				private String image;
				@SerializedName("sdt")
				@Expose
				private String sdt;
				@SerializedName("birthday")
				@Expose
				private String birthday;

				@SerializedName("vip_member")
				@Expose
				private Boolean vipMember;
				@SerializedName("vip_expdate")
				@Expose
				private String vipExpdate;
				@SerializedName("api_token")
				@Expose
				private String apiToken;

				public String getName() {
								return name;
				}

				public void setName(String name) {
								this.name = name;
				}

				public String getEmail() {
								return email;
				}

				public void setEmail(String email) {
								this.email = email;
				}

				public String getImage() {
								return image;
				}

				public void setImage(String image) {
								this.image = image;
				}

				public String getSdt() {
								return sdt;
				}

				public void setSdt(String sdt) {
								this.sdt = sdt;
				}

				public String getBirthday() {
								return birthday;
				}

				public void setBirthday(String birthday) {
								this.birthday = birthday;
				}

				public Boolean getVipMember() {
								return vipMember;
				}

				public void setVipMember(Boolean vipMember) {
								this.vipMember = vipMember;
				}

				public String getVipExpdate() {
								return vipExpdate;
				}

				public void setVipExpdate(String  vipExpdate) {
								this.vipExpdate = vipExpdate;
				}

				public String getApiToken() {
								return apiToken;
				}

				public void setApiToken(String apiToken) {
								this.apiToken = apiToken;
				}

				@Override
				public String toString() {
								return "Data{" +
												"name='" + name + '\'' +
												", email='" + email + '\'' +
												", image='" + image + '\'' +
												", sdt=" + sdt +
												", birthday=" + birthday +
												", vipMember=" + vipMember +
												", vipExpdate=" + vipExpdate +
												", apiToken='" + apiToken + '\'' +
												'}';
				}
}