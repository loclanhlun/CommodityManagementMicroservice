package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Commodity;
import com.commoditymanagement.core.data.CommodityWarehouse;
import com.commoditymanagement.core.data.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommodityWarehouseRepository extends JpaRepository<CommodityWarehouse, Long> {
    List<CommodityWarehouse> findCommodityWarehouseByCommodityAndWarehouse(String commodityCode, Warehouse warehouse);
    List<CommodityWarehouse> findCommodityWarehouseByCommodityAndWarehouse(Commodity commodity, Warehouse warehouse);
    @Query(value = "Select * from commoditywarehouse cw " +
            "inner join commodity  c " +
            "inner join warehouse  w " +
            "where cw.commodity_id = c.id " +
            "and cw.warehouse_id = w.id " +
            "and c.categoryId = :categoryId ", nativeQuery = true)
    List<CommodityWarehouse> findCommodityWarehouseByCategoryIdNamedParams(@Param("categoryId") Long categoryId);

    List<CommodityWarehouse> findCommodityWarehouseByWarehouseCode(String warehouseCode);
}
