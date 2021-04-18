package com.commoditymanagement.commodityservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestFromImportBill {

    @JsonProperty
    private String warehouseCode;

    @JsonProperty
    private String supplierCode;

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
}
