package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.request.add.AddAgencyRequest;
import com.commoditymanagement.userservice.request.edit.EditAgencyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/v1/authenticate/supplier")
public class SupplierController {
    private static final String URL = "http://commodity-service/rest/v1/supplier";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListSupplier(){
        String url = URL + "/list";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable("id") Long supplierId){
        String url = URL + "/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> params = new HashMap<>();
        params.put("id", supplierId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class, params);
        return response;
    }

    @PostMapping(value = "/add-supplier")
    public ResponseEntity<?> addSupplier(@RequestBody AddAgencyRequest request){
        String url = URL + "/add-supplier";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/edit-supplier/{id}")
    public ResponseEntity<?> editSupplier(@PathVariable("id") Long supplierId,
                                        @RequestBody EditAgencyRequest request){
        String url = URL + "/edit-supplier/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> params = new HashMap<>();
        params.put("id", supplierId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT,httpEntity, ResponseModel.class, params);
        return response;
    }

    @PutMapping(value = "/remove-supplier/{id}")
    public ResponseEntity<?> removeAgency(@PathVariable("id") Long supplierId){
        String url = URL + "/remove-supplier/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> params = new HashMap<>();
        params.put("id", supplierId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT,httpEntity, ResponseModel.class, params);
        return response;
    }
}
