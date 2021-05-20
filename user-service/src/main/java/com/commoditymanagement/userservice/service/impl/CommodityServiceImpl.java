package com.commoditymanagement.userservice.service.impl;


import com.commoditymanagement.core.data.Category;
import com.commoditymanagement.core.data.Commodity;
import com.commoditymanagement.userservice.repository.CategoryRepository;
import com.commoditymanagement.userservice.repository.CommodityRepository;
import com.commoditymanagement.userservice.request.add.AddCommodityRequest;
import com.commoditymanagement.userservice.request.get.GetResultById;
import com.commoditymanagement.userservice.request.edit.EditCommodityRequest;
import com.commoditymanagement.userservice.request.get.SearchByNameAndStatus;
import com.commoditymanagement.userservice.response.CommodityResponse;
import com.commoditymanagement.userservice.service.CommodityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CommodityResponse> findAllCommodity() {
        List<CommodityResponse> commodityDTO = new ArrayList<>();
        List<Commodity> commodityEntity = commodityRepository.findCommoditiesByStatus(0);
        for(Commodity item : commodityEntity){
            CommodityResponse commodityResponse = new CommodityResponse();
            commodityResponse.setId(item.getId());
            commodityResponse.setCode(item.getCode());
            commodityResponse.setName(item.getName());
            commodityResponse.setCategoryName(item.getCategory().getName());
            commodityResponse.setStatus(item.getStatus());
            commodityDTO.add(commodityResponse);
        }
        return commodityDTO;
    }

    @Override
    public CommodityResponse findById(Long id) throws Exception{
        if(id == null){
           throw new Exception("Commodity Id cannot null!");
        }
        Commodity commodity = commodityRepository.findById(id).orElse(null);
        if(commodity == null){
            throw new Exception("Commodity id does not exist!");
        }
        CommodityResponse response = new CommodityResponse();
        response.setId(commodity.getId());
        response.setCode(commodity.getCode());
        response.setName(commodity.getName());
        response.setCategoryName(commodity.getCategory().getName());
        response.setCategoryId(commodity.getCategory().getId());
        response.setStatus(commodity.getStatus());
        return response;
    }

    @Override
    public List<CommodityResponse> findAllByCode(String code) {
        return null;
    }

    @Override
    public List<CommodityResponse> searchAllCommodity(SearchByNameAndStatus request) {
        List<CommodityResponse> listResponse =  new ArrayList<>();
        List<Commodity> listEntity = null;
        if(StringUtils.isEmpty(request.getName()) && request.getStatus() != null){
            listEntity = commodityRepository.findCommoditiesByStatus(Integer.parseInt(request.getStatus()));
        }else if(StringUtils.isEmpty(request.getName()) && request.getStatus() == null){
            listEntity = commodityRepository.findCommoditiesByStatus(0);
        }else if(request.getStatus() == null){
            listEntity = commodityRepository.findCategoryByLikeName(request.getName());
        }else{
            listEntity = commodityRepository.findCategoryByLikeNameAndStatus(request.getName(), Integer.parseInt(request.getStatus()));
        }
        for(Commodity item : listEntity){
            CommodityResponse commodityResponse = new CommodityResponse();
            commodityResponse.setId(item.getId());
            commodityResponse.setCode(item.getCode());
            commodityResponse.setName(item.getName());
            commodityResponse.setCategoryName(item.getCategory().getName());
            commodityResponse.setStatus(item.getStatus());
            listResponse.add(commodityResponse);
        }
        return listResponse;
    }

    @Override
    public List<CommodityResponse> findAllByCategoryId(GetResultById request) {
        List<CommodityResponse> commodityResponses = new ArrayList<>();
        Category category = categoryRepository.findById(request.getId()).orElse(null);
        List<Commodity> listEntity = commodityRepository.findCommoditiesByCategory(category);
        for(Commodity item : listEntity){
            CommodityResponse commodityResponse = new CommodityResponse();
            commodityResponse.setId(item.getId());
            commodityResponse.setCode(item.getCode());
            commodityResponse.setName(item.getName());
            commodityResponse.setCategoryName(item.getCategory().getName());
            commodityResponse.setStatus(item.getStatus());
            commodityResponses.add(commodityResponse);
        }
        return commodityResponses;
    }

    @Override
    public void save(AddCommodityRequest request) throws Exception {
        checkCommodityCodeAndName(request.getCode(), request.getName());
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null){
            throw new Exception("Category does not exist!");
        }
        Commodity commodity = new Commodity();
        commodity.setCode(request.getCode());
        commodity.setName(request.getName());
        commodity.setCategory(category);
        commodityRepository.save(commodity);
    }

    @Override
    public void update(EditCommodityRequest request) throws Exception {
        Commodity oldCommodity = commodityRepository.findById(request.getId()).orElse(null);
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null){
            throw new Exception("Category does not exist!");
        }
        if(oldCommodity == null) {
            throw new Exception("Commodity id does not exist");
        }
        validateRequest(request, oldCommodity);
        commodityRepository.save(setCommodity(request,oldCommodity,category));
    }

    @Override
    public void remove(Long id) throws Exception {
        Commodity oldCommodity = commodityRepository.findById(id).orElse(null);
        if(oldCommodity == null){
            throw new Exception("Commodity id does not exist");
        }
        oldCommodity.setStatus(1);
        commodityRepository.save(oldCommodity);
    }

    private Commodity setCommodity(EditCommodityRequest request, Commodity oldCommodity, Category category){
        oldCommodity.setCode(request.getCode());
        oldCommodity.setName(request.getName());
        oldCommodity.setCategory(category);
        oldCommodity.setStatus(request.getStatus());
        return oldCommodity;
    }

    private void validateRequest(EditCommodityRequest commodityRequest, Commodity oldCommodity )throws Exception{
        if(commodityRequest.getCode().equals(oldCommodity.getCode())
                && !commodityRequest.getName().equals(oldCommodity.getName())){
            checkCommodityName(commodityRequest.getName());

        }else if(!commodityRequest.getCode().equals(oldCommodity.getCode())
                && commodityRequest.getName().equals(oldCommodity.getName())){
            checkCommodityCode(commodityRequest.getCode());

        }else if(!commodityRequest.getCode().equals(oldCommodity.getCode())
                && !commodityRequest.getName().equals(oldCommodity.getName())){
            checkCommodityCodeAndName(commodityRequest.getCode(),commodityRequest.getName());
        }
    }

    private void checkCommodityCodeAndName(String code, String name)throws Exception{
        if(isExistCode(code) && isExistName(name)){
            throw new Exception("Commodity code and name is exist");
        }else if(!isExistCode(code) && isExistName(name)){
            throw new Exception("Commodity name is exist");
        }else if(isExistCode(code) && !isExistName(name)){
            throw new Exception("Commodity code is exist");
        }
    }

    private void checkCommodityName(String name)throws Exception{
        if(isExistName(name)){
            throw new Exception("Commodity name is exist");
        }
    }

    private void checkCommodityCode(String code)throws Exception{
        if(isExistCode(code)){
            throw new Exception("Commodity code is exist");
        }
    }

    private boolean isExistCode(String code){
        List<Commodity> commodities = commodityRepository.findByCode(code);
        if(commodities.size() >= 1){
            return true;
        }
        return false;
    }

    private boolean isExistName(String name){
        List<Commodity> commodities = commodityRepository.findCommoditiesByName(name);
        if(commodities.size() >= 1){
            return true;
        }
        return false;
    }
}
