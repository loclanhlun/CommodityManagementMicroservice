package com.commoditymanagement.userservice.request.get;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchImportBillByDateRequest {

    @JsonProperty
    private String fromDate;

    @JsonProperty
    private String toDate;

    @JsonProperty
    private String warehouseCode;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
}
