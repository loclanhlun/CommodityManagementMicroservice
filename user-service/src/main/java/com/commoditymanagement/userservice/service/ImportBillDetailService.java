package com.commoditymanagement.userservice.service;

import com.commoditymanagement.userservice.request.add.AddImportBillDetailRequest;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.response.ImportBillDetailResponse;

import java.util.List;

public interface ImportBillDetailService {

    List<ImportBillDetailResponse> findAllImportBillDetailByImportBillId(Long id);

    void save(AddImportBillDetailRequest request) throws Exception;
}
