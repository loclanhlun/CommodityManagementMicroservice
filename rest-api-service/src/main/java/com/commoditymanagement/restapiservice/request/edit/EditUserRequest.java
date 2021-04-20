package com.commoditymanagement.restapiservice.request.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class EditUserRequest {

    @JsonProperty
    private Long id;

    @NotBlank(message = "Please enter password!")
    @JsonProperty
    private String password;

    @NotBlank(message = "Please enter full name!")
    @JsonProperty
    private String fullName;

    @NotBlank(message = "Please enter phone number!")
    @JsonProperty
    private String phoneNumber;

    @NotBlank(message = "Please enter address!")
    @JsonProperty
    private String address;

    @JsonProperty
    private int gender;

    @NotBlank(message = "Please enter role!")
    @JsonProperty
    private String roleCode;

    @JsonProperty
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
