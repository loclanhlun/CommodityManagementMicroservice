package com.commoditymanagement.userservice.service;

import com.commoditymanagement.userservice.request.add.AddImportBillDetailRequest;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;

public interface ImportBillDetailService {
    void save(AddImportBillDetailRequest request);
}
