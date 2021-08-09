package com.commoditymanagement.restapiservice.controller;


import java.util.HashMap;
import java.util.Map;

import com.commoditymanagement.core.constant.UrlConstants;
import com.commoditymanagement.restapiservice.request.SearchByNameAndStatus;
import com.commoditymanagement.restapiservice.request.add.AddUserRequest;
import com.commoditymanagement.restapiservice.request.edit.EditUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.commoditymanagement.core.response.ResponseModel;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/v1/admin/user")
@CrossOrigin("http://localhost:8080")
public class UserController {
	

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListUser(HttpServletRequest httpServletRequest,
                                         @RequestParam(value = "fullName", required = false) String fullName,
                                         @RequestParam(value = "status", required = false) String status) {
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, String> params = new HashMap<>();
        params.put("fullName", fullName);
        params.put("status", status);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.GET_USER_LIST_URL, HttpMethod.GET,httpEntity, ResponseModel.class, params);
        return response;
    }

    @GetMapping(value = "/list-role")
    public ResponseEntity<?> getListRole(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.GET_ROLE_LIST_URL, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> searchWarehouseByLikeNameAndStatus(HttpServletRequest httpServletRequest,
                                                                @RequestBody SearchByNameAndStatus request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.SEARCH_USER, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserById(HttpServletRequest httpServletRequest,
                                         @PathVariable("id") Long userId ) {
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", userId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.GET_USER_BY_ID_URL, HttpMethod.GET,httpEntity, ResponseModel.class,params);
        return response;
    }


    @PostMapping(value = "/add-user")
    public ResponseEntity<?> addUser(HttpServletRequest httpServletRequest,@RequestBody AddUserRequest request){
    	String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request, headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.ADD_USER_URL, HttpMethod.POST,httpEntity, ResponseModel.class);
    	return response;
    }

    @PutMapping(value = "/edit-user")
    public ResponseEntity<?> editUser(HttpServletRequest httpServletRequest,
                                      @RequestBody EditUserRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request, headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.EDIT_USER_URL, HttpMethod.PUT,httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/remove-user/{id}")
    public ResponseEntity<?> removeUser(HttpServletRequest httpServletRequest,
                                      @PathVariable("id") Long userId){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", userId);
        HttpEntity<?> httpEntity  = new HttpEntity<>( headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.REMOVE_USER_BY_ID_URL, HttpMethod.PUT,httpEntity, ResponseModel.class,params);
        return response;
    }




}
