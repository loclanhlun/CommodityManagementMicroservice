package com.commoditymanagement.userservice.request.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditAgencyRequest {

    @JsonProperty
    private String code;

    @JsonProperty
    private String name;

    @JsonProperty
    private String address;

    @JsonProperty
    private String phoneNumber;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
