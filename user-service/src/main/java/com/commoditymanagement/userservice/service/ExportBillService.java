package com.commoditymanagement.userservice.service;

import com.commoditymanagement.core.data.User;
import com.commoditymanagement.userservice.request.add.AddExportBillRequest;
import com.commoditymanagement.userservice.response.ExportBillResponse;

import java.util.List;

public interface ExportBillService {

    List<ExportBillResponse> findAllExportBill();

    void save(AddExportBillRequest request, User user) throws Exception;
}
