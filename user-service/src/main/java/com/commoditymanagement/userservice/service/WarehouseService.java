package com.commoditymanagement.userservice.service;

import com.commoditymanagement.core.data.Warehouse;
import com.commoditymanagement.userservice.request.add.AddWarehouseRequest;
import com.commoditymanagement.userservice.request.edit.EditWarehouseRequest;
import com.commoditymanagement.userservice.request.get.SearchByNameAndStatus;
import com.commoditymanagement.userservice.response.WarehouseResponse;

import java.util.List;

public interface WarehouseService {

    List<WarehouseResponse> findAllWarehouse() throws Exception;

    WarehouseResponse findById(Long warehouseId) throws Exception;

    List<WarehouseResponse> searchAllWarehouse(SearchByNameAndStatus request) throws Exception;

    void save(AddWarehouseRequest request) throws Exception;

    void update(EditWarehouseRequest request) throws Exception;

    void remove(Long id) throws Exception;

}
