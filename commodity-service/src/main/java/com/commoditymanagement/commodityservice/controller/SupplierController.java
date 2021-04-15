package com.commoditymanagement.commodityservice.controller;

import com.commoditymanagement.commodityservice.request.add.AddSupplierRequest;
import com.commoditymanagement.commodityservice.request.edit.EditSupplierRequest;
import com.commoditymanagement.commodityservice.response.SupplierResponse;
import com.commoditymanagement.commodityservice.service.SupplierService;
import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListSupplier() throws Exception {
        List<SupplierResponse> listsSupplier = supplierService.findAllSupplier();
        ResponseModel response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", listsSupplier);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable("id") Long supplierId){
        ResponseModel response;
        SupplierResponse supplierResponse = null;
        try {
            supplierResponse = supplierService.findById(supplierId);
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", supplierResponse);
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
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        }catch (Exception e){
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/edit-supplier/{id}")
    public ResponseEntity<?> editSupplier(@PathVariable("id") Long supplierId,
                                          @RequestBody EditSupplierRequest request) {
        ResponseModel response;
        try {
            supplierService.update(request);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
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
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        }catch (Exception e){
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }
}
