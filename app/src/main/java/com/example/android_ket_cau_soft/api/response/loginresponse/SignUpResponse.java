package com.example.android_ket_cau_soft.api.response.loginresponse;



import com.example.android_ket_cau_soft.api.response.BaseResponse;

import java.io.Serializable;

public class SignUpResponse extends BaseResponse implements Serializable {

				public SignUpResponse(Boolean status, String msg) {
								this.status = status;
								this.msg = msg;
				}

}