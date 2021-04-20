package com.commoditymanagement.restapiservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AddImportBillRequest {

    @NotBlank(message = "Warehouse is mandatory!")
    @JsonProperty
    private String warehouseCode;

    @NotBlank(message = "Supplier is mandatory!")
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
