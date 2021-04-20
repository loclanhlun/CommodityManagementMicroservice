package com.commoditymanagement.userservice.service;

import com.commoditymanagement.core.data.User;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.response.ImportBillResponse;

import java.util.List;

public interface ImportBillService {

    List<ImportBillResponse> findAllImportBill();

    void save(AddImportBillRequest request, User user) throws Exception;

}
