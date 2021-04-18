package com.commoditymanagement.userservice.controller;
import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.request.add.AddWarehouseRequest;
import com.commoditymanagement.userservice.request.edit.EditWarehouseRequest;
import com.commoditymanagement.userservice.response.WarehouseResponse;
import com.commoditymanagement.userservice.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/authenticate/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListWarehouse() throws Exception {
        List<WarehouseResponse> listsWarehouse = warehouseService.findAllWarehouse();
        ResponseModel response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", listsWarehouse);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getWarehouseById(@PathVariable("id") Long warehouseId){
        ResponseModel response;
        WarehouseResponse warehouseResponse = null;
        try {
            warehouseResponse = warehouseService.findById(warehouseId);
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", warehouseResponse);
        }catch (Exception e){
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), warehouseResponse);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/add-warehouse")
    public ResponseEntity<?> addWarehouse(@RequestBody AddWarehouseRequest request){
        ResponseModel response;
        try {
            warehouseService.save(request);
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        }catch (Exception e){
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/edit-warehouse/{id}")
    public ResponseEntity<?> editWarehouse(@PathVariable("id") Long warehouseId,
                                           @RequestBody EditWarehouseRequest request){
        ResponseModel response;
        try {
            warehouseService.update(request);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        } catch (Exception e) {
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/remove-warehouse/{id}")
    public ResponseEntity<?> removeWarehouse(@PathVariable("id") Long warehouseId){
        ResponseModel response;
        try {
            warehouseService.remove(warehouseId);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        }catch (Exception e){
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }
}
