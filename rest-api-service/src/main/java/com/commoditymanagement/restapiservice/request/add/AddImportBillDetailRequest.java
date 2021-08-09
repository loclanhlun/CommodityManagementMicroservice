package com.commoditymanagement.restapiservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddImportBillDetailRequest {
    @JsonProperty
    private List<ItemImportBillDetailRequest> data;

    public List<ItemImportBillDetailRequest> getData() {
        return data;
    }

    public void setData(List<ItemImportBillDetailRequest> data) {
        this.data = data;
    }
}
