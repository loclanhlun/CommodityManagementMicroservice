package com.commoditymanagement.commodityservice.repository;

import com.commoditymanagement.core.data.Commodity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {
    List<Commodity> findCommoditiesByStatus(int status);

    List<Commodity> findByCode(String code);

    List<Commodity> findCommoditiesByName(String name);
}
