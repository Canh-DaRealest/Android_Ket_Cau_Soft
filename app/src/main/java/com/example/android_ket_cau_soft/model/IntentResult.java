package com.example.android_ket_cau_soft.model;

public class IntentResult {
    private Object name;

    private Object data;

    public IntentResult(Object name, Object listData) {
        this.name = name;
        this.data = listData;
    }

    public IntentResult() {
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
