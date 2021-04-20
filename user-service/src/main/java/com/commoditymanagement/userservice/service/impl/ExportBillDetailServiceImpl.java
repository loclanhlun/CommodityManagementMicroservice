package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.*;
import com.commoditymanagement.userservice.repository.*;
import com.commoditymanagement.userservice.request.add.AddExportDetailRequest;
import com.commoditymanagement.userservice.request.add.ItemExportDetailRequest;
import com.commoditymanagement.userservice.request.add.ItemImportBillDetailRequest;
import com.commoditymanagement.userservice.service.ExportBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ExportBillDetailServiceImpl implements ExportBillDetailService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ExportBillRepository exportBillRepository;

    @Autowired
    private CommodityWarehouseRepository commodityWarehouseRepository;

    @Autowired
    private ExportBillDetailRepository exportBillDetailRepository;

    @Override
    public void save(AddExportDetailRequest request) throws Exception {
        ExportBill exportBill = exportBillRepository.findExportBillOderByIdDesc().get(0);
        List<ItemExportDetailRequest> listExportBillDetail = request.getData();
        Commodity commodity = null;

        Warehouse warehouse = warehouseRepository.findById(exportBill.getWarehouse().getId()).orElse(null);
        int count = 0;
        int count1 = 0;
        for(ItemExportDetailRequest item : listExportBillDetail){
            if(checkCommodityCode(item.getCommodityCode())){
                throw new Exception("Commodity " + item.getCommodityCode() + "is not found!");
            }
            count ++;
        }
        if(count == listExportBillDetail.size()){
            for(ItemExportDetailRequest item : listExportBillDetail){
                commodity = commodityRepository.findByCode(item.getCommodityCode()).get(0);
                if(checkCommodityWarehouseByCommodityCodeAndWarehouseCode(commodity,warehouse)){
                    throw new Exception("Commodity " + item.getCommodityCode() + "is not found in this warehouse!");
                }
                count1 ++;
            }
        }
        if(count1 == listExportBillDetail.size()){
            for(ItemExportDetailRequest item : listExportBillDetail){
                commodity = commodityRepository.findByCode(item.getCommodityCode()).get(0);
                //add export bill detail
                ExportBillDetail exportBillDetail = new ExportBillDetail();
                exportBillDetail.setExportBill(exportBill);
                exportBillDetail.setCommodity(commodity);
                exportBillDetail.setPrice(item.getPrice());
                exportBillDetail.setQuantity(item.getQuantity());
                exportBillDetailRepository.save(exportBillDetail);
                //update commodity warehouse
                CommodityWarehouse oldCommodityWarehouses = commodityWarehouseRepository.findCommodityWarehouseByCommodityAndWarehouse(commodity, warehouse).get(0);
                oldCommodityWarehouses.setQuantity(oldCommodityWarehouses.getQuantity() - item.getQuantity());
                commodityWarehouseRepository.save(oldCommodityWarehouses);
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
