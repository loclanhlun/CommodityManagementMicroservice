package com.commoditymanagement.userservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUserRequest {

    @JsonProperty
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
