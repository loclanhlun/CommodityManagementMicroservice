package com.commoditymanagement.restapiservice.request.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class EditAgencyRequest {

    @JsonProperty
    private Long id;

    @NotBlank(message = "Agency Code is mandatory")
    @JsonProperty
    private String code;

    @NotBlank(message = "Agency name is mandatory")
    @JsonProperty
    private String name;

    @NotBlank(message = "Agency address is mandatory")
    @JsonProperty
    private String address;

    @NotBlank(message = "Agency phone number is mandatory")
    @JsonProperty
    private String phoneNumber;

    @JsonProperty
    private int status;

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
