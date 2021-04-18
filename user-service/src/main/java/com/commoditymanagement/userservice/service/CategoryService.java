package com.commoditymanagement.userservice.service;

import com.commoditymanagement.userservice.request.add.AddCategoryRequest;
import com.commoditymanagement.userservice.request.edit.EditCategoryRequest;
import com.commoditymanagement.userservice.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
	
	List<CategoryResponse> findAllCategory();

	CategoryResponse findById(Long id) throws Exception;
	
	List<CategoryResponse> findAllByCode(String code);
	
	void save(AddCategoryRequest AddCategoryRequest) throws Exception;
	
	void update(EditCategoryRequest categoryRequest) throws Exception;
	
	void remove(Long id) throws Exception;
}
