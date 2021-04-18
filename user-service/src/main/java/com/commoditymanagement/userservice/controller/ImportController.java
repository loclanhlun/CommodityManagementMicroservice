package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.data.User;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.jwt.JwtUtils;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.service.ImportBillService;
import com.commoditymanagement.userservice.service.UserService;
import com.commoditymanagement.userservice.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/v1/authenticate/import-bill")
public class ImportController {

    private static final String URL = "http://warehouse-service/rest/v1/import-bill";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ImportBillService importBillService;

    @PostMapping(value = "/add-import-bill")
    public ResponseEntity<?> addImportBill(HttpServletRequest httpServletRequest,
                                           @RequestBody AddImportBillRequest request){

        ResponseModel responseModel ;
        String jwt = parseJwt(httpServletRequest);
        String email = jwtUtils.getEmailFormJwtToken(jwt);
        User user = userService.findByEmail(email);
        importBillService.save(request,user);
        responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        return ResponseEntity.ok(responseModel);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }

}
