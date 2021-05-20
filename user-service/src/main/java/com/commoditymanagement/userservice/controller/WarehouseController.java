package com.commoditymanagement.userservice.controller;
import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.request.add.AddWarehouseRequest;
import com.commoditymanagement.userservice.request.edit.EditWarehouseRequest;
import com.commoditymanagement.userservice.request.get.SearchByNameAndStatus;
import com.commoditymanagement.userservice.response.CommodityResponse;
import com.commoditymanagement.userservice.response.WarehouseResponse;
import com.commoditymanagement.userservice.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/admin/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;


    @GetMapping(value = "/list")
    public ResponseEntity<?> getListWarehouse() throws Exception {
        List<WarehouseResponse> listsWarehouse = warehouseService.findAllWarehouse();
        ResponseModel response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, listsWarehouse);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getWarehouseById(@PathVariable("id") Long warehouseId){
        ResponseModel response;
        WarehouseResponse warehouseResponse = null;
        try {
            warehouseResponse = warehouseService.findById(warehouseId);
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, warehouseResponse);
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
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
        }catch (Exception e){
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> searchWarehouseByLikeNameAndStatus(@RequestBody SearchByNameAndStatus request) throws Exception {
        List<WarehouseResponse> lists = warehouseService.searchAllWarehouse(request);
        ResponseModel response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,"Success", lists);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/edit-warehouse")
    public ResponseEntity<?> editWarehouse(@RequestBody EditWarehouseRequest request){
        ResponseModel response;
        try {
            warehouseService.update(request);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
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
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
        }catch (Exception e){
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }
}
