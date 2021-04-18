package com.commoditymanagement.warehouseservice.service;

import com.commoditymanagement.core.data.ImportBill;
import com.commoditymanagement.core.data.Supplier;
import com.commoditymanagement.core.data.User;
import com.commoditymanagement.core.data.Warehouse;
import com.commoditymanagement.warehouseservice.request.add.AddImportBillRequest;

import java.util.List;

public interface ImportBillService {

    List<ImportBill> findAllImportBill();

    void save(AddImportBillRequest request, Warehouse warehouse, User user, Supplier supplier) throws Exception;
}
