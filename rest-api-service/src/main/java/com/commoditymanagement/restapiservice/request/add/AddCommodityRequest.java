package com.commoditymanagement.restapiservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AddCommodityRequest {
    @NotBlank(message = "Commodity Code is mandatory")
    @JsonProperty
    private String code;

    @NotBlank(message = "Commodity name is mandatory")
    @JsonProperty
    private String name;


    @JsonProperty
    private Long categoryId;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
