package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.request.AddCategoryRequest;
import com.commoditymanagement.userservice.request.EditCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/v1/authenticate/category")
public class CategoryController {

    private static final String URL = "http://commodity-service/rest/v1/category";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListCategory(){
        String url = URL + "/list";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }

    @PostMapping(value = "/add-category")
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest request){
        String url = URL + "/add-category";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/edit-category/{id}")
    public ResponseEntity<?> addCategory(@PathVariable("id") Long categoryId, @RequestBody EditCategoryRequest request){
        String url = URL + "/edit-category/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> params = new HashMap<>();
        params.put("id", categoryId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT,httpEntity, ResponseModel.class,params);
        return response;
    }
}
