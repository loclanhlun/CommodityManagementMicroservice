package com.commoditymanagement.restapiservice.controller;

import com.commoditymanagement.core.constant.UrlConstants;
import com.commoditymanagement.core.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import com.commoditymanagement.restapiservice.request.SignInRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/rest/v1")
@CrossOrigin("http://localhost:8080")
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Validated @RequestBody SignInRequest request ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request, headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.LOGIN_URL, HttpMethod.POST,httpEntity, ResponseModel.class);
        return ResponseEntity.ok(response.getBody());
    }
}
