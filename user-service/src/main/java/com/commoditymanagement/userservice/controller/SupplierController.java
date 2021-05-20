package com.commoditymanagement.userservice.controller;


import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.request.add.AddSupplierRequest;
import com.commoditymanagement.userservice.request.edit.EditSupplierRequest;
import com.commoditymanagement.userservice.request.get.SearchByNameAndStatus;
import com.commoditymanagement.userservice.response.CommodityResponse;
import com.commoditymanagement.userservice.response.SupplierResponse;
import com.commoditymanagement.userservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/admin/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListSupplier() throws Exception {
        List<SupplierResponse> listsSupplier = supplierService.findAllSupplier();
        ResponseModel response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, listsSupplier);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> searchSupplierByLikeNameAndStatus(@RequestBody SearchByNameAndStatus request) throws Exception {
        List<SupplierResponse> lists = supplierService.searchAllSupplier(request);
        ResponseModel response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,"Success", lists);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable("id") Long supplierId){
        ResponseModel response;
        SupplierResponse supplierResponse = null;
        try {
            supplierResponse = supplierService.findById(supplierId);
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, supplierResponse);
        }catch (Exception e){
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), supplierResponse);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/add-supplier")
    public ResponseEntity<?> addSupplier(@RequestBody AddSupplierRequest request){
        ResponseModel response;
        try {
            supplierService.save(request);
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
        }catch (Exception e){
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/edit-supplier")
    public ResponseEntity<?> editSupplier(@RequestBody EditSupplierRequest request) {
        ResponseModel response;
        try {
            supplierService.update(request);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
        } catch (Exception e) {
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/remove-supplier/{id}")
    public ResponseEntity<?> removeSupplier(@PathVariable("id") Long supplierId){
        ResponseModel response;
        try {
            supplierService.remove(supplierId);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
        }catch (Exception e){
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }
}
