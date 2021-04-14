package com.commoditymanagement.commodityservice.repository;

import com.commoditymanagement.core.data.Agency;
import com.commoditymanagement.core.data.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByCode(String code);

    List<Supplier> findSuppliersByName(String name);

    List<Supplier> findSuppliersByStatus(int status);
}
