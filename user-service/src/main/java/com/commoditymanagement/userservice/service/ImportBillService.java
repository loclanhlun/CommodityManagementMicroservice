package com.commoditymanagement.userservice.service;

import com.commoditymanagement.core.data.User;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.response.ImportBillResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ImportBillService {

    List<ImportBillResponse> findAllImportBill();

    List<ImportBillResponse> searchImportBillByImportDateAndWarehouseCode(String fromDate, String toDate, String warehouseCode);

    void save(AddImportBillRequest request, User user) throws Exception;

    void delete();

}
