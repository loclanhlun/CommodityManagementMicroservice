package com.commoditymanagement.userservice.request.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditCategoryRequest {

    @JsonProperty
    private String code;

    @JsonProperty
    private String name;

    @JsonProperty
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
