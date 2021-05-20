package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.*;
import com.commoditymanagement.userservice.repository.CommodityRepository;
import com.commoditymanagement.userservice.repository.ImportBillRepository;
import com.commoditymanagement.userservice.repository.SupplierRepository;
import com.commoditymanagement.userservice.repository.WarehouseRepository;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.response.ImportBillResponse;
import com.commoditymanagement.userservice.response.ItemStatisticalImportBill;
import com.commoditymanagement.userservice.response.StatisticalImportBillResponse;
import com.commoditymanagement.userservice.service.ImportBillService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public List<ImportBillResponse> findAllImportBill() {
        List<ImportBillResponse> listResult = new ArrayList<>();
        List<ImportBill> listEntity = importBillRepository.findAll();
        for(ImportBill item : listEntity){
            ImportBillResponse response = new ImportBillResponse();
            response.setId(item.getId());
            response.setFullName(item.getUser().getFullName());
            response.setSupplierName(item.getSupplier().getName());
            response.setWarehouseName(item.getWarehouses().getName());
            response.setImportDate(formatter.format(item.getImportDate()));
            response.setTotalPrice(item.getTotalPrice());
            listResult.add(response);
        }
        return listResult;
    }

    @Override
    public List<ImportBillResponse> searchImportBillByImportDateAndWarehouseCode(String fromDate, String toDate, String warehouseCode) {
        List<ImportBillResponse> importBillResponses = new ArrayList<>();
        if(StringUtils.isEmpty(warehouseCode)){
            List<ImportBill> importBills = importBillRepository.findImportBillByImportDateNamedParams(fromDate,toDate);
            for(ImportBill item : importBills){
                ImportBillResponse response = new ImportBillResponse();
                response.setId(item.getId());
                response.setFullName(item.getUser().getFullName());
                response.setSupplierName(item.getSupplier().getName());
                response.setWarehouseName(item.getWarehouses().getName());
                response.setImportDate(formatter.format(item.getImportDate()));
                response.setTotalPrice(item.getTotalPrice());
                importBillResponses.add(response);
            }
        }else {
            List<ImportBill> importBills = importBillRepository.findImportBillByWarehouseCodeAndImportDate(fromDate,toDate,warehouseCode);
            for(ImportBill item : importBills){
                ImportBillResponse response = new ImportBillResponse();
                response.setId(item.getId());
                response.setFullName(item.getUser().getFullName());
                response.setSupplierName(item.getSupplier().getName());
                response.setWarehouseName(item.getWarehouses().getName());
                response.setImportDate(formatter.format(item.getImportDate()));
                response.setTotalPrice(item.getTotalPrice());
                importBillResponses.add(response);
            }
        }
        return importBillResponses;
    }

    @Override
    public List<StatisticalImportBillResponse> statisticalImportBillByYear(String year) {
        List<StatisticalImportBillResponse> listResponse = new ArrayList<>();
        List<ImportBill> listEntity = importBillRepository.statisticalImportBillByYear(year);

        for(int month = 1; month <= 12 ; month ++){
            StatisticalImportBillResponse response = new StatisticalImportBillResponse();
            ItemStatisticalImportBill itemStatisticalImportBill = new ItemStatisticalImportBill();
            response.setMonth(month);
            itemStatisticalImportBill.setTotalPrice(BigDecimal.valueOf(0));
            for(ImportBill item : listEntity){
                if(item.getImportDate().getMonth()+1 == month){
                    itemStatisticalImportBill.setTotalPrice(item.getTotalPrice());
                }
            }
            response.setData(itemStatisticalImportBill);

            listResponse.add(response);
        }
        return listResponse;
    }


    @Override
    public void save(AddImportBillRequest request, User user) throws Exception {
        validateRequest(request.getWarehouseCode(),request.getSupplierCode());
        List<Supplier> supplier = supplierRepository.findByCode(request.getSupplierCode());
        List<Warehouse> warehouse = warehouseRepository.findByCode(request.getWarehouseCode());
        ImportBill importBill = new ImportBill();
        importBill.setUser(user);
        importBill.setSupplier(supplier.get(0));
        importBill.setWarehouses(warehouse.get(0));
        importBill.setImportDate(new Date());
        importBillRepository.save(importBill);
    }

    @Override
    public void delete() {
        ImportBill importBill = importBillRepository.findImportBillOderByIdDesc().get(0);
        importBillRepository.delete(importBill);
    }

    public void validateRequest(String warehouseCode, String supplierCode)throws Exception{
        if(checkWarehouseCode(warehouseCode) && checkSupplierCode(supplierCode)){
            throw new Exception("Invalid Warehouse and Supplier");
        }
        if(checkWarehouseCode(warehouseCode)){
            throw new Exception("Invalid Warehouse");
        }
        if(checkSupplierCode(supplierCode)){
            throw new Exception("Invalid Supplier");
        }

    }

    public boolean checkWarehouseCode(String code){
        List<Warehouse> warehouses = warehouseRepository.findByCode(code);
        if(CollectionUtils.isEmpty(warehouses)){
            return true;
        }
        return false;
    }

    public boolean checkSupplierCode(String code){
        List<Supplier> suppliers = supplierRepository.findByCode(code);
        if(CollectionUtils.isEmpty(suppliers)){
            return true;
        }
        return false;
    }


}
