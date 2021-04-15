package com.commoditymanagement.commodityservice.controller;


import java.util.List;

import com.commoditymanagement.commodityservice.request.add.AddCategoryRequest;
import com.commoditymanagement.commodityservice.request.edit.EditCategoryRequest;
import com.commoditymanagement.core.constant.ResponseConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.commoditymanagement.commodityservice.response.CategoryResponse;
import com.commoditymanagement.commodityservice.service.CategoryService;
import com.commoditymanagement.core.response.ResponseModel;

@RestController
@RequestMapping(value = "/rest/v1/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = "/list")
	public ResponseEntity<?> getListCategory(){
		List<CategoryResponse> lists = categoryService.findAllCategory();
		ResponseModel response = buildResponse(lists);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") Long categoryId) throws Exception {
		ResponseModel response = new ResponseModel();
		CategoryResponse categoryResponse = new CategoryResponse();
		try {
			categoryResponse = categoryService.findById(categoryId);
			response.setMessage("Success");
			response.setResultCode(ResponseConstant.RESULT_CODE_SUCCESS);
			response.setObject(categoryResponse);
		}catch (Exception e){
			response.setMessage(e.getMessage());
			response.setResultCode(ResponseConstant.RESULT_CODE_ERROR);
			response.setObject(new CategoryResponse());
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/add-category")
	public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequest request){
		ResponseModel response = new ResponseModel();
		try {
			categoryService.save(request);
			response.setMessage("Success");
			response.setResultCode(ResponseConstant.RESULT_CODE_SUCCESS);
		}catch (Exception e){
			response.setMessage(e.getMessage());
			response.setResultCode(ResponseConstant.RESULT_CODE_ERROR);
		}

		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/edit-category/{id}")
	public ResponseEntity<?> editCategory(@PathVariable("id") Long categoryId,@RequestBody EditCategoryRequest request){
		ResponseModel response = new ResponseModel();
		try {
			categoryService.update(request);
			response.setMessage("success");
			response.setResultCode(ResponseConstant.RESULT_CODE_SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setResultCode(ResponseConstant.RESULT_CODE_ERROR);
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/remove-category/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable("id") Long categoryId) throws Exception {
		ResponseModel response = new ResponseModel();
		try {
			categoryService.remove(categoryId);
			response.setMessage("Success");
			response.setResultCode(ResponseConstant.RESULT_CODE_SUCCESS);
		}catch (Exception e){
			response.setMessage(e.getMessage());
			response.setResultCode(ResponseConstant.RESULT_CODE_ERROR);
		}
		return ResponseEntity.ok(response);
	}

	private ResponseModel buildResponse(List<CategoryResponse> lists) {
    	ResponseModel responseModel = new ResponseModel();
    	responseModel.setMessage("Success");
    	responseModel.setResultCode("0");
    	responseModel.setObject(lists);
    	return responseModel;
    }
}
