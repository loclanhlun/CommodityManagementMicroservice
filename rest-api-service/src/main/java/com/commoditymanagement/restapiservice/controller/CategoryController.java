package com.commoditymanagement.restapiservice.controller;

import com.commoditymanagement.core.constant.UrlConstants;
import com.commoditymanagement.core.response.ResponseModel;
import com.commoditymanagement.restapiservice.request.SearchByNameAndStatus;
import com.commoditymanagement.restapiservice.request.add.AddCategoryRequest;
import com.commoditymanagement.restapiservice.request.edit.EditCategoryRequest;
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
@RequestMapping(value = "/rest/v1/admin/category")
@CrossOrigin("http://localhost:8080")
public class CategoryController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/list")
    public ResponseEntity<?> getListCategory(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.GET_CATEGORY_LIST_URL, HttpMethod.GET,httpEntity, ResponseModel.class);
        return response;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCategoryById(HttpServletRequest httpServletRequest,
                                             @PathVariable("id") Long categoryId){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", categoryId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.GET_CATEGORY_BY_ID_URL, HttpMethod.GET,httpEntity, ResponseModel.class, params);
        return response;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> searchCategoryByLikeNameAndStatus(HttpServletRequest httpServletRequest,
                                                             @RequestBody SearchByNameAndStatus request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.SEARCH_CATEGORY, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }

    @PostMapping(value = "/add-category")
    public ResponseEntity<?> addCategory(HttpServletRequest httpServletRequest,
                                         @Valid @RequestBody AddCategoryRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.ADD_CATEGORY_URL, HttpMethod.POST,httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/edit-category")
    public ResponseEntity<?> editCategory(HttpServletRequest httpServletRequest,
                                         @Valid @RequestBody EditCategoryRequest request){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        HttpEntity<?> httpEntity  = new HttpEntity<>(request,headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.EDIT_CATEGORY_URL, HttpMethod.PUT, httpEntity, ResponseModel.class);
        return response;
    }

    @PutMapping(value = "/remove-category/{id}")
    public ResponseEntity<?> removeCategory(HttpServletRequest httpServletRequest,
                                            @PathVariable("id") Long categoryId){
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, bearerToken );
        Map<String, Long> params = new HashMap<>();
        params.put("id", categoryId);
        HttpEntity<?> httpEntity  = new HttpEntity<>(headers);
        ResponseEntity<ResponseModel> response = restTemplate.exchange(UrlConstants.REMOVE_CATEGORY_BY_ID_URL, HttpMethod.PUT, httpEntity, ResponseModel.class, params);
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
