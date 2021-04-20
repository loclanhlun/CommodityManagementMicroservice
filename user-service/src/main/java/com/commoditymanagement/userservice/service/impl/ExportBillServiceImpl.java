package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.*;
import com.commoditymanagement.userservice.repository.AgencyRepository;
import com.commoditymanagement.userservice.repository.ExportBillRepository;
import com.commoditymanagement.userservice.repository.WarehouseRepository;
import com.commoditymanagement.userservice.request.add.AddExportBillRequest;
import com.commoditymanagement.userservice.response.ExportBillResponse;
import com.commoditymanagement.userservice.service.ExportBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    @Override
    public List<ExportBillResponse> findAllExportBill() {
        return null;
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
        exportBill.setExportDate(new Date(0));
        exportBill.setStatus(0);
        exportBillRepository.save(exportBill);
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
