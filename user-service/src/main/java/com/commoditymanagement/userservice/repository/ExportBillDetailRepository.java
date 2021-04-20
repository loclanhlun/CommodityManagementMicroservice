package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.ExportBillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportBillDetailRepository extends JpaRepository<ExportBillDetail, Long> {

}
