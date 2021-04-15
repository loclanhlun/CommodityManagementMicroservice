package com.commoditymanagement.commodityservice.repository;

import com.commoditymanagement.core.data.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findCategoriesByStatus(int status);

	List<Category> findByCode(String code);

	List<Category> findCategoryByName(String name);

}
