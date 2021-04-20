package com.commoditymanagement.userservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImportBillResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String fullName;

    @JsonProperty
    private String supplierName;

    @JsonProperty
    private String warehouseName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
