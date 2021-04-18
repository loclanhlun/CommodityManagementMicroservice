package com.commoditymanagement.commodityservice.service.impl;

import com.commoditymanagement.commodityservice.repository.SupplierRepository;
import com.commoditymanagement.commodityservice.request.add.AddSupplierRequest;
import com.commoditymanagement.commodityservice.request.edit.EditSupplierRequest;
import com.commoditymanagement.commodityservice.response.SupplierResponse;
import com.commoditymanagement.commodityservice.service.SupplierService;
import com.commoditymanagement.core.data.Supplier;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<SupplierResponse> findAllSupplier() throws Exception {
        List<SupplierResponse> listResponse = new ArrayList<>();
        List<Supplier> listEntity = supplierRepository.findSuppliersByStatus(0);
        for(Supplier item : listEntity){
            SupplierResponse response = getSupplierResponse(item);
            listResponse.add(response);
        }
        return listResponse;
    }

    @Override
    public Supplier findByCodeFromImportBill(String supplierCode) throws Exception {
        if(supplierCode == null){
            throw new Exception("Supplier code is null");
        }
        Supplier supplierEntity = supplierRepository.findByCode(supplierCode).get(0);
        return supplierEntity;
    }

    @Override
    public SupplierResponse findById(Long supplierId) throws Exception {
        Supplier supplierEntity = supplierRepository.findById(supplierId).orElse(null);
        if(supplierEntity == null){
            throw new Exception("Supplier id does not exist!");
        }
        SupplierResponse response = getSupplierResponse(supplierEntity);
        return response;
    }

    @Override
    public void save(AddSupplierRequest request) throws Exception {
        checkSupplierCodeAndName(request.getCode(),request.getName());
        validateRequest(request);
        Supplier supplier = setSupplier(request);
        supplierRepository.save(supplier);
    }

    @Override
    public void update(EditSupplierRequest request) throws Exception {
        Supplier oldSupplier = supplierRepository.findById(request.getId()).orElse(null);
        if(oldSupplier == null){
            throw new Exception("Agency id does not exist!");
        }
        validateRequest(request, oldSupplier);
        supplierRepository.save(setSupplier(request,oldSupplier));
    }

    @Override
    public void remove(Long id) throws Exception {
        Supplier oldSupplier = supplierRepository.findById(id).orElse(null);
        if(oldSupplier == null){
            throw new Exception("Commodity id does not exist");
        }
        oldSupplier.setStatus(1);
        supplierRepository.save(oldSupplier);
    }

    private SupplierResponse getSupplierResponse(Supplier supplier)throws Exception{
        SupplierResponse supplierResponse = new SupplierResponse();
        supplierResponse.setId(supplier.getId());
        supplierResponse.setCode(supplier.getCode());
        supplierResponse.setName(supplier.getName());
        supplierResponse.setPhoneNumber(supplier.getPhoneNumber());
        supplierResponse.setAddress(supplier.getAddress());
        supplierResponse.setStatus(supplier.getStatus());
        return supplierResponse;
    }

    private Supplier setSupplier(AddSupplierRequest request)throws Exception{
        Supplier supplier = new Supplier();
        supplier.setCode(request.getCode());
        supplier.setName(request.getName());
        supplier.setPhoneNumber(request.getPhoneNumber());
        supplier.setAddress(request.getAddress());
        return supplier;
    }

    private Supplier setSupplier(EditSupplierRequest request, Supplier oldSupplier){
        oldSupplier.setCode(request.getCode());
        oldSupplier.setName(request.getName());
        oldSupplier.setAddress(request.getAddress());
        oldSupplier.setPhoneNumber(request.getPhoneNumber());
        oldSupplier.setStatus(request.getStatus());
        return oldSupplier;
    }

    private void validateRequest(AddSupplierRequest request)throws Exception{
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

    private void validateRequest(EditSupplierRequest supplierRequest, Supplier oldSupplier )throws Exception{
        if(supplierRequest.getCode().equals(oldSupplier.getCode())
                && !supplierRequest.getName().equals(oldSupplier.getName())){
            checkSupplierName(supplierRequest.getName());

        }else if(!supplierRequest.getCode().equals(oldSupplier.getCode())
                && supplierRequest.getName().equals(oldSupplier.getName())){
            checkSupplierCode(supplierRequest.getCode());

        }else if(!supplierRequest.getCode().equals(oldSupplier.getCode())
                && !supplierRequest.getName().equals(oldSupplier.getName())){
            checkSupplierCodeAndName(supplierRequest.getCode(),supplierRequest.getName());
        }
    }
    private void checkSupplierName(String name)throws Exception{
        if(isExistName(name)){
            throw new Exception("Supplier name is exist");
        }
    }

    private void checkSupplierCode(String code)throws Exception{
        if(isExistCode(code)){
            throw new Exception("Supplier code is exist");
        }
    }

    private void checkSupplierCodeAndName(String code, String name)throws Exception{
        if(isExistCode(code) && isExistName(name)){
            throw new Exception("Supplier code and name is exist");
        }else if(!isExistCode(code) && isExistName(name)){
            throw new Exception("Supplier name is exist");
        }else if(isExistCode(code) && !isExistName(name)){
            throw new Exception("Supplier code is exist");
        }
    }

    private boolean isExistCode(String code){
        List<Supplier> supplierList = supplierRepository.findByCode(code);
        if(supplierList.size() >= 1){
            return true;
        }
        return false;
    }

    private boolean isExistName(String name){
        List<Supplier> supplierList = supplierRepository.findSuppliersByName(name);
        if(supplierList.size() >= 1){
            return true;
        }
        return false;
    }


}
