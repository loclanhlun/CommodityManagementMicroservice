package com.commoditymanagement.restapiservice.controller;

import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.restapiservice.request.add.AddExportBillRequest;
import com.commoditymanagement.restapiservice.request.add.AddImportBillRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/rest/v1/export-bill")
public class ExportBillController {

    private static final String URL = "http://user-service/rest/v1/authenticate/export-bill";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/add-export-bill")
    public ResponseEntity<?> addImportBill(HttpServletRequest httpServletRequest,
                                           @Valid @RequestBody AddExportBillRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = URL + "/add-export-bill";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken);
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }
}
