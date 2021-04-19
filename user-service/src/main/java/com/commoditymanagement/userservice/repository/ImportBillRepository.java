package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.ImportBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportBillRepository extends JpaRepository<ImportBill, Long> {

    @Query(value = "select im from ImportBill as im order by im.id desc")
    List<ImportBill> findImportBillOderByIdDesc();
}
