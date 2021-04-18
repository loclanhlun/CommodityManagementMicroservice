package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findCategoriesByStatus(int status);

	List<Category> findByCode(String code);

	List<Category> findCategoryByName(String name);

}
