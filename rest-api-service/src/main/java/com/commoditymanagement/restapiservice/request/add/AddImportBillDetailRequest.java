package com.commoditymanagement.restapiservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AddImportBillDetailRequest {

    @JsonProperty
    private List<ImportBillDetailRequest> data;

    public List<ImportBillDetailRequest> getData() {
        return data;
    }

    public void setData(List<ImportBillDetailRequest> data) {
        this.data = data;
    }
}
