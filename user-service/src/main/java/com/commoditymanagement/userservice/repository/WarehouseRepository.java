package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findWarehouseByStatus(int status);

    List<Warehouse> findByCode(String code);

    List<Warehouse> findWarehouseByName(String name);
}
