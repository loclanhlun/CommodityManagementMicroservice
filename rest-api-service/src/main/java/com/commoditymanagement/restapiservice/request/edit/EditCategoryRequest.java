package com.commoditymanagement.restapiservice.request.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class EditCategoryRequest {

    @JsonProperty
    private Long id;

    @NotBlank(message = "Category Code is mandatory")
    @JsonProperty
    private String code;

    @NotBlank(message = "Category Name is mandatory")
    @JsonProperty
    private String name;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
