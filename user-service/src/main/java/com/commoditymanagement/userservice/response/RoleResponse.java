package com.commoditymanagement.userservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String code;

    @JsonProperty
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
