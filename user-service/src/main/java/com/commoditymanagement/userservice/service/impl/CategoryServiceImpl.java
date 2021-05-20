package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.Category;
import com.commoditymanagement.userservice.repository.CategoryRepository;
import com.commoditymanagement.userservice.request.add.AddCategoryRequest;
import com.commoditymanagement.userservice.request.edit.EditCategoryRequest;
import com.commoditymanagement.userservice.request.get.SearchByNameAndStatus;
import com.commoditymanagement.userservice.response.CategoryResponse;
import com.commoditymanagement.userservice.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;


	@Override
	public List<CategoryResponse> findAllCategoryByStatus() {
		List<CategoryResponse> categoriesDTO = new ArrayList<>();
		List<Category> categoriesEntity = categoryRepository.findCategoriesByStatus(0);
		for(Category item : categoriesEntity) {
			CategoryResponse response = new CategoryResponse();
			response.setId(item.getId());
			response.setCode(item.getCode());
			response.setName(item.getName());
			response.setStatus(item.getStatus());
			categoriesDTO.add(response);
		}

		return categoriesDTO;
	}

	@Override
	public CategoryResponse findById(Long id) throws Exception {
		CategoryResponse response = new CategoryResponse();
		Category categoriesEntity = categoryRepository.findById(id).orElse(null);
		if(categoriesEntity == null){
			throw new Exception("Category id does not exist");
		}
		response.setId(categoriesEntity.getId());
		response.setCode(categoriesEntity.getCode());
		response.setName(categoriesEntity.getName());
		response.setStatus(categoriesEntity.getStatus());
		return response;
	}

	@Override
	public List<CategoryResponse> findAllByCode(String code) {
		
		return null;
	}

	@Override
	public List<CategoryResponse> searchAllCategory(SearchByNameAndStatus request) {
		List<CategoryResponse> response = new ArrayList<>();
		List<Category> listEntity = null;
		if(StringUtils.isEmpty(request.getName()) && request.getStatus() != null){
			listEntity = categoryRepository.findCategoriesByStatus(Integer.parseInt(request.getStatus()));

		}else if(request.getStatus() == null && !StringUtils.isEmpty(request.getName())){
			 listEntity = categoryRepository.findCategoryByLikeName(request.getName());
		}else if(StringUtils.isEmpty(request.getName()) && request.getStatus() == null){
			 listEntity = categoryRepository.findCategoriesByStatus(0);
		}else{
			 listEntity = categoryRepository.findCategoryByLikeNameAndStatus(request.getName(), Integer.parseInt(request.getStatus()));
		}
		for(Category item : listEntity){
			CategoryResponse categoryResponse = new CategoryResponse();
			categoryResponse.setId(item.getId());
			categoryResponse.setCode(item.getCode());
			categoryResponse.setName(item.getName());
			categoryResponse.setStatus(item.getStatus());
			response.add(categoryResponse);
		}
		return response;
	}


	@Override
	public void save(AddCategoryRequest AddCategoryRequest) throws Exception {
		checkCategoryCodeAndName(AddCategoryRequest.getCode(), AddCategoryRequest.getName());
		Category category = new Category();
		category.setCode(AddCategoryRequest.getCode());
		category.setName(AddCategoryRequest.getName());
		category.setStatus(0);
		categoryRepository.save(category);
	}

	@Override
	public void update(EditCategoryRequest categoryRequest) throws Exception {
		Category oldCategory = categoryRepository.findById(categoryRequest.getId()).orElse(null);
		if(oldCategory == null) {
			throw new Exception("category id does not exist");
		}
		validateRequest(categoryRequest, oldCategory);
		categoryRepository.save(setCategory(categoryRequest,oldCategory));
	}



	@Override
	public void remove(Long id) throws Exception {
		Category oldCategory = categoryRepository.findById(id).orElse(null);
		if(oldCategory == null){
			throw new Exception("Category id does not exist");
		}
		oldCategory.setStatus(1);
		categoryRepository.save(oldCategory);
	}

	private void validateRequest(EditCategoryRequest categoryRequest, Category oldCategory )throws Exception{
		if(categoryRequest.getCode().equals(oldCategory.getCode())
				&& !categoryRequest.getName().equals(oldCategory.getName())){
			checkCategoryName(categoryRequest.getName());

		}else if(!categoryRequest.getCode().equals(oldCategory.getCode())
				&& categoryRequest.getName().equals(oldCategory.getName())){
			checkCategoryCode(categoryRequest.getCode());

		}else if(!categoryRequest.getCode().equals(oldCategory.getCode())
				&& !categoryRequest.getName().equals(oldCategory.getName())){
			checkCategoryCodeAndName(categoryRequest.getCode(),categoryRequest.getName());
		}
	}

	private Category setCategory(EditCategoryRequest request, Category oldCategory){
		oldCategory.setCode(request.getCode());
		oldCategory.setName(request.getName());
		oldCategory.setStatus(request.getStatus());
		return oldCategory;
	}

	private void checkCategoryCodeAndName(String code, String name)throws Exception{
		if(isExistCode(code) && isExistName(name)){
			throw new Exception("Category code and name is exist");
		}else if(!isExistCode(code) && isExistName(name)){
			throw new Exception("Category name is exist");
		}else if(isExistCode(code) && !isExistName(name)){
			throw new Exception("Category code is exist");
		}
	}

	private void checkCategoryName(String name)throws Exception{
		if(isExistName(name)){
			throw new Exception("Category name is exist");
		}
	}

	private void checkCategoryCode(String code)throws Exception{
		if(isExistCode(code)){
			throw new Exception("Category code is exist");
		}
	}

	private boolean isExistCode(String code){
		List<Category> categories = categoryRepository.findByCode(code);
		if(categories.size() >= 1){
			return true;
		}
		return false;
	}

	private boolean isExistName(String name){
		List<Category> categories = categoryRepository.findCategoryByName(name);
		if(categories.size() >= 1){
			return true;
		}
		return false;
	}



}
