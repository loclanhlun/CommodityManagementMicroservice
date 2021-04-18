package com.commoditymanagement.userservice.service.impl;


import com.commoditymanagement.core.data.Warehouse;
import com.commoditymanagement.userservice.repository.WarehouseRepository;
import com.commoditymanagement.userservice.request.add.AddWarehouseRequest;
import com.commoditymanagement.userservice.request.edit.EditWarehouseRequest;
import com.commoditymanagement.userservice.response.WarehouseResponse;
import com.commoditymanagement.userservice.service.WarehouseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<WarehouseResponse> findAllWarehouse() throws Exception {
        List<WarehouseResponse> listResponse = new ArrayList<>();
        List<Warehouse> listEntity = warehouseRepository.findWarehouseByStatus(0);
        for(Warehouse item : listEntity){
            WarehouseResponse response = getWarehouseResponse(item);
            listResponse.add(response);
        }
        return listResponse;
    }

    @Override
    public WarehouseResponse findById(Long warehouseId) throws Exception {
        Warehouse warehouseEntity = warehouseRepository.findById(warehouseId).orElse(null);
        if(warehouseEntity == null){
            throw new Exception("Warehouse id does not exist!");
        }
        WarehouseResponse response = getWarehouseResponse(warehouseEntity);
        return response;
    }

    @Override
    public void save(AddWarehouseRequest request) throws Exception {
        checkWarehouseCodeAndName(request.getCode(),request.getName());
        validateRequest(request);
        Warehouse warehouse = setWarehouse(request);
        warehouseRepository.save(warehouse);
    }

    @Override
    public void update(EditWarehouseRequest request) throws Exception {
        Warehouse oldWarehouse = warehouseRepository.findById(request.getId()).orElse(null);
        if(oldWarehouse == null){
            throw new Exception("Warehouse id does not exist!");
        }
        validateRequest(request, oldWarehouse);
        warehouseRepository.save(setWarehouse(request,oldWarehouse));
    }

    @Override
    public void remove(Long id) throws Exception {
        Warehouse oldWarehouse = warehouseRepository.findById(id).orElse(null);
        if(oldWarehouse == null){
            throw new Exception("Commodity id does not exist");
        }
        oldWarehouse.setStatus(1);
        warehouseRepository.save(oldWarehouse);
    }

    private WarehouseResponse getWarehouseResponse(Warehouse warehouse)throws Exception{
        WarehouseResponse warehouseResponse = new WarehouseResponse();
        warehouseResponse.setId(warehouse.getId());
        warehouseResponse.setCode(warehouse.getCode());
        warehouseResponse.setName(warehouse.getName());
        warehouseResponse.setPhoneNumber(warehouse.getPhoneNumber());
        warehouseResponse.setAddress(warehouse.getAddress());
        warehouseResponse.setStatus(warehouse.getStatus());
        return warehouseResponse;
    }

    private Warehouse setWarehouse(AddWarehouseRequest request)throws Exception{
        Warehouse warehouse = new Warehouse();
        warehouse.setCode(request.getCode());
        warehouse.setName(request.getName());
        warehouse.setPhoneNumber(request.getPhoneNumber());
        warehouse.setAddress(request.getAddress());
        return warehouse;
    }

    private Warehouse setWarehouse(EditWarehouseRequest request, Warehouse oldWarehouse){
        oldWarehouse.setCode(request.getCode());
        oldWarehouse.setName(request.getName());
        oldWarehouse.setAddress(request.getAddress());
        oldWarehouse.setPhoneNumber(request.getPhoneNumber());
        oldWarehouse.setStatus(request.getStatus());
        return oldWarehouse;
    }

    private void validateRequest(AddWarehouseRequest request)throws Exception{
        if(StringUtils.isEmpty(request.getCode())){
            throw new Exception("Supplier code cannot null");
        }
        if(StringUtils.isEmpty(request.getName())){
            throw new Exception("Supplier name cannot null");
        }
        if(StringUtils.isEmpty(request.getAddress())){
            throw new Exception("Supplier address cannot null");
        }
        if(StringUtils.isEmpty(request.getPhoneNumber())){
            throw new Exception("Supplier phone number cannot null");
        }
    }

    private void validateRequest(EditWarehouseRequest warehouseRequest, Warehouse oldWarehouse )throws Exception{
        if(warehouseRequest.getCode().equals(oldWarehouse.getCode())
                && !warehouseRequest.getName().equals(oldWarehouse.getName())){
            checkWarehouseName(warehouseRequest.getName());

        }else if(!warehouseRequest.getCode().equals(oldWarehouse.getCode())
                && warehouseRequest.getName().equals(oldWarehouse.getName())){
            checkWarehouseCode(warehouseRequest.getCode());

        }else if(!warehouseRequest.getCode().equals(oldWarehouse.getCode())
                && !warehouseRequest.getName().equals(oldWarehouse.getName())){
            checkWarehouseCodeAndName(warehouseRequest.getCode(),warehouseRequest.getName());
        }
    }
    private void checkWarehouseName(String name)throws Exception{
        if(isExistName(name)){
            throw new Exception("Supplier name is exist");
        }
    }

    private void checkWarehouseCode(String code)throws Exception{
        if(isExistCode(code)){
            throw new Exception("Supplier code is exist");
        }
    }

    private void checkWarehouseCodeAndName(String code, String name)throws Exception{
        if(isExistCode(code) && isExistName(name)){
            throw new Exception("Warehouse code and name is exist");
        }else if(!isExistCode(code) && isExistName(name)){
            throw new Exception("Warehouse name is exist");
        }else if(isExistCode(code) && !isExistName(name)){
            throw new Exception("Warehouse code is exist");
        }
    }

    private boolean isExistCode(String code){
        List<Warehouse> supplierList = warehouseRepository.findByCode(code);
        if(supplierList.size() >= 1){
            return true;
        }
        return false;
    }

    private boolean isExistName(String name){
        List<Warehouse> supplierList = warehouseRepository.findWarehouseByName(name);
        if(supplierList.size() >= 1){
            return true;
        }
        return false;
    }
}
