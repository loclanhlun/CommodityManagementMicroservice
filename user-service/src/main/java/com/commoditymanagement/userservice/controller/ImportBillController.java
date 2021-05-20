package com.commoditymanagement.userservice.controller;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.data.User;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.userservice.jwt.JwtUtils;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.request.get.SearchImportBillByDateRequest;
import com.commoditymanagement.userservice.response.ImportBillResponse;
import com.commoditymanagement.userservice.response.StatisticalImportBillResponse;
import com.commoditymanagement.userservice.service.ImportBillDetailService;
import com.commoditymanagement.userservice.service.ImportBillService;
import com.commoditymanagement.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/import-bill")
public class ImportBillController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ImportBillService importBillService;

    @Autowired
    private ImportBillDetailService importBillDetailService;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListImportBill(){
        ResponseModel response;
        List<ImportBillResponse> listImportBill = importBillService.findAllImportBill();
        response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, listImportBill);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> searchImportBill(@RequestBody SearchImportBillByDateRequest request){
        List<ImportBillResponse> list = importBillService.searchImportBillByImportDateAndWarehouseCode(request.getFromDate(),request.getToDate(), request.getWarehouseCode());
        ResponseModel response = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, list);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/statistical-import-bill")
    public ResponseEntity<?> statisticalImportBillByYear(){
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String year = String.valueOf(localDate.getYear());
        List<StatisticalImportBillResponse> list = importBillService.statisticalImportBillByYear(year);
        ResponseModel responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, list);
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping(value = "/delete-import-bill")
    public ResponseEntity<?> deleteImportBill(){
        importBillService.delete();
        ResponseModel responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);

        return ResponseEntity.ok(responseModel);
    }

    @PostMapping(value = "/add-import-bill")
    public ResponseEntity<?> addImportBill(HttpServletRequest httpServletRequest,
                                           @RequestBody AddImportBillRequest request) throws Exception {

        ResponseModel responseModel ;
        String jwt = parseJwt(httpServletRequest);
        String email = jwtUtils.getEmailFormJwtToken(jwt);
        User user = userService.findByEmail(email);
        try {
            importBillService.save(request,user);
            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS, ResponseConstant.MESSAGE_SUCCESS, null);
        }catch (Exception e){
            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR,e.getMessage(), null);
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
