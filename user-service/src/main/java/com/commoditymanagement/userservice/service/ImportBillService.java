package com.commoditymanagement.userservice.service;

import com.commoditymanagement.core.data.User;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.response.ImportBillResponse;
import com.commoditymanagement.userservice.response.StatisticalImportBillResponse;

import java.util.List;

public interface ImportBillService {

    List<ImportBillResponse> findAllImportBill();

    List<ImportBillResponse> searchImportBillByImportDateAndWarehouseCode(String fromDate, String toDate, String warehouseCode);

//    List<ImportBillResponse> searchImportBillByWarehouseCodeAndImportDate(String fromDate, String toDate, String warehouseCode);

    List<StatisticalImportBillResponse> statisticalImportBillByYear(String year);

    void save(AddImportBillRequest request, User user) throws Exception;

    void delete();

}
