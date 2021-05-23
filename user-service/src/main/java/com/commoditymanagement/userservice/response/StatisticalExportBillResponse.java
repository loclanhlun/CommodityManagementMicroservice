package com.commoditymanagement.userservice.response;

public class StatisticalExportBillResponse {

    private int month;

    private Object data;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
