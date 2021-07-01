package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.request.edit.EditCommodityWarehouseRequest;
import com.commoditymanagement.userservice.request.get.GetResultByCode;
import com.commoditymanagement.userservice.response.CommodityWarehouseResponse;
import com.commoditymanagement.userservice.service.CommodityWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/commodity-warehouse")
public class CommodityWarehouseController {

    @Autowired
    private CommodityWarehouseService commodityWarehouseService;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListCommodityWarehouse(){
        List<CommodityWarehouseResponse> list = commodityWarehouseService.findAllCommodityWarehouse();
        ResponseModel responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, list);

        return ResponseEntity.ok(responseModel);
    }

    @PostMapping(value = "/search-commodity-warehouse")
    public ResponseEntity<?> searchCommodityWarehouseByWarehouseId(@RequestBody GetResultByCode request){
        List<CommodityWarehouseResponse> list = commodityWarehouseService.findAllCommodityWarehouseByWarehouseCode(request.getCode());
        ResponseModel responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, list);
        return ResponseEntity.ok(responseModel);
    }

    @PostMapping(value = "/search-price")
    public ResponseEntity<?> searchPriceByCommodityCodeAndWarehouseCode(@RequestBody GetResultByCode request){
        CommodityWarehouseResponse commodityWarehouseResponse = commodityWarehouseService.findPriceCommodityByCommodityCodeAndWarehouseCode(request.getCode(), request.getCode2());
        ResponseModel responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, commodityWarehouseResponse);
        return ResponseEntity.ok(responseModel);
    }

    @GetMapping(value = "/list-commodity-warehouse")
    public ResponseEntity<?> getListCommodityWarehouseByCategoryId(@RequestParam(value = "categoryId", required = false) Long categoryId){
        List<CommodityWarehouseResponse> list = commodityWarehouseService.findAllCommodityWarehouseByCategoryId(categoryId);
        ResponseModel responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, list);

        return ResponseEntity.ok(responseModel);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCommodityWarehouseById(@PathVariable("id") Long id) throws Exception {
        CommodityWarehouseResponse commodityWarehouseResponse = commodityWarehouseService.findById(id);
        ResponseModel responseModel = new ResponseModel(
                ResponseConstant.RESULT_CODE_SUCCESS,ResponseConstant.MESSAGE_SUCCESS,commodityWarehouseResponse
        );
        return ResponseEntity.ok(responseModel);
    }

    @PutMapping(value = "/edit-commodity-warehouse")
    public ResponseEntity<?> editCommodityWarehouse(@RequestBody EditCommodityWarehouseRequest request){
        ResponseModel responseModel;
        try {
            commodityWarehouseService.update(request);
            responseModel = new ResponseModel(
                   ResponseConstant.RESULT_CODE_SUCCESS,ResponseConstant.MESSAGE_SUCCESS,null
           );
       }catch (Exception e){
            responseModel = new ResponseModel(
                    ResponseConstant.RESULT_CODE_SUCCESS,e.getMessage(),null
            );
       }
        return ResponseEntity.ok(responseModel);
    }
}
