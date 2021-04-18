package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.Commodity;
import com.commoditymanagement.core.data.ImportBill;
import com.commoditymanagement.core.data.ImportBillDetail;
import com.commoditymanagement.userservice.repository.CommodityRepository;
import com.commoditymanagement.userservice.repository.ImportBillDetailRepository;
import com.commoditymanagement.userservice.repository.ImportBillRepository;
import com.commoditymanagement.userservice.request.add.AddImportBillDetailRequest;
import com.commoditymanagement.userservice.request.add.AddImportBillRequest;
import com.commoditymanagement.userservice.request.add.ImportBillDetailRequest;
import com.commoditymanagement.userservice.service.ImportBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportBillDetailServiceImpl implements ImportBillDetailService {

    @Autowired
    private ImportBillRepository importBillRepository;

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private ImportBillDetailRepository importBillDetailRepository;
    @Override
    public void save(AddImportBillDetailRequest request) {
        ImportBill importBill = importBillRepository.findById(request.getBillId()).orElse(null);
        List<ImportBillDetailRequest> listImportBillDetail = request.getData();


        for(ImportBillDetailRequest item : listImportBillDetail){
            Commodity commodity = commodityRepository.findByCode(item.getCommodityCode()).get(0);
            System.out.println(commodity.toString());
//            ImportBillDetail importBillDetail = new ImportBillDetail();
//            importBillDetail.setImportBill(importBill);
//            importBillDetail.setCommodities(commodity);
//            importBillDetail.setPrice(item.getPrice());
//            importBillDetail.setQuantity(item.getQuantity());
//            importBillDetailRepository.save(importBillDetail);
        }

    }
}
