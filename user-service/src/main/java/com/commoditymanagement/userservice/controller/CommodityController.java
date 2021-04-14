package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.request.add.AddCommodityRequest;
import com.commoditymanagement.userservice.request.edit.EditCommodityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/v1/authenticate/commodity")
public class CommodityController {

    private static final String URL = "http://commodity-service/rest/v1/commodity";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListCommodity(){
        String url = URL + "/list";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCommodityById(@PathVariable("id") Long commodityId){
        String url = URL + "/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> params = new HashMap<>();
        params.put("id", commodityId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class, params);
        return response;
    }

    @PostMapping(value = "/add-commodity")
    public ResponseEntity<?> addCommodity(@RequestBody AddCommodityRequest request){
        String url = URL + "/add-commodity";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/edit-commodity/{id}")
    public ResponseEntity<?> addCommodity(@PathVariable("id") Long commodityId, @RequestBody EditCommodityRequest request){
        String url = URL + "/edit-commodity/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> params = new HashMap<>();
        params.put("id", commodityId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT,httpEntity, ResponseModel.class,params);
        return response;
    }

    @PutMapping(value = "/remove-commodity/{id}")
    public ResponseEntity<?> removeCommodity(@PathVariable("id") Long commodityId){

        String url = URL + "/remove-commodity/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> params = new HashMap<>();
        params.put("id", commodityId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, ResponseModel.class, params);
        return response;
    }
}
