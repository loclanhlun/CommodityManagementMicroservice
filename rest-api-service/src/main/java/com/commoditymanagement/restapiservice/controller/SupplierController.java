package com.commoditymanagement.restapiservice.controller;

import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.restapiservice.request.add.AddAgencyRequest;
import com.commoditymanagement.restapiservice.request.add.AddSupplierRequest;
import com.commoditymanagement.restapiservice.request.edit.EditAgencyRequest;
import com.commoditymanagement.restapiservice.request.edit.EditSupplierRequest;
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
@RequestMapping(value = "/rest/v1/supplier")
@CrossOrigin("http://localhost:8080")
public class SupplierController {
    private static final String URL = "http://user-service/rest/v1/admin/supplier";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListSupplier(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = URL + "/list";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSupplierById(HttpServletRequest httpServletRequest,
                                           @PathVariable("id") Long supplierId){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = URL + "/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", supplierId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.GET,httpEntity, ResponseModel.class, params);
        return response;
    }

    @PostMapping(value = "/add-supplier")
    public ResponseEntity<?> addSupplier(HttpServletRequest httpServletRequest,
                                       @Valid @RequestBody AddSupplierRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = URL + "/add-supplier";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/edit-supplier")
    public ResponseEntity<?> editSupplier(HttpServletRequest httpServletRequest,
                                        @Valid @RequestBody EditSupplierRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = URL + "/edit-supplier";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT,httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/remove-supplier/{id}")
    public ResponseEntity<?> removeSupplier(HttpServletRequest httpServletRequest,
                                          @PathVariable("id") Long supplierId){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String url = URL + "/remove-supplier/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", supplierId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(url, HttpMethod.PUT,httpEntity, ResponseModel.class, params);
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
