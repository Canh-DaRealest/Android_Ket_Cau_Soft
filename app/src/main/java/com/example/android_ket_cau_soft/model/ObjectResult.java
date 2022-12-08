package com.example.android_ket_cau_soft.model;

import java.util.List;

public class ObjectResult {
    private String name;

    private List<?> listData;
    private Object data;
    private int dataType =0;

    public ObjectResult(String name, List<?> listData, int dataType) {
        this.name = name;
        this.listData = listData;
        this.dataType = dataType;
    }

    public ObjectResult(String name, List<?> listData) {
        this.name = name;
        this.listData = listData;
    }


    public ObjectResult(String name, Object data, int dataType) {
        this.name = name;
        this.data = data;
        this.dataType = dataType;
    }


    public ObjectResult(String name, Object data) {
        this.name = name;
        this.data = data;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
