package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.data.User;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.jwt.JwtUtils;
import com.commoditymanagement.userservice.request.add.AddExportBillRequest;
import com.commoditymanagement.userservice.request.get.SearchImportBillByDateRequest;
import com.commoditymanagement.userservice.response.ExportBillResponse;
import com.commoditymanagement.userservice.response.ImportBillResponse;
import com.commoditymanagement.userservice.service.ExportBillService;
import com.commoditymanagement.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/export-bill")
public class ExportBillController {

    @Autowired
    private ExportBillService exportBillService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping(value = "/list")
    public ResponseEntity<?> getListExportBill(){
        List<ExportBillResponse> list = exportBillService.findAllExportBill();
        ResponseModel responseModel;
        responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, list);
        return ResponseEntity.ok(responseModel);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> searchExportBill(@RequestBody SearchImportBillByDateRequest request){
        List<ExportBillResponse> list = exportBillService.searchExportBillByExportDateAndWarehouseCode(request.getFromDate(),request.getToDate(), request.getWarehouseCode());
        ResponseModel response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, list);
        return ResponseEntity.ok(response);
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
            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
        }catch (Exception e){
            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR, e.getMessage(), null);
        }
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping(value = "/delete-export-bill")
    public ResponseEntity<?> deleteExportBillById(){
        exportBillService.delete();
        ResponseModel responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
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
