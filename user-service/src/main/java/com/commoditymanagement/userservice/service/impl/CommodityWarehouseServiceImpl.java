package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.CommodityWarehouse;
import com.commoditymanagement.userservice.repository.CommodityWarehouseRepository;
import com.commoditymanagement.userservice.response.CommodityWarehouseResponse;
import com.commoditymanagement.userservice.service.CommodityWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityWarehouseServiceImpl implements CommodityWarehouseService {

    @Autowired
    private CommodityWarehouseRepository commodityWarehouseRepository;

    @Override
    public List<CommodityWarehouseResponse> findAllCommodityWarehouse() {
        List<CommodityWarehouseResponse> listResponse = new ArrayList<>();
        List<CommodityWarehouse> listEntity = commodityWarehouseRepository.findAll();
        for(CommodityWarehouse item : listEntity){
            CommodityWarehouseResponse commodityWarehouseResponse = new CommodityWarehouseResponse();
            commodityWarehouseResponse.setId(item.getId());
            commodityWarehouseResponse.setCommodityName(item.getCommodity().getName());
            commodityWarehouseResponse.setWarehouseName(item.getWarehouse().getName());
            commodityWarehouseResponse.setQuantity(item.getQuantity());
            listResponse.add(commodityWarehouseResponse);

        }

        return listResponse;
    }

    @Override
    public List<CommodityWarehouseResponse> findAllCommodityWarehouseByCategoryId(Long categoryId) {
        List<CommodityWarehouseResponse> listResponse = new ArrayList<>();
        List<CommodityWarehouse> listEntity = commodityWarehouseRepository.findCommodityWarehouseByCategoryIdNamedParams(categoryId);
        for(CommodityWarehouse item : listEntity){
            CommodityWarehouseResponse commodityWarehouseResponse = new CommodityWarehouseResponse();
            commodityWarehouseResponse.setId(item.getId());
            commodityWarehouseResponse.setCommodityCode(item.getCommodity().getCode());
            commodityWarehouseResponse.setWarehouseCode(item.getWarehouse().getCode());
            commodityWarehouseResponse.setCommodityName(item.getCommodity().getName());
            commodityWarehouseResponse.setWarehouseName(item.getWarehouse().getName());
            commodityWarehouseResponse.setQuantity(item.getQuantity());
            listResponse.add(commodityWarehouseResponse);

        }

        return listResponse;
    }

    @Override
    public List<CommodityWarehouseResponse> findAllCommodityWarehouseByWarehouseCode(String warehouseCode) {
        List<CommodityWarehouseResponse> listResponse = new ArrayList<>();
        List<CommodityWarehouse> listEntity = commodityWarehouseRepository.findCommodityWarehouseByWarehouseCode(warehouseCode);
        for(CommodityWarehouse item : listEntity){
            CommodityWarehouseResponse commodityWarehouseResponse = new CommodityWarehouseResponse();
            commodityWarehouseResponse.setId(item.getId());
            commodityWarehouseResponse.setCommodityCode(item.getCommodity().getCode());
            commodityWarehouseResponse.setWarehouseCode(item.getWarehouse().getCode());
            commodityWarehouseResponse.setCommodityName(item.getCommodity().getName());
            commodityWarehouseResponse.setWarehouseName(item.getWarehouse().getName());
            commodityWarehouseResponse.setQuantity(item.getQuantity());
            listResponse.add(commodityWarehouseResponse);

        }

        return listResponse;
    }
}
