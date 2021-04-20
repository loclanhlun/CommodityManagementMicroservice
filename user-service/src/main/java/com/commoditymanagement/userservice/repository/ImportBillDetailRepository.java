package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.ImportBillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportBillDetailRepository extends JpaRepository<ImportBillDetail, Long> {

    List<ImportBillDetail> findByImportBillId(Long id);
}
