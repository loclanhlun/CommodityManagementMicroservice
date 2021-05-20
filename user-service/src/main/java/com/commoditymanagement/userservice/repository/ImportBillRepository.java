package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.ImportBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportBillRepository extends JpaRepository<ImportBill, Long> {

    @Query(value = "select * from ImportBill as im where date_format(im.importdate,'%d/%m/%Y') >= :fromDate and  date_format(im.importdate,'%d/%m/%Y') <= :toDate",
            nativeQuery = true)
    List<ImportBill> findImportBillByImportDateNamedParams(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value = "select im.id, im.importdate, sum(im.totalprice) as 'totalprice', im.supplierid, im.warehouseid, im.userid from ImportBill im where year(im.importdate) = ?1 group by month(im.importdate)", nativeQuery = true)
    List<ImportBill> statisticalImportBillByYear(String year);

    @Query(value = "select * from ImportBill as im " +
            "inner join warehouse as w " +
            "where im.warehouseid = w.id " +
            "and date_format(im.importdate,'%d/%m/%Y') >= :fromDate " +
            "and  date_format(im.importdate,'%d/%m/%Y') <= :toDate " +
            "and w.code = :warehouseCode",
            nativeQuery = true)
    List<ImportBill> findImportBillByWarehouseCodeAndImportDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("warehouseCode") String warehouseCode);



    @Query(value = "select im from ImportBill as im order by im.id desc")
    List<ImportBill> findImportBillOderByIdDesc();
}
