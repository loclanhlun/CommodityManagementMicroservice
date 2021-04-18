package com.commoditymanagement.warehouseservice.service;

import com.commoditymanagement.core.data.CommodityWarehouse;
import com.commoditymanagement.warehouseservice.request.add.AddImportBillRequest;

import java.util.List;

public interface CommodityWarehouseService {
    List<CommodityWarehouse> findAllCommodityWarehouse();

    void save(AddImportBillRequest request);
}
