package com.commoditymanagement.restapiservice.controller;

import com.commoditymanagement.core.constant.UrlConstants;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.restapiservice.request.add.AddExportBillRequest;
import com.commoditymanagement.restapiservice.request.add.AddExportDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/v1/export-bill-detail")
@CrossOrigin("http://localhost:8080")
public class ExportBillDetailController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/add-export-bill-detail")
    public ResponseEntity<?> addImportBill(HttpServletRequest httpServletRequest,
                                           @Valid @RequestBody AddExportDetailRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.ADD_EXPORT_DETAIL_URL, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<?> getExportBillDetailByExportBillId(HttpServletRequest httpServletRequest,
                                              @PathVariable("id") Long exportBillId){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", exportBillId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.GET_EXPORT_DETAIL_BY_IMPORT_BILL_ID_URL, HttpMethod.GET,httpEntity, ResponseModel.class, params);
        return response;
    }
}
