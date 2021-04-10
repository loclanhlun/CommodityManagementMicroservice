package com.commoditymanagement.restapiservice.controller;

import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.restapiservice.request.AddCategoryRequest;
import com.commoditymanagement.restapiservice.request.EditCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/rest/v1/category")
public class CategoryController {
    private static final String URL = "http://user-service/rest/v1/authenticate/category";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListCategory(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = URL + "/list";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }

    @PostMapping(value = "/add-category")
    public ResponseEntity<?> addCategory(HttpServletRequest httpServletRequest,
                                         @RequestBody AddCategoryRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = URL + "/add-category";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/edit-category/{id}")
    public ResponseEntity<?> editCategory(HttpServletRequest httpServletRequest,
                                         @PathVariable("id") Long categoryId,
                                         @RequestBody EditCategoryRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = URL + "/edit-category/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", categoryId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, ResponseModel.class, params);
        return response;
    }
}
