package com.example.android_ket_cau_soft.model;

public class Result {
				private boolean key;
				private String value;

				public Result(boolean key, String value) {
								this.key = key;
								this.value = value;
				}

				public Result() {
				}

				public boolean getKey() {
								return key;
				}

				public void setKey(boolean key) {
								this.key = key;
				}

				public String getValue() {
								return value;
				}

				public void setValue(String value) {
								this.value = value;
				}
}
