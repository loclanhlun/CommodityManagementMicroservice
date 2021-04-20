package com.commoditymanagement.userservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String fullName;

    @JsonProperty
    private String email;

    @JsonProperty
    private String address;

    @JsonProperty
    private String phoneNumber;

    @JsonProperty
    private int gender;

    @JsonProperty
    private String role;

    @JsonProperty
    private int status;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
