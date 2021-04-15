package com.commoditymanagement.commodityservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommodityResponse {

	@JsonProperty
	private Long id;

	@JsonProperty
	private String code;

	@JsonProperty
	private String name;

	@JsonProperty
	private String categoryName;

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
