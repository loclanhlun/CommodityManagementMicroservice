package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.request.add.AddExportDetailRequest;
import com.commoditymanagement.userservice.service.ExportBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/v1/authenticate/export-bill-detail")
public class ExportBillDetailController {
    
    @Autowired
    private ExportBillDetailService exportBillDetailService;

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<?> getListExportBillDetailByExportBillId(@PathVariable("id") Long exportBillId){
        return ResponseEntity.ok("1");
    }

    @PostMapping(value = "/add-export-bill-detail")
    public ResponseEntity<?> addExportBillDetail(@RequestBody AddExportDetailRequest request){
        ResponseModel responseModel;
        try {
            exportBillDetailService.save(request);
            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        }catch (Exception e){
            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(responseModel);
    }
}
