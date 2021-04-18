package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {
    List<Commodity> findCommoditiesByStatus(int status);

    List<Commodity> findByCode(String code);

    List<Commodity> findCommoditiesByName(String name);
}
