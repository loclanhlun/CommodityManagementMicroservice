package com.commoditymanagement.userservice.request.get;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchByNameAndStatus {

    @JsonProperty
    private String name;

    @JsonProperty
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
