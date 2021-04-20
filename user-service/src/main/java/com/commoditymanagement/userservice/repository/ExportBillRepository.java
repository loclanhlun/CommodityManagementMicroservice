package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.ExportBill;
import com.commoditymanagement.core.data.ImportBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportBillRepository extends JpaRepository<ExportBill, Long> {
    @Query(value = "select ex from ExportBill as ex order by ex.id desc")
    List<ExportBill> findExportBillOderByIdDesc();
}
