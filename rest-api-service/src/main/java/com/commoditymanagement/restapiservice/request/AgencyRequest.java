package com.commoditymanagement.restapiservice.request;

public class AgencyRequest {

    private String code;

    private String name;

    public AgencyRequest(){

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
