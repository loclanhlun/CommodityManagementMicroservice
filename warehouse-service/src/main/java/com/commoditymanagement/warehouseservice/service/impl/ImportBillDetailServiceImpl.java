package com.commoditymanagement.warehouseservice.service.impl;

import com.commoditymanagement.core.data.ImportBillDetail;
import com.commoditymanagement.warehouseservice.request.add.AddImportBillRequest;
import com.commoditymanagement.warehouseservice.service.ImportBillDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImportBillDetailServiceImpl implements ImportBillDetailService {
    @Override
    public List<ImportBillDetail> findAllImportBillDetailByImportBillId(Long id) {
        return null;
    }

    @Override
    public void save(AddImportBillRequest request) {

    }
}
