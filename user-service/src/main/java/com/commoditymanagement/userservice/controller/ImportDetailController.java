package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.request.add.AddImportBillDetailRequest;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.response.ImportBillDetailResponse;
import com.commoditymanagement.userservice.service.ImportBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/import-bill-detail")
public class ImportDetailController {

    @Autowired
    private ImportBillDetailService importBillDetailService;

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<?> getListImportBillDetail(@PathVariable("id") Long importBillId){
        ResponseModel responseModel;
        List<ImportBillDetailResponse> listImportBillDetail = importBillDetailService.findAllImportBillDetailByImportBillId(importBillId);
        responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, listImportBillDetail);
        return ResponseEntity.ok(responseModel);
    }

//    @PostMapping(value = "/add-import-bill-detail")
//    public ResponseEntity<?> addImportBill(@RequestBody AddImportBillDetailRequest request) throws Exception {
//        ResponseModel responseModel;
//        try {
//            importBillDetailService.save(request);
//            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS,null);
//        }catch (Exception e){
//            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(),null);
//        }
//
//        return ResponseEntity.ok(responseModel);
//    }
}
