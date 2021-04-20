package com.commoditymanagement.userservice.service;

import com.commoditymanagement.userservice.request.add.AddExportDetailRequest;

public interface ExportBillDetailService {

    void save(AddExportDetailRequest request) throws Exception;
}
