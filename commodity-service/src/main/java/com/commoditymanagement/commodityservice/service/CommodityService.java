package com.commoditymanagement.commodityservice.service;

import com.commoditymanagement.commodityservice.request.add.AddCommodityRequest;
import com.commoditymanagement.commodityservice.request.edit.EditCommodityRequest;
import com.commoditymanagement.commodityservice.response.CommodityResponse;

import java.util.List;

public interface CommodityService {

    List<CommodityResponse> findAllCommodity();

    CommodityResponse findById(Long id) throws Exception;

    List<CommodityResponse> findAllByCode(String code);

    List<CommodityResponse> findAllByCategoryId(Long categoryId);

    void save(AddCommodityRequest request) throws Exception;

    void update(EditCommodityRequest request) throws Exception;

    void remove(Long id) throws Exception;

}
