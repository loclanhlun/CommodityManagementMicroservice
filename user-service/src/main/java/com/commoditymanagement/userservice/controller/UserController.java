package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.userservice.request.edit.EditUserRequest;
import com.commoditymanagement.userservice.request.get.SearchByNameAndStatus;
import com.commoditymanagement.userservice.response.RoleResponse;
import com.commoditymanagement.userservice.response.UserResponse;
import com.commoditymanagement.userservice.service.impl.RoleServiceImpl;
import com.commoditymanagement.userservice.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.jwt.JwtUtils;
import com.commoditymanagement.userservice.request.SignInRequest;
import com.commoditymanagement.userservice.request.UserRequest;
import com.commoditymanagement.userservice.response.JwtResponse;
import com.commoditymanagement.userservice.service.UserService;
import com.commoditymanagement.userservice.service.impl.UserDetailImpl;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/admin/user")
public class UserController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
    @Autowired
    private UserService userService;

    @Autowired
	private RoleServiceImpl roleService;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
    
    

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListUser(@RequestParam(name = "fullName", required = false) String fullName,
										 @RequestParam(name = "status", required = false) String status)throws AccessDeniedException{
		ResponseModel response;
    	List<UserResponse> listUser = null;
    	try {
    		listUser = userService.findAll(fullName, status);
    		response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, listUser);
		} catch (AccessDeniedException e){
			response = new ResponseModel("401", e.getMessage(), null);
		} catch (Exception e){
    		response = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), listUser);
		}

		return ResponseEntity.ok(response);
    }

	@GetMapping(value = "/list-role")
	public ResponseEntity<?> getListRole(){
		List<RoleResponse> listRole = roleService.getAllRole();
		ResponseModel response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, listRole);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/search")
	public ResponseEntity<?> searchUserByLikeFullNameAndStatus(@RequestBody SearchByNameAndStatus request){
    	List<UserResponse> list = userService.searchAllUser(request);
    	ResponseModel responseModel = new ResponseModel(
    			ResponseConstant.RESULT_CODE_SUCCESS,ResponseConstant.MESSAGE_SUCCESS,list
		);
    	return ResponseEntity.ok(responseModel);
	}
    

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") long userId){
		ResponseModel responseModel;
		try {
			UserResponse userResponse = userService.findById(userId);
			responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,ResponseConstant.MESSAGE_SUCCESS, userResponse);
		}catch (Exception e){
			responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,e.getMessage(), null);
		}
		return ResponseEntity.ok(responseModel);
	}

//	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserRequest request){
    	ResponseModel response = new ResponseModel();
    	try {
    		userService.addUser(request);
    		response.setMessage(ResponseConstant.MESSAGE_SUCCESS);
    		response.setResultCode(ResponseConstant.RESULT_CODE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
    		response.setResultCode(ResponseConstant.RESULT_CODE_ERROR);
		}
    	return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/edit-user")
	public ResponseEntity<?> editUser(@RequestBody EditUserRequest request){
    	ResponseModel responseModel;
    	try {
			userService.updateUser(request);
			responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
		}catch (Exception e){
    		responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(),null);
		}

    	return ResponseEntity.ok(responseModel);
	}

	@PutMapping(value = "/remove-user/{id}")
	public ResponseEntity<?> removeUser(HttpServletRequest httpServletRequest,
										@PathVariable("id") Long userId){
		String jwt = parseJwt(httpServletRequest);
		String email = jwtUtils.getEmailFormJwtToken(jwt);
    	ResponseModel responseModel;

    	try {
			userService.removeUser(userId,email);
			responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
		}catch (Exception e){
			responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
		}
    	return ResponseEntity.ok(responseModel);
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
    	responseModel.setMessage(ResponseConstant.MESSAGE_SUCCESS);
    	responseModel.setResultCode("0");
    	responseModel.setObject(jwtResponse);
    	return responseModel;
    }

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}







}
