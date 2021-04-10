package com.commoditymanagement.restapiservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtResponse {
	
	@JsonProperty
	private String token;
	
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String role;
	
	
	public JwtResponse() {
	
	}
	
	public JwtResponse(String token, Long id, String email, String role) {
		this.token = token;
		this.id = id;
		this.email = email;
		this.role = role;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
