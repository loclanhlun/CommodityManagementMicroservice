package com.commoditymanagement.warehouseservice.controller;

import com.commoditymanagement.core.constant.ResponseConstant;
import com.commoditymanagement.core.data.Supplier;
import com.commoditymanagement.core.data.User;
import com.commoditymanagement.core.data.Warehouse;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.warehouseservice.request.add.AddImportBillRequest;
import com.commoditymanagement.warehouseservice.response.SupplierResponse;
import com.commoditymanagement.warehouseservice.response.UserResponse;
import com.commoditymanagement.warehouseservice.response.WarehouseResponse;
import com.commoditymanagement.warehouseservice.service.ImportBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/v1/import-bill")
public class ImportBillController {

    private static final String URL_GET_WAREHOUSE = "http://commodity-service/rest/v1/warehouse/get-warehouse";
    private static final String URL_GET_USER = "http://user-service/rest/v1/authenticate/user/get-user";
    private static final String URL_GET_SUPPLIER = "http://commodity-service/rest/v1/supplier/get-supplier";

    @Autowired
    private ImportBillService importBillService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListImportBill(){

        return ResponseEntity.ok("1");
    }

    @PostMapping(value = "/add-import-bill")
    public ResponseEntity<?> addImportBill(HttpServletRequest httpServletRequest,
                                           @RequestBody AddImportBillRequest request) {
        ResponseModel responseModel;
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request, headers);
        ResponseEntity<Warehouse> warehouseResponseEntity = restTemplate.exchange(URL_GET_WAREHOUSE, HttpMethod.POST,httpEntity, Warehouse.class);
        ResponseEntity<Supplier> supplierResponseEntity = restTemplate.exchange(URL_GET_SUPPLIER, HttpMethod.POST,httpEntity, Supplier.class);
        ResponseEntity<User> userResponseEntity = restTemplate.exchange(URL_GET_USER, HttpMethod.POST,httpEntity, User.class);
        Warehouse warehouse = warehouseResponseEntity.getBody();
        Supplier supplier = supplierResponseEntity.getBody();
        User user = userResponseEntity.getBody();
        try{

            importBillService.save(request,warehouse,user,supplier);
            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_SUCCESS,"Success", null);
        }catch (Exception e){
            responseModel = new ResponseModel(ResponseConstant.RESULT_CODE_ERROR,e.getMessage(), null);
        }
        return ResponseEntity.ok(responseModel);


    }

    @GetMapping(value = "/import-bill-details/{id}")
    public ResponseEntity<?> getImportBillDetailByImportBillId(@PathVariable("id") Long importBillId){

        return ResponseEntity.ok("a");
    }




}
