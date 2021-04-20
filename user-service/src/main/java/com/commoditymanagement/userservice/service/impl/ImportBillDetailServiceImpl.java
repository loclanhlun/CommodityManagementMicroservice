package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.*;
import com.commoditymanagement.userservice.repository.*;
import com.commoditymanagement.userservice.request.add.AddImportBillDetailRequest;
import com.commoditymanagement.userservice.request.add.ItemImportBillDetailRequest;
import com.commoditymanagement.userservice.response.ImportBillDetailResponse;
import com.commoditymanagement.userservice.service.ImportBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportBillDetailServiceImpl implements ImportBillDetailService {

    @Autowired
    private ImportBillRepository importBillRepository;

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CommodityWarehouseRepository commodityWarehouseRepository;

    @Autowired
    private ImportBillDetailRepository importBillDetailRepository;

    @Override
    public List<ImportBillDetailResponse> findAllImportBillDetailByImportBillId(Long id) {
        List<ImportBillDetailResponse> listResponse = new ArrayList<>();
        List<ImportBillDetail> listEntity = importBillDetailRepository.findByImportBillId(id);
        for(ImportBillDetail item : listEntity){
            ImportBillDetailResponse response = new ImportBillDetailResponse();
            response.setId(item.getId());
            response.setImportBillId(item.getImportBill().getId());
            response.setCommodityName(item.getCommodities().getName());
            response.setQuantity(item.getQuantity());
            response.setPrice(item.getPrice());
            listResponse.add(response);
        }
        return listResponse;
    }

    @Override
    public void save(AddImportBillDetailRequest request) throws Exception {
        ImportBill importBill = importBillRepository.findImportBillOderByIdDesc().get(0);
        List<ItemImportBillDetailRequest> listImportBillDetail = request.getData();
        Commodity commodity = null;

        int count = 0;
        for(ItemImportBillDetailRequest item : listImportBillDetail){
            if(checkCommodityCode(item.getCommodityCode())){
                throw new Exception("Commodity " + item.getCommodityCode() + "is not found!");
            }
            count ++;
        }
        if(count == listImportBillDetail.size()){
            Warehouse warehouse = warehouseRepository.findById(importBill.getWarehouses().getId()).orElse(null);
            for(ItemImportBillDetailRequest item : listImportBillDetail){
                commodity = commodityRepository.findByCode(item.getCommodityCode()).get(0);
                ImportBillDetail importBillDetail = new ImportBillDetail();
                importBillDetail.setImportBill(importBill);
                importBillDetail.setCommodities(commodity);
                importBillDetail.setPrice(item.getPrice());
                importBillDetail.setQuantity(item.getQuantity());
                importBillDetailRepository.save(importBillDetail);
                //

                CommodityWarehouse commodityWarehouse = new CommodityWarehouse();
                if(checkCommodityWarehouseByCommodityCodeAndWarehouseCode(commodity,warehouse)){
                    commodityWarehouse.setCommodity(commodity);
                    commodityWarehouse.setWarehouse(warehouse);
                    commodityWarehouse.setQuantity(item.getQuantity());
                    commodityWarehouseRepository.save(commodityWarehouse);
                }else{
                    CommodityWarehouse oldCommodityWarehouses = commodityWarehouseRepository.findCommodityWarehouseByCommodityAndWarehouse(commodity, warehouse).get(0);
                    oldCommodityWarehouses.setQuantity(oldCommodityWarehouses.getQuantity() + item.getQuantity());
                    commodityWarehouseRepository.save(oldCommodityWarehouses);
                }
            }
        }
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
