package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Category;
import com.commoditymanagement.core.data.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {
    List<Commodity> findCommoditiesByStatus(int status);

    List<Commodity> findByCode(String code);

    List<Commodity> findCommoditiesByName(String name);

    List<Commodity> findCommoditiesByCategory(Category category);

    @Query(value = "select * from Commodity c where c.name like %:name% and c.status = :status", nativeQuery = true)
    List<Commodity> findCategoryByLikeNameAndStatus(@Param("name") String name, @Param("status") int status);

    @Query(value = "select * from Commodity a where a.name like %:name%", nativeQuery = true)
    List<Commodity> findCategoryByLikeName(@Param("name") String name);
}
