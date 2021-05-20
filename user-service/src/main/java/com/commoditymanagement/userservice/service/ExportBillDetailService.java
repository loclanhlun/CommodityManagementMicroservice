package com.commoditymanagement.userservice.service;

import com.commoditymanagement.userservice.request.add.AddExportDetailRequest;
import com.commoditymanagement.userservice.response.ExportBillDetailResponse;

import java.util.List;

public interface ExportBillDetailService {

    List<ExportBillDetailResponse> findAllByExportBillId(Long exportBillId);

    void save(AddExportDetailRequest request) throws Exception;
}
