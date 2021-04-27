package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.jwt.JwtUtils;
import com.commoditymanagement.userservice.service.UserService;
import com.commoditymanagement.userservice.service.impl.RoleServiceImpl;
import com.commoditymanagement.userservice.service.impl.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.commoditymanagement.userservice.request.SignInRequest;
import com.commoditymanagement.userservice.request.UserRequest;
import com.commoditymanagement.userservice.response.JwtResponse;
import com.commoditymanagement.userservice.service.impl.UserDetailsServiceImpl;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/v1/")
public class loginController {

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


    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Validated @RequestBody SignInRequest request) throws AccessDeniedException, UsernameNotFoundException {
        ResponseModel responseModel = buildResponse();
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailImpl userDetailImpl = (UserDetailImpl) authentication.getPrincipal();
            JwtResponse jwtResponse = addResponse(userDetailImpl,jwt);
            responseModel.setObject(jwtResponse);

        } catch (AccessDeniedException e) {
            responseModel.setResultCode("401");

        } catch (UsernameNotFoundException e) {
            responseModel.setResultCode("404");
        } catch (Exception e) {
            responseModel.setResultCode("999");
            responseModel.setMessage(e.getMessage());
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

    private ResponseModel buildResponse() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage("Success");
        responseModel.setResultCode("0");
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
