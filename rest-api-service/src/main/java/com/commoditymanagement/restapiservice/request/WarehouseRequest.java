package com.commoditymanagement.restapiservice.request;

public class WarehouseRequest {

    private String code;

    private String name;

    public WarehouseRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
