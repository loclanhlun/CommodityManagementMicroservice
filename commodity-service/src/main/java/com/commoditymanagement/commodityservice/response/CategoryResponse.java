package com.commoditymanagement.commodityservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryResponse {

	@JsonProperty
	private String name;

	@JsonProperty
	private String code;

	@JsonProperty
	private int status;

	public CategoryResponse() {
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
