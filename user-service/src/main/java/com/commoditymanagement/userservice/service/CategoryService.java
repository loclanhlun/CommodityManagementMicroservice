package com.commoditymanagement.userservice.service;

import com.commoditymanagement.userservice.request.add.AddCategoryRequest;
import com.commoditymanagement.userservice.request.edit.EditCategoryRequest;
import com.commoditymanagement.userservice.request.get.SearchByNameAndStatus;
import com.commoditymanagement.userservice.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
	
	List<CategoryResponse> findAllCategoryByStatus();

	CategoryResponse findById(Long id) throws Exception;
	
	List<CategoryResponse> findAllByCode(String code);

	List<CategoryResponse> searchAllCategory(SearchByNameAndStatus request);
	
	void save(AddCategoryRequest AddCategoryRequest) throws Exception;
	
	void update(EditCategoryRequest categoryRequest) throws Exception;
	
	void remove(Long id) throws Exception;
}
