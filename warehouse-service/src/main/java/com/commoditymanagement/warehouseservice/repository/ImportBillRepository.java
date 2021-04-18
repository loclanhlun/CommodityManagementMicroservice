package com.commoditymanagement.warehouseservice.repository;

import com.commoditymanagement.core.data.ImportBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportBillRepository extends JpaRepository<ImportBill, Long> {
}
