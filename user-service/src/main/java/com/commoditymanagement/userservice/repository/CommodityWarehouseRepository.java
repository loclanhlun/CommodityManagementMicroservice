package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Commodity;
import com.commoditymanagement.core.data.CommodityWarehouse;
import com.commoditymanagement.core.data.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityWarehouseRepository extends JpaRepository<CommodityWarehouse, Long> {
    List<CommodityWarehouse> findCommodityWarehouseByCommodityAndWarehouse(String commodityCode, Warehouse warehouse);
    List<CommodityWarehouse> findCommodityWarehouseByCommodityAndWarehouse(Commodity commodity, Warehouse warehouse);
}
