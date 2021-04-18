package com.commoditymanagement.restapiservice.request.add;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddImportBillRequest {

    @JsonProperty
    private String warehouseCode;

    @JsonProperty
    private String supplierCode;

//    private List<AddImportBillDetailRequest> importBillDetailRequest;

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

//    public List<AddImportBillDetailRequest> getImportBillDetailRequest() {
//        return importBillDetailRequest;
//    }
//
//    public void setImportBillDetailRequest(List<AddImportBillDetailRequest> importBillDetailRequest) {
//        this.importBillDetailRequest = importBillDetailRequest;
//    }
}
