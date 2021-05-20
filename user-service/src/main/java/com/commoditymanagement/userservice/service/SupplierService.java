package com.commoditymanagement.userservice.service;

import com.commoditymanagement.userservice.request.add.AddSupplierRequest;
import com.commoditymanagement.userservice.request.edit.EditSupplierRequest;
import com.commoditymanagement.userservice.request.get.SearchByNameAndStatus;
import com.commoditymanagement.userservice.response.SupplierResponse;

import java.util.List;

public interface SupplierService {

    List<SupplierResponse> findAllSupplier() throws Exception;

    SupplierResponse findById(Long supplierId) throws Exception;

    List<SupplierResponse> searchAllSupplier(SearchByNameAndStatus request) throws Exception;

    void save(AddSupplierRequest request) throws Exception;

    void update(EditSupplierRequest request) throws Exception;

    void remove(Long id) throws Exception;
}
