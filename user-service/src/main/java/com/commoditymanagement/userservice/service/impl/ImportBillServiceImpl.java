package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.*;
import com.commoditymanagement.userservice.repository.*;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.request.add.ItemImportBillDetailRequest;
import com.commoditymanagement.userservice.response.ImportBillResponse;
import com.commoditymanagement.userservice.service.ImportBillService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImportBillServiceImpl implements ImportBillService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CommodityRepository commodityRepository;


    @Autowired
    private CommodityWarehouseRepository commodityWarehouseRepository;

    @Autowired
    private ImportBillDetailRepository importBillDetailRepository;

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

        ImportBill importBill1 = importBillRepository.findImportBillOderByIdDesc().get(0);
        List<ItemImportBillDetailRequest> listImportBillDetail = request.getData();
        if(listImportBillDetail.size() == 0){
            throw new Exception("Bạn chưa nhập hàng!");
        }
        Commodity commodity = null;

        int count = 0;
        int count1 = 0;
        for(ItemImportBillDetailRequest item : listImportBillDetail){
            if(checkCommodityCode(item.getCommodityCode())){
                throw new Exception("Commodity " + item.getCommodityCode() + "is not found!");
            }
            count ++;
        }

        if(count == listImportBillDetail.size()){
            for(ItemImportBillDetailRequest item : listImportBillDetail){
                commodity = commodityRepository.findByCode(item.getCommodityCode()).get(0);
                if(item.getQuantity() < 0){
                    throw new Exception("Số lượng của " + commodity.getName() + " phải từ 0 trở lên");
                }
                count1++;
            }
        }
        double totalPrice = 0;
        if(count1 == listImportBillDetail.size()){
            Warehouse warehouses = warehouseRepository.findById(importBill.getWarehouses().getId()).orElse(null);
            for(ItemImportBillDetailRequest item : listImportBillDetail){
                commodity = commodityRepository.findByCode(item.getCommodityCode()).get(0);
                ImportBillDetail importBillDetail = new ImportBillDetail();
                importBillDetail.setImportBill(importBill);
                importBillDetail.setCommodities(commodity);
                importBillDetail.setPrice(item.getPrice());
                importBillDetail.setQuantity(item.getQuantity());
                totalPrice += item.getQuantity() * item.getPrice().doubleValue();
                importBillDetailRepository.save(importBillDetail);
                //
                CommodityWarehouse commodityWarehouse = new CommodityWarehouse();
                if(checkCommodityWarehouseByCommodityCodeAndWarehouseCode(commodity,warehouses)){
                    commodityWarehouse.setCommodity(commodity);
                    commodityWarehouse.setWarehouse(warehouses);
                    commodityWarehouse.setQuantity(item.getQuantity());
                    commodityWarehouseRepository.save(commodityWarehouse);
                }else{
                    CommodityWarehouse oldCommodityWarehouses = commodityWarehouseRepository.findCommodityWarehouseByCommodityAndWarehouse(commodity, warehouses).get(0);
                    oldCommodityWarehouses.setQuantity(oldCommodityWarehouses.getQuantity() + item.getQuantity());
                    commodityWarehouseRepository.save(oldCommodityWarehouses);
                }
            }
            importBill1.setTotalPrice(BigDecimal.valueOf(totalPrice));
            importBillRepository.save(importBill1);
        }
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

    public boolean checkCommodityWarehouseByCommodityCodeAndWarehouseCode(Commodity commodity, Warehouse warehouse){
        List<CommodityWarehouse> commodityWarehouses = commodityWarehouseRepository.findCommodityWarehouseByCommodityAndWarehouse(commodity, warehouse);
        if(CollectionUtils.isEmpty(commodityWarehouses)){
            return true;
        }
        return false;
    }

    public boolean checkCommodityCode(String code){
        List<Commodity> commodity = commodityRepository.findByCode(code);
        if(CollectionUtils.isEmpty(commodity)){
            return true;
        }
        return false;
    }


}
