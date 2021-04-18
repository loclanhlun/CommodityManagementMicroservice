package com.commoditymanagement.warehouseservice.service.impl;

import com.commoditymanagement.core.data.*;
import com.commoditymanagement.warehouseservice.repository.ImportBillRepository;
import com.commoditymanagement.warehouseservice.request.add.AddImportBillRequest;
import com.commoditymanagement.warehouseservice.response.ImportBillResponse;
import com.commoditymanagement.warehouseservice.service.ImportBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImportBillServiceImpl implements ImportBillService {

    @Autowired
    private ImportBillRepository importBillRepository;

    @Override
    public List<ImportBill> findAllImportBill() {
        List<ImportBillResponse> responses = new ArrayList<>();
        List<ImportBill> listImportBill = importBillRepository.findAll();
        return null;
    }

    @Override
    public void save(AddImportBillRequest request, Warehouse warehouse, User user, Supplier supplier)throws Exception {
        ImportBill importBill = new ImportBill();
        importBill.setWarehouses(warehouse);
        importBill.setUser(user);
        importBill.setSupplier(supplier);
        importBill.setImportDate(new Date());
        importBillRepository.save(importBill);
    }










}
