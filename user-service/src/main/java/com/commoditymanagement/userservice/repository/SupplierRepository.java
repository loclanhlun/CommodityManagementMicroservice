package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Commodity;
import com.commoditymanagement.core.data.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByCode(String code);

    List<Supplier> findSuppliersByName(String name);

    List<Supplier> findSuppliersByStatus(int status);

    @Query(value = "select * from Supplier s where s.name like %:name% and s.status = :status", nativeQuery = true)
    List<Supplier> findSupplierByLikeNameAndStatus(@Param("name") String name, @Param("status") int status);

    @Query(value = "select * from Supplier s where s.name like %:name%", nativeQuery = true)
    List<Supplier> findSupplierByLikeName(@Param("name") String name);
}
