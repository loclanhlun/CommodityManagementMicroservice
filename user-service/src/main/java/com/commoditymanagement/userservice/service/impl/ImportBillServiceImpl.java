package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.ImportBill;
import com.commoditymanagement.core.data.Supplier;
import com.commoditymanagement.core.data.User;
import com.commoditymanagement.core.data.Warehouse;
import com.commoditymanagement.userservice.repository.ImportBillRepository;
import com.commoditymanagement.userservice.repository.SupplierRepository;
import com.commoditymanagement.userservice.repository.UserRepository;
import com.commoditymanagement.userservice.repository.WarehouseRepository;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.service.ImportBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImportBillServiceImpl implements ImportBillService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ImportBillRepository importBillRepository;

    @Override
    public void save(AddImportBillRequest request, User user) {
        List<Supplier> supplier = supplierRepository.findByCode(request.getSupplierCode());
        List<Warehouse> warehouse = warehouseRepository.findByCode(request.getWarehouseCode());
        //TODO: Import Bill
        ImportBill importBill = new ImportBill();
        importBill.setUser(user);
        importBill.setSupplier(supplier.get(0));
        importBill.setWarehouses(warehouse.get(0));
        importBill.setImportDate(new Date());
        importBill.setStatus(0);
        importBillRepository.save(importBill);
    }
}
