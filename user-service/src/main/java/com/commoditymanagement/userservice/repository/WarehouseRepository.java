package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Supplier;
import com.commoditymanagement.core.data.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findWarehouseByStatus(int status);

    List<Warehouse> findByCode(String code);

    List<Warehouse> findWarehouseByName(String name);

    @Query(value = "select * from Warehouse s where s.name like %:name% and s.status = :status", nativeQuery = true)
    List<Warehouse> findWarehouseByLikeNameAndStatus(@Param("name") String name, @Param("status") int status);

    @Query(value = "select * from Warehouse s where s.name like %:name%", nativeQuery = true)
    List<Warehouse> findWarehouseByLikeName(@Param("name") String name);
}
