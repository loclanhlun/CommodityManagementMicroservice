package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.data.User;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.jwt.JwtUtils;
import com.commoditymanagement.userservice.request.add.AddExportBillRequest;
import com.commoditymanagement.userservice.service.ExportBillService;
import com.commoditymanagement.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/v1/authenticate/export-bill")
public class ExportBillController {

    @Autowired
    private ExportBillService exportBillService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping(value = "/list")
    public ResponseEntity<?> getListExportBill(){
        return ResponseEntity.ok("1");
    }

    @PostMapping(value = "/add-export-bill")
    public ResponseEntity<?> addExportBill(HttpServletRequest httpServletRequest,
                                           @RequestBody AddExportBillRequest request){
        ResponseModel responseModel;
        String jwt = parseJwt(httpServletRequest);
        String email = jwtUtils.getEmailFormJwtToken(jwt);
        User user = userService.findByEmail(email);
        try {
            exportBillService.save(request, user);
            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, "Success", null);
        }catch (Exception e){
            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
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
