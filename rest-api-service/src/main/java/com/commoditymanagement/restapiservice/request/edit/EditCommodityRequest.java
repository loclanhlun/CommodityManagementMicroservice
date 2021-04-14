package com.commoditymanagement.restapiservice.request.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class EditCommodityRequest {

    @NotBlank(message = "Commodity Code is mandatory")
    @JsonProperty
    private String code;

    @NotBlank(message = "Commodity name is mandatory")
    @JsonProperty
    private String name;

    @NotBlank(message = "Category is mandatory")
    @JsonProperty
    private Long categoryId;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
