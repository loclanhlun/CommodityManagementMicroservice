package com.commoditymanagement.restapiservice.request;

import javax.validation.constraints.NotBlank;

public class AddUserRequest {
	
	@NotBlank(message = "Password is mandatory")
	private String password;

	@NotBlank(message = "Full name is mandatory")
    private String fullName;

	@NotBlank(message = "Email is mandatory")
    private String email;

    private int gender;

	@NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    private String address;

    @NotBlank(message = "Role is mandatory")
    private String roleCode;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

    
}
