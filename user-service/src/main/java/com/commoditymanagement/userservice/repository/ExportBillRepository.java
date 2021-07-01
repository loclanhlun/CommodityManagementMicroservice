package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.ExportBill;
import com.commoditymanagement.core.data.ImportBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExportBillRepository extends JpaRepository<ExportBill, Long> {

    @Query(value = "select count(*) from ExportBill as ex where month(ex.exportdate) = :month", nativeQuery = true)
    Long countExportBillByExportMonth(@Param("month") int month);

    @Query(value = "select sum(ex.totalprice) from ExportBill ex where month(ex.exportdate) = :month", nativeQuery = true)
    BigDecimal sumTotalPriceByExportMonth(@Param("month") int month);

    @Query(value = "select * from ExportBill as ex where date_format(ex.exportdate,'%d/%m/%Y') >= :fromDate and  date_format(ex.exportdate,'%d/%m/%Y') <= :toDate",
            nativeQuery = true)
    List<ExportBill> findExportBillByExportDateNamedParams(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value = "select im.id, im.exportdate, sum(im.totalprice) as 'totalprice', im.agency_id, im.warehouse_id, im.user_id from ExportBill im where year(im.exportdate) = ?1 group by month(im.exportdate)", nativeQuery = true)
    List<ExportBill> statisticalExportBillByYear(String year);

    @Query(value = "select * from ExportBill as ex " +
            "inner join warehouse as w " +
            "where ex.warehouse_id = w.id " +
            "and date_format(ex.exportdate,'%d/%m/%Y') >= :fromDate " +
            "and  date_format(ex.exportdate,'%d/%m/%Y') <= :toDate " +
            "and w.code = :warehouseCode",
            nativeQuery = true)
    List<ExportBill> findExportBillByWarehouseCodeAndExportDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("warehouseCode") String warehouseCode);

    @Query(value = "select ex from ExportBill as ex order by ex.id desc")
    List<ExportBill> findExportBillOderByIdDesc();
}
