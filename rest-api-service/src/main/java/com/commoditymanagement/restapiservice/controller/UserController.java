package com.commoditymanagement.restapiservice.controller;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.commoditymanagement.restapiservice.request.edit.EditUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.restapiservice.request.add.AddUserRequest;
import com.commoditymanagement.restapiservice.request.SignInRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/v1/user")
@CrossOrigin("http://localhost:8080")
public class UserController {
	
	private static final String USER_SERVICE_URL = "http://user-service/rest/v1/authenticate/user/";
	

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListUser(HttpServletRequest httpServletRequest) {
        String url = USER_SERVICE_URL+"list";
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }

    @GetMapping(value = "/list-role")
    public ResponseEntity<?> getListRole(HttpServletRequest httpServletRequest){
        String url = USER_SERVICE_URL+"list-role";
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserById(HttpServletRequest httpServletRequest,
                                         @PathVariable("id") Long userId ) {
        String url = USER_SERVICE_URL+"{id}";
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", userId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class,params);
        return response;
    }
    
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Validated @RequestBody SignInRequest request ){
    	String url = USER_SERVICE_URL+"login";
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request, headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }


    @PostMapping(value = "/add-user")
    public ResponseEntity<?> addUser(HttpServletRequest httpServletRequest,@RequestBody AddUserRequest request){
    	String url = USER_SERVICE_URL+"add-user";
    	String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request, headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseModel.class);
    	return response;
    }

    @PutMapping(value = "/edit-user")
    public ResponseEntity<?> editUser(HttpServletRequest httpServletRequest,
                                      @RequestBody EditUserRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = USER_SERVICE_URL+"edit-user";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request, headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT,httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/remove-user/{id}")
    public ResponseEntity<?> removeUser(HttpServletRequest httpServletRequest,
                                      @PathVariable("id") Long userId){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = USER_SERVICE_URL+"remove-user/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", userId);
        HttpEntity<?> httpEntity  = new HttpEntity<>( headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT,httpEntity, ResponseModel.class,params);
        return response;
    }
    
 
  


}
