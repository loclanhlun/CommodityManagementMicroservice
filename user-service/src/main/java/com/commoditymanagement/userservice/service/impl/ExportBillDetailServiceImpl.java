package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.*;
import com.commoditymanagement.userservice.repository.*;
import com.commoditymanagement.userservice.request.add.AddExportDetailRequest;
import com.commoditymanagement.userservice.request.add.ItemExportDetailRequest;
import com.commoditymanagement.userservice.request.add.ItemImportBillDetailRequest;
import com.commoditymanagement.userservice.response.ExportBillDetailResponse;
import com.commoditymanagement.userservice.response.ExportBillResponse;
import com.commoditymanagement.userservice.service.ExportBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    public List<ExportBillDetailResponse> findAllByExportBillId(Long exportBillId) {
        List<ExportBillDetailResponse> listResponse = new ArrayList<>();
        List<ExportBillDetail> listEntity = exportBillDetailRepository.findByExportBillId(exportBillId);
        for(ExportBillDetail item : listEntity){
            ExportBillDetailResponse response = new ExportBillDetailResponse();
            response.setId(item.getId());
            response.setCommodityName(item.getCommodity().getName());
            response.setQuantity(item.getQuantity());
            response.setPrice(item.getPrice());
            listResponse.add(response);
        }
        return listResponse;
    }

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
                    throw new Exception("Mã hàng hóa " + item.getCommodityCode() + "Không tồn tại!");
                }
                if(checkQuantity(item.getQuantity(), commodity, warehouse)){
                    throw new Exception("Số lượng của " + commodity.getName() + " trong kho không đủ!");
                }
                if(item.getQuantity() < 0){
                    throw new Exception("Số lượng của " + commodity.getName() + " phải từ 0 trở lên!");
                }
                count1 ++;
            }
        }

        double totalPrice = 0;
        if(count1 == listExportBillDetail.size()){
            for(ItemExportDetailRequest item : listExportBillDetail){
                commodity = commodityRepository.findByCode(item.getCommodityCode()).get(0);
                //add export bill detail
                ExportBillDetail exportBillDetail = new ExportBillDetail();
                exportBillDetail.setExportBill(exportBill);
                exportBillDetail.setCommodity(commodity);
                exportBillDetail.setPrice(item.getPrice());
                exportBillDetail.setQuantity(item.getQuantity());
                totalPrice += item.getQuantity() * item.getPrice().doubleValue();
                exportBillDetailRepository.save(exportBillDetail);
                //update commodity warehouse
                CommodityWarehouse oldCommodityWarehouses = commodityWarehouseRepository.findCommodityWarehouseByCommodityAndWarehouse(commodity, warehouse).get(0);
                oldCommodityWarehouses.setQuantity(oldCommodityWarehouses.getQuantity() - item.getQuantity());
                commodityWarehouseRepository.save(oldCommodityWarehouses);
            }
        }
        exportBill.setTotalPrice(BigDecimal.valueOf(totalPrice));
        exportBillRepository.save(exportBill);
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

    public boolean checkQuantity(int quantity, Commodity commodity, Warehouse warehouse){
        List<CommodityWarehouse> commodityWarehouses = commodityWarehouseRepository.findCommodityWarehouseByCommodityAndWarehouse(commodity, warehouse);
        if(quantity > commodityWarehouses.get(0).getQuantity()){
            return true;
        }
        return false;
    }
}
