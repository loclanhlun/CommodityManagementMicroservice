package com.commoditymanagement.restapiservice.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignInRequest {

	@NotBlank(message = "Please enter your email")
	@Email
    private String email;

	@NotBlank(message = "Please enter your password")
    private String password;

    public SignInRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
