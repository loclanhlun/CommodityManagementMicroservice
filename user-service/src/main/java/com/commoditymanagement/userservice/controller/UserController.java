package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.userservice.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.jwt.JwtUtils;
import com.commoditymanagement.userservice.request.SignInRequest;
import com.commoditymanagement.userservice.request.UserInput;
import com.commoditymanagement.userservice.request.UserRequest;
import com.commoditymanagement.userservice.response.JwtResponse;
import com.commoditymanagement.userservice.service.UserService;
import com.commoditymanagement.userservice.service.impl.UserDetailImpl;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/authenticate/user")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListUser(){
		List<UserResponse> listUser = userService.findAll();
        ResponseModel response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", listUser);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Validated @RequestBody SignInRequest request){
    	Authentication authentication = 
    			authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(
    					request.getEmail(), 
    					request.getPassword()));
  
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	String jwt = jwtUtils.generateJwtToken(authentication);
    	UserDetailImpl userDetailImpl = (UserDetailImpl) authentication.getPrincipal();
    	JwtResponse jwtResponse = addResponse(userDetailImpl,jwt);
    	ResponseModel responseModel = buildResponse(jwtResponse);
    	return ResponseEntity.ok(responseModel);
    }
    
    @PostMapping(value = "/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserRequest request){
    	ResponseModel response = new ResponseModel();
    	try {
    		userService.addUser(request);
    		response.setMessage("Success");
    		response.setResultCode(ResponseConstant.RESULT_CODE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
    		response.setResultCode(ResponseConstant.RESULT_CODE_ERROR);
		}
    	return ResponseEntity.ok(response);
    }
    
    private JwtResponse addResponse(UserDetailImpl userDetailImpl, String token) {
    	JwtResponse jwtResponse = new JwtResponse();
    	jwtResponse.setId(userDetailImpl.getId());
    	jwtResponse.setEmail(userDetailImpl.getEmail());
    	jwtResponse.setToken(token);
    	jwtResponse.setRole(userDetailImpl.getRoleCode());
    	return jwtResponse;
    }
    
    private ResponseModel buildResponse(JwtResponse jwtResponse) {
    	ResponseModel responseModel = new ResponseModel();
    	responseModel.setMessage("Success");
    	responseModel.setResultCode("0");
    	responseModel.setObject(jwtResponse);
    	return responseModel;
    }
    
    
    

    
    

}
