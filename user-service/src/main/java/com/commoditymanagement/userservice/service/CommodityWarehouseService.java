package com.commoditymanagement.userservice.service;

import com.commoditymanagement.userservice.request.edit.EditCommodityWarehouseRequest;
import com.commoditymanagement.userservice.response.CommodityWarehouseResponse;

import java.util.List;

public interface CommodityWarehouseService {
    List<CommodityWarehouseResponse> findAllCommodityWarehouse();

    List<CommodityWarehouseResponse> findAllCommodityWarehouseByCategoryId(Long categoryId);

    List<CommodityWarehouseResponse> findAllCommodityWarehouseByWarehouseCode(String warehouseCode);

    CommodityWarehouseResponse findPriceCommodityByCommodityCodeAndWarehouseCode(String commodityCode, String warehouseCode);

    CommodityWarehouseResponse findById(Long id)throws Exception;

    void update(EditCommodityWarehouseRequest request);


}
