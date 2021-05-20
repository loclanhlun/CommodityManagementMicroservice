package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findCategoriesByStatus(int status);

	List<Category> findByCode(String code);

	List<Category> findCategoryByName(String name);

	@Query(value = "select * from Category c where c.name like %:name% and c.status = :status", nativeQuery = true)
	List<Category> findCategoryByLikeNameAndStatus(@Param("name") String name, @Param("status") int status);

	@Query(value = "select * from Category a where a.name like %:name%", nativeQuery = true)
	List<Category> findCategoryByLikeName(@Param("name") String name);

}
