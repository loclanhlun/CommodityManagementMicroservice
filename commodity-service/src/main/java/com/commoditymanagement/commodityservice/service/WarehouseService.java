package com.commoditymanagement.commodityservice.service;

import com.commoditymanagement.commodityservice.request.add.AddWarehouseRequest;
import com.commoditymanagement.commodityservice.request.edit.EditWarehouseRequest;
import com.commoditymanagement.commodityservice.response.WarehouseResponse;
import com.commoditymanagement.core.data.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<WarehouseResponse> findAllWarehouse() throws Exception;

    WarehouseResponse findById(Long warehouseId) throws Exception;

    Warehouse findByCodeFromImportBill(String warehouseCode) throws Exception;

    void save(AddWarehouseRequest request) throws Exception;

    void update(EditWarehouseRequest request) throws Exception;

    void remove(Long id) throws Exception;

}
