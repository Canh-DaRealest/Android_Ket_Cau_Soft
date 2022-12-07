package com.example.android_ket_cau_soft.model;

import java.util.List;

public class ObjectResult {
				private String name;

				private List<?> listData;

	public ObjectResult(String name, List<?> listData) {
		this.name = name;
		this.listData = listData;
	}

	public ObjectResult() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<?> getListData() {
		return listData;
	}

	public void setListData(List<?> listData) {
		this.listData = listData;
	}
}
