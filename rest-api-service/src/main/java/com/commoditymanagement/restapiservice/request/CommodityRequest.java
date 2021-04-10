package com.commoditymanagement.restapiservice.request;

public class CommodityRequest {

    private String code;

    private String name;

    private Long categoryCode;

    public CommodityRequest() {
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

    public Long getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Long categoryCode) {
        this.categoryCode = categoryCode;
    }
}
