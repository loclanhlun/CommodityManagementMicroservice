package com.commoditymanagement.userservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddExportBillRequest {

    @JsonProperty
    private String warehouseCode;

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
