package com.commoditymanagement.warehouseservice.service.impl;

import com.commoditymanagement.core.data.CommodityWarehouse;
import com.commoditymanagement.warehouseservice.request.add.AddImportBillRequest;
import com.commoditymanagement.warehouseservice.service.CommodityWarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityWarehouseServiceImpl implements CommodityWarehouseService {
    @Override
    public List<CommodityWarehouse> findAllCommodityWarehouse() {
        return null;
    }

    @Override
    public void save(AddImportBillRequest request) {

    }
}
