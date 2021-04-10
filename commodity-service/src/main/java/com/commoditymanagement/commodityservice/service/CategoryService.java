package com.commoditymanagement.commodityservice.service;

import java.util.List;

import com.commoditymanagement.commodityservice.request.AddCategoryRequest;
import com.commoditymanagement.commodityservice.request.EditCategoryRequest;
import com.commoditymanagement.commodityservice.response.CategoryResponse;
import com.commoditymanagement.core.data.Category;

public interface CategoryService {
	
	List<CategoryResponse> findAllCategory();

	List<CategoryResponse> findById(Long id);
	
	List<CategoryResponse> findAllByCode(String code);
	
	void save(AddCategoryRequest AddCategoryRequest) throws Exception;
	
	void update(EditCategoryRequest categoryRequest, Long id) throws Exception;
	
	void remove(Long id);
}
