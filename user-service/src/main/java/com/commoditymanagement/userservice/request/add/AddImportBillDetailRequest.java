package com.commoditymanagement.userservice.request.add;


import java.util.List;

public class AddImportBillDetailRequest {

    private Long billId;

    private List<ImportBillDetailRequest> data;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public List<ImportBillDetailRequest> getData() {
        return data;
    }

    public void setData(List<ImportBillDetailRequest> data) {
        this.data = data;
    }
}
