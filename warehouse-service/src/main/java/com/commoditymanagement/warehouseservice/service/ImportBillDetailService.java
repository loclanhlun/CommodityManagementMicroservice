package com.commoditymanagement.warehouseservice.service;

import com.commoditymanagement.core.data.ImportBillDetail;
import com.commoditymanagement.warehouseservice.request.add.AddImportBillRequest;

import java.util.List;

public interface ImportBillDetailService {
    List<ImportBillDetail> findAllImportBillDetailByImportBillId(Long id);

    void save(AddImportBillRequest request);
}
