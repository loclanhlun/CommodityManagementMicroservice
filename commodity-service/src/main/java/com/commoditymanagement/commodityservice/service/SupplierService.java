package com.commoditymanagement.commodityservice.service;

import com.commoditymanagement.commodityservice.request.add.AddSupplierRequest;
import com.commoditymanagement.commodityservice.request.edit.EditSupplierRequest;
import com.commoditymanagement.commodityservice.response.SupplierResponse;
import com.commoditymanagement.core.data.Supplier;

import java.util.List;

public interface SupplierService {

    List<SupplierResponse> findAllSupplier() throws Exception;

    SupplierResponse findById(Long supplierId) throws Exception;

    void save(AddSupplierRequest request) throws Exception;

    void update(EditSupplierRequest request) throws Exception;

    void remove(Long id) throws Exception;
}
