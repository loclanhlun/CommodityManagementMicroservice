package com.commoditymanagement.commodityservice.service;

import java.util.List;

import com.commoditymanagement.commodityservice.request.add.AddCategoryRequest;
import com.commoditymanagement.commodityservice.request.edit.EditCategoryRequest;
import com.commoditymanagement.commodityservice.response.CategoryResponse;

public interface CategoryService {
	
	List<CategoryResponse> findAllCategory();

	CategoryResponse findById(Long id) throws Exception;
	
	List<CategoryResponse> findAllByCode(String code);
	
	void save(AddCategoryRequest AddCategoryRequest) throws Exception;
	
	void update(EditCategoryRequest categoryRequest, Long id) throws Exception;
	
	void remove(Long id) throws Exception;
}
