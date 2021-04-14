package com.commoditymanagement.commodityservice.controller;

import com.commoditymanagement.commodityservice.request.add.AddCommodityRequest;
import com.commoditymanagement.commodityservice.request.edit.EditCommodityRequest;
import com.commoditymanagement.commodityservice.response.CommodityResponse;
import com.commoditymanagement.commodityservice.service.CommodityService;
import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;


    @GetMapping(value = "/list")
    public ResponseEntity<?> getListCommodity(){
        List<CommodityResponse> lists = commodityService.findAllCommodity();
        ResponseModel response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,"Success", lists);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCommodityById(@PathVariable("id") Long commodityId) throws Exception {
        ResponseModel response;
        CommodityResponse commodityResponse = null;
        try{
            commodityResponse = commodityService.findById(commodityId);
            response = new ResponseModel("Success", ResponseConstant.RESULT_CODE_SUCCESS,commodityResponse);
        }catch (Exception e){
            response = new ResponseModel(e.getMessage(), ResponseConstant.RESULT_CODE_SUCCESS,commodityResponse);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/add-commodity")
    public ResponseEntity<?> addCommodity(@RequestBody AddCommodityRequest request)throws Exception{
        ResponseModel response;
        try {
            commodityService.save(request);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,"Success",  null);
        }catch (Exception e){
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(),  null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/edit-commodity/{id}")
    public ResponseEntity<?> editCommodity(@PathVariable("id") Long commodityId,
                                           @RequestBody EditCommodityRequest request){
        ResponseModel response;
        try {
            commodityService.update(request, commodityId);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,"Success",null);
        } catch (Exception e) {
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(),null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/remove-commodity/{id}")
    public ResponseEntity<?> removeCommodity(@PathVariable("id") Long commodityId){
        ResponseModel response;
        try {
            commodityService.remove(commodityId);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,"Success",null);
        }catch (Exception e){
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR,e.getMessage(),null);
        }
        return ResponseEntity.ok(response);
    }


}
