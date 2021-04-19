package com.commoditymanagement.userservice.request.add;


import com.fasterxml.jackson.annotation.JsonProperty;

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
