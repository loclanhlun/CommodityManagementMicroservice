package com.commoditymanagement.restapiservice.controller;

import com.commoditymanagement.core.constant.UrlConstants;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.restapiservice.request.SearchByNameAndStatus;
import com.commoditymanagement.restapiservice.request.add.AddCommodityRequest;
import com.commoditymanagement.restapiservice.request.add.GetResultById;
import com.commoditymanagement.restapiservice.request.edit.EditCommodityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/v1/admin/commodity")
@CrossOrigin("http://localhost:8080")
public class CommodityController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListCommodity(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.GET_COMMODITY_LIST_URL, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }

    @PostMapping(value = "/list-commodity")
    public ResponseEntity<?> getCommodityByCategoryId(HttpServletRequest httpServletRequest,
                                                      @RequestBody GetResultById request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.GET_COMMODITY_LIST_BY_CATEGORY_ID_URL, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCommodityById(HttpServletRequest httpServletRequest,
                                             @PathVariable("id") Long commodityId){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", commodityId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.GET_COMMODITY_BY_ID_URL, HttpMethod.GET,httpEntity, ResponseModel.class, params);
        return response;
    }



    @PostMapping(value = "/add-commodity")
    public ResponseEntity<?> addCategory(HttpServletRequest httpServletRequest,
                                         @Valid @RequestBody AddCommodityRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.ADD_COMMODITY_URL, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }
    @PostMapping(value = "/search")
    public ResponseEntity<?> searchCommodityByLikeNameAndStatus(HttpServletRequest httpServletRequest,
                                                               @RequestBody SearchByNameAndStatus request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.SEARCH_COMMODITY, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/edit-commodity")
    public ResponseEntity<?> editCategory(HttpServletRequest httpServletRequest,
                                          @Valid @RequestBody EditCommodityRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.EDIT_COMMODITY_URL, HttpMethod.PUT, httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/remove-commodity/{id}")
    public ResponseEntity<?> removeCategory(HttpServletRequest httpServletRequest,
                                            @PathVariable("id") Long commodityId){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", commodityId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.REMOVE_COMMODITY_BY_ID_URL, HttpMethod.PUT, httpEntity, ResponseModel.class, params);
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }
}
