package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.CommodityWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityWarehouseRepository extends JpaRepository<CommodityWarehouse, Long> {
}
