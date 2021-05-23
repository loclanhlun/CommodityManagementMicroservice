package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.*;
import com.commoditymanagement.userservice.repository.AgencyRepository;
import com.commoditymanagement.userservice.repository.ExportBillRepository;
import com.commoditymanagement.userservice.repository.WarehouseRepository;
import com.commoditymanagement.userservice.request.add.AddExportBillRequest;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExportBillServiceImpl implements ExportBillService {

    @Autowired
    private ExportBillRepository exportBillRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

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
        validateRequest(request.getWarehouseCode(), request.getAgencyCode());
        List<Agency> agencies = agencyRepository.findByCode(request.getAgencyCode());
        List<Warehouse> warehouses = warehouseRepository.findByCode(request.getWarehouseCode());
        ExportBill exportBill = new ExportBill();
        exportBill.setAgency(agencies.get(0));
        exportBill.setWarehouse(warehouses.get(0));
        exportBill.setUser(user);
        exportBill.setExportDate(new Date());
        exportBillRepository.save(exportBill);
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
}
