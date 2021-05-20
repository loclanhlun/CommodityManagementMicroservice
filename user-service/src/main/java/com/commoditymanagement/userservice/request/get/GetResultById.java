package com.commoditymanagement.userservice.request.get;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetResultById {

    @JsonProperty
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
