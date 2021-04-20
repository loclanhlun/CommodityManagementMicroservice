package com.commoditymanagement.restapiservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AddExportBillRequest {

    @NotBlank(message = "Please enter Warehouse!")
    @JsonProperty
    private String warehouseCode;

    @NotBlank(message = "Please enter Agency!")
    @JsonProperty
    private String agencyCode;

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }
}
