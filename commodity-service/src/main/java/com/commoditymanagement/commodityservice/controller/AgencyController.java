package com.commoditymanagement.commodityservice.controller;

import com.commoditymanagement.commodityservice.request.add.AddAgencyRequest;
import com.commoditymanagement.commodityservice.request.edit.EditAgencyRequest;
import com.commoditymanagement.commodityservice.response.AgencyResponse;
import com.commoditymanagement.commodityservice.service.AgencyService;
import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/agency")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;


    @GetMapping(value = "/list")
    public ResponseEntity<?> getListAgency() throws Exception {
        List<AgencyResponse> listsAgency = agencyService.findAllAgency();
        ResponseModel response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", listsAgency);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAgencyById(@PathVariable("id") Long agencyId) throws Exception {
        ResponseModel response;
        AgencyResponse agencyResponse = null;
        try {
            agencyResponse = agencyService.findById(agencyId);
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", agencyResponse);
        }catch (Exception e){
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), agencyResponse);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/add-agency")
    public ResponseEntity<?> addAgency(@RequestBody AddAgencyRequest request){
        ResponseModel response;
        try {
            agencyService.save(request);
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        }catch (Exception e){
            response  = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/edit-agency/{id}")
    public ResponseEntity<?> editAgency(@PathVariable("id") Long agencyId,@RequestBody EditAgencyRequest request){
        ResponseModel response;
        try {
            agencyService.update(request,agencyId);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        }catch (Exception e){
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/remove-agency/{id}")
    public ResponseEntity<?> removeAgency(@PathVariable("id") Long agencyId){
        ResponseModel response;
        try {
            agencyService.remove(agencyId);
            response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        }catch (Exception e){
            response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(response);
    }
}
