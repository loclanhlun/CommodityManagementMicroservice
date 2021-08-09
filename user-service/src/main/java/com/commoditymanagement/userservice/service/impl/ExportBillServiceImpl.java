package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.*;
import com.commoditymanagement.userservice.repository.*;
import com.commoditymanagement.userservice.request.add.AddExportBillRequest;
import com.commoditymanagement.userservice.request.add.ItemExportDetailRequest;
import com.commoditymanagement.userservice.response.ExportBillResponse;
import com.commoditymanagement.userservice.response.ItemStatisticalImportBill;
import com.commoditymanagement.userservice.response.StatisticalExportBillResponse;
import com.commoditymanagement.userservice.service.ExportBillService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExportBillServiceImpl implements ExportBillService {

    @Autowired
    private ExportBillRepository exportBillRepository;

    @Autowired
    private ExportBillDetailRepository exportBillDetailRepository;

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private CommodityWarehouseRepository commodityWarehouseRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Long countExportBillByMonth() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        long count = exportBillRepository.countExportBillByExportMonth(month);
        return count;
    }

    @Override
    public BigDecimal sumTotalPriceByMonth() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        BigDecimal totalPrice = exportBillRepository.sumTotalPriceByExportMonth(month);
        return totalPrice;
    }

    @Override
    public List<ExportBillResponse> findAllExportBill() {
        List<ExportBillResponse> listResponse = new ArrayList<>();
        List<ExportBill> listExportBillsEntity = exportBillRepository.findAll();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for(ExportBill item : listExportBillsEntity){
            ExportBillResponse dto = new ExportBillResponse();
            dto.setId(item.getId());
            dto.setFullName(item.getUser().getFullName());
            dto.setAgencyName(item.getAgency().getName());
            dto.setWarehouseName(item.getWarehouse().getName());
            dto.setExportDate(formatter.format(item.getExportDate()));
            dto.setTotalPrice(item.getTotalPrice());
            listResponse.add(dto);

        }
        return listResponse;
    }

    @Override
    public void save(AddExportBillRequest request, User user) throws Exception {
        List<ItemExportDetailRequest> listExportBillDetail = request.getData();
        System.out.println(listExportBillDetail.size());
        if(listExportBillDetail.size() == 0){
            throw new Exception("Bạn chưa nhập hàng!");
        }
        validateRequest(request.getWarehouseCode(), request.getAgencyCode());
        List<Agency> agencies = agencyRepository.findByCode(request.getAgencyCode());
        List<Warehouse> warehouses = warehouseRepository.findByCode(request.getWarehouseCode());
        ExportBill exportBill = new ExportBill();
        exportBill.setAgency(agencies.get(0));
        exportBill.setWarehouse(warehouses.get(0));
        exportBill.setUser(user);
        exportBill.setExportDate(new Date());
        exportBillRepository.save(exportBill);

        ExportBill exportBill1 = exportBillRepository.findExportBillOderByIdDesc().get(0);

        Commodity commodity = null;

        Warehouse warehouse = warehouseRepository.findById(exportBill1.getWarehouse().getId()).orElse(null);
        int count = 0;
        int count1 = 0;
        for(ItemExportDetailRequest item : listExportBillDetail){
            if(checkCommodityCode(item.getCommodityCode())){
                throw new Exception("Commodity " + item.getCommodityCode() + "is not found!");
            }
            count ++;
        }

        if(count == listExportBillDetail.size()){
            for(ItemExportDetailRequest item : listExportBillDetail){
                commodity = commodityRepository.findByCode(item.getCommodityCode()).get(0);
                if(checkCommodityWarehouseByCommodityCodeAndWarehouseCode(commodity,warehouse)){
                    throw new Exception("Mã hàng hóa " + item.getCommodityCode() + "Không tồn tại!");
                }
                if(checkQuantity(item.getQuantity(), commodity, warehouse)){
                    throw new Exception("Số lượng của " + commodity.getName() + " trong kho không đủ!");
                }
                if(item.getQuantity() < 0){
                    throw new Exception("Số lượng của " + commodity.getName() + " phải từ 0 trở lên!");
                }
                count1 ++;
            }
        }

        double totalPrice = 0;
        if(count1 == listExportBillDetail.size()){
            for(ItemExportDetailRequest item : listExportBillDetail){
                commodity = commodityRepository.findByCode(item.getCommodityCode()).get(0);
                //add export bill detail
                ExportBillDetail exportBillDetail = new ExportBillDetail();
                exportBillDetail.setExportBill(exportBill);
                exportBillDetail.setCommodity(commodity);
                exportBillDetail.setPrice(item.getPrice());
                exportBillDetail.setQuantity(item.getQuantity());
                totalPrice += item.getQuantity() * item.getPrice().doubleValue();
                exportBillDetailRepository.save(exportBillDetail);
                //update commodity warehouse
                CommodityWarehouse oldCommodityWarehouses = commodityWarehouseRepository.findCommodityWarehouseByCommodityAndWarehouse(commodity, warehouse).get(0);
                oldCommodityWarehouses.setQuantity(oldCommodityWarehouses.getQuantity() - item.getQuantity());
                commodityWarehouseRepository.save(oldCommodityWarehouses);
            }
        }
        exportBill1.setTotalPrice(BigDecimal.valueOf(totalPrice));
        exportBillRepository.save(exportBill1);
    }

    @Override
    public List<ExportBillResponse> searchExportBillByExportDateAndWarehouseCode(String fromDate, String toDate, String warehouseCode) {
        List<ExportBillResponse> exportBillResponses = new ArrayList<>();
        if(StringUtils.isEmpty(warehouseCode)){
            List<ExportBill> exportBills = exportBillRepository.findExportBillByExportDateNamedParams(fromDate,toDate);
            for(ExportBill item : exportBills){
                ExportBillResponse response = new ExportBillResponse();
                response.setId(item.getId());
                response.setFullName(item.getUser().getFullName());
                response.setAgencyName(item.getAgency().getName());
                response.setWarehouseName(item.getWarehouse().getName());
                response.setExportDate(formatter.format(item.getExportDate()));
                response.setTotalPrice(item.getTotalPrice());
                exportBillResponses.add(response);
            }
        }else {
            List<ExportBill> exportBills = exportBillRepository.findExportBillByWarehouseCodeAndExportDate(fromDate,toDate,warehouseCode);
            for(ExportBill item : exportBills){
                ExportBillResponse response = new ExportBillResponse();
                response.setId(item.getId());
                response.setFullName(item.getUser().getFullName());
                response.setAgencyName(item.getAgency().getName());
                response.setWarehouseName(item.getWarehouse().getName());
                response.setExportDate(formatter.format(item.getExportDate()));
                response.setTotalPrice(item.getTotalPrice());
                exportBillResponses.add(response);
            }
        }
        return exportBillResponses;
    }
    @Override
    public List<StatisticalExportBillResponse> statisticalExportBillByYear(String year) {
        List<StatisticalExportBillResponse> listResponse = new ArrayList<>();
        List<ExportBill> listEntity = exportBillRepository.statisticalExportBillByYear(year);

        for(int month = 1; month <= 12 ; month ++){
            StatisticalExportBillResponse response = new StatisticalExportBillResponse();
            ItemStatisticalImportBill itemStatisticalImportBill = new ItemStatisticalImportBill();
            response.setMonth(month);
            itemStatisticalImportBill.setTotalPrice(BigDecimal.valueOf(0));
            for(ExportBill item : listEntity){
                if(item.getExportDate().getMonth()+1 == month){
                    itemStatisticalImportBill.setTotalPrice(item.getTotalPrice());
                }
            }
            response.setData(itemStatisticalImportBill);

            listResponse.add(response);
        }
        return listResponse;
    }

    @Override
    public void delete() {
        ExportBill exportBill = exportBillRepository.findExportBillOderByIdDesc().get(0);
        exportBillRepository.delete(exportBill);
    }

    public void validateRequest(String warehouseCode, String agencyCode)throws Exception{
        if(checkWarehouseCode(warehouseCode) && checkAgencyCode(agencyCode)){
            throw new Exception("Invalid Warehouse and Agency");
        }
        if(checkWarehouseCode(warehouseCode)){
            throw new Exception("Invalid Warehouse");
        }
        if(checkAgencyCode(agencyCode)){
            throw new Exception("Invalid Agency");
        }
    }

    public boolean checkWarehouseCode(String code){
        List<Warehouse> warehouses = warehouseRepository.findByCode(code);
        if(CollectionUtils.isEmpty(warehouses)){
            return true;
        }
        return false;
    }

    public boolean checkAgencyCode(String code){
        List<Agency> agency = agencyRepository.findByCode(code);
        if(CollectionUtils.isEmpty(agency)){
            return true;
        }
        return false;
    }

    public boolean checkCommodityWarehouseByCommodityCodeAndWarehouseCode(Commodity commodity, Warehouse warehouse){
        List<CommodityWarehouse> commodityWarehouses = commodityWarehouseRepository.findCommodityWarehouseByCommodityAndWarehouse(commodity, warehouse);
        if(CollectionUtils.isEmpty(commodityWarehouses)){
            return true;
        }
        return false;
    }

    public boolean checkCommodityCode(String code){
        List<Commodity> commodity = commodityRepository.findByCode(code);
        if(CollectionUtils.isEmpty(commodity)){
            return true;
        }
        return false;
    }

    public boolean checkQuantity(int quantity, Commodity commodity, Warehouse warehouse){
        List<CommodityWarehouse> commodityWarehouses = commodityWarehouseRepository.findCommodityWarehouseByCommodityAndWarehouse(commodity, warehouse);
        if(quantity > commodityWarehouses.get(0).getQuantity()){
            return true;
        }
        return false;
    }
}
