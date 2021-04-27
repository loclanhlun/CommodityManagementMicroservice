package com.commoditymanagement.userservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddImportBillRequest {

    @JsonProperty
    private String warehouseCode;

    @JsonProperty
    private String supplierCode;

    @JsonProperty
    private List<ItemImportBillDetailRequest> data;

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public List<ItemImportBillDetailRequest> getData() {
        return data;
    }

    public void setData(List<ItemImportBillDetailRequest> data) {
        this.data = data;
    }
}
