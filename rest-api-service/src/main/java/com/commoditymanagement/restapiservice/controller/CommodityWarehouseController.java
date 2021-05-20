package com.commoditymanagement.restapiservice.controller;

import com.commoditymanagement.core.constant.UrlConstants;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.restapiservice.request.add.GetResultByCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest/v1/commodity-warehouse")
@CrossOrigin("http://localhost:8080")
public class CommodityWarehouseController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListCommodityWarehouse(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.GET_COMMODITY_WAREHOUSE_LIST_URL, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }

    @PostMapping(value = "/search-commodity-warehouse")
    public ResponseEntity<?> searchCommodityWarehouseByWarehouseId(HttpServletRequest httpServletRequest,
                                                                   @RequestBody GetResultByCode request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.SEARCH_COMMODITY_WAREHOUSE_URL, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }
}
