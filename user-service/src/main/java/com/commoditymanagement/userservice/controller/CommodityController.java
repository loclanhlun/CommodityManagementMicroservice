package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.request.add.AddCommodityRequest;
import com.commoditymanagement.userservice.request.get.GetResultById;
import com.commoditymanagement.userservice.request.edit.EditCommodityRequest;
import com.commoditymanagement.userservice.request.get.SearchByNameAndStatus;
import com.commoditymanagement.userservice.response.CommodityResponse;
import com.commoditymanagement.userservice.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/admin/commodity")

public class CommodityController {

    @Autowired
    private CommodityService commodityService;


    @GetMapping(value = "/list")
    public ResponseEntity<?> getListCommodity(){
        List<CommodityResponse> lists = commodityService.findAllCommodity();
        ResponseModel response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,"Success", lists);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/list-commodity")
    public ResponseEntity<?> getListCommodityByCategoryId(@RequestBody GetResultById request){
        List<CommodityResponse> lists = commodityService.findAllByCategoryId(request);
        ResponseModel response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,"Success", lists);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> searchCommodityByLikeNameAndStatus(@RequestBody SearchByNameAndStatus request){
        List<CommodityResponse> lists = commodityService.searchAllCommodity(request);
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
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,ResponseConstant.MESSAGE_SUCCESS,  null);
        }catch (Exception e){
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(),  null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/edit-commodity")
    public ResponseEntity<?> editCommodity( @RequestBody EditCommodityRequest request){
        ResponseModel response;
        try {
            commodityService.update(request);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,ResponseConstant.MESSAGE_SUCCESS,null);
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
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,ResponseConstant.MESSAGE_SUCCESS,null);
        }catch (Exception e){
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR,e.getMessage(),null);
        }
        return ResponseEntity.ok(response);
    }


}
