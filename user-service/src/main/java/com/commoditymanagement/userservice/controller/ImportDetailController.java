package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.userservice.request.add.AddImportBillDetailRequest;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.service.ImportBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/v1/import-bill-detail")
public class ImportDetailController {

    @Autowired
    private ImportBillDetailService importBillDetailService;

    @PostMapping(value = "/add-import-bill-detail")
    public ResponseEntity<?> addImportBill(@RequestBody AddImportBillDetailRequest request){
        importBillDetailService.save(request);
        return null;
    }
}
