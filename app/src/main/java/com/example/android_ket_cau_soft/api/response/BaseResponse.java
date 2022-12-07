package com.example.android_ket_cau_soft.api.response;


import com.example.android_ket_cau_soft.model.UserData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class BaseResponse {

				@SerializedName("status")
				@Expose
				protected Boolean status;

				@SerializedName("msg")
				@Expose
				protected String msg;

				@SerializedName("data")
				@Expose
				protected UserData userData;



				public UserData getData() {
								return userData;
				}

				public void setData(UserData userData) {
								this.userData = userData;
				}



				public String getMsg() {
								return msg;
				}

				protected void setMsg(String msg) {
								this.msg = msg;
				}

				public Boolean getStatus() {
								return status;
				}

				public void setStatus(Boolean status) {
								this.status = status;
				}
}
