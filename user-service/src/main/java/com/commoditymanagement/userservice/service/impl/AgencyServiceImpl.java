package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.Agency;
import com.commoditymanagement.userservice.repository.AgencyRepository;
import com.commoditymanagement.userservice.request.add.AddAgencyRequest;
import com.commoditymanagement.userservice.request.edit.EditAgencyRequest;
import com.commoditymanagement.userservice.response.AgencyResponse;
import com.commoditymanagement.userservice.service.AgencyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Override
    public List<AgencyResponse> findAllAgency() throws Exception {
        List<AgencyResponse> response = new ArrayList<>();
        List<Agency> agencyEntity = agencyRepository.findAgenciesByStatus(0);
        for(Agency item : agencyEntity){
            AgencyResponse agencyResponse = getAgency(item);
            response.add(agencyResponse);
        }
        return response;
    }

    @Override
    public AgencyResponse findById(Long id) throws Exception{
        Agency agency = agencyRepository.findById(id).orElse(null);
        if(agency == null){
            throw new Exception("Agency id does not exist!");
        }
        AgencyResponse response = getAgency(agency);
        return response;
    }

    @Override
    public void save(AddAgencyRequest request) throws Exception {
        checkAgencyCodeAndName(request.getCode(), request.getName());
        validateRequest(request);
        Agency agency = setAgency(request);
        agencyRepository.save(agency);
    }

    @Override
    public void update(EditAgencyRequest request) throws Exception {
        Agency oldAgency = agencyRepository.findById(request.getId()).orElse(null);
        if(oldAgency == null){
            throw new Exception("Agency id does not exist!");
        }
        validateRequest(request, oldAgency);
        agencyRepository.save(setAgency(request,oldAgency));
    }

    @Override
    public void remove(Long id) throws Exception {
        Agency oldAgency = agencyRepository.findById(id).orElse(null);
        if(oldAgency == null){
            throw new Exception("Commodity id does not exist");
        }
        oldAgency.setStatus(1);
        agencyRepository.save(oldAgency);
    }
    private AgencyResponse getAgency(Agency agency)throws Exception{
        AgencyResponse agencyResponse = new AgencyResponse();
        agencyResponse.setId(agency.getId());
        agencyResponse.setCode(agency.getCode());
        agencyResponse.setName(agency.getName());
        agencyResponse.setPhoneNumber(agency.getPhoneNumber());
        agencyResponse.setAddress(agency.getAddress());
        agencyResponse.setStatus(agency.getStatus());
        return agencyResponse;
    }
    private Agency setAgency(AddAgencyRequest request)throws Exception{
        Agency agency = new Agency();
        agency.setCode(request.getCode());
        agency.setName(request.getName());
        agency.setPhoneNumber(request.getPhoneNumber());
        agency.setAddress(request.getAddress());
        return agency;
    }

    private Agency setAgency(EditAgencyRequest request, Agency oldAgency){
        oldAgency.setCode(request.getCode());
        oldAgency.setName(request.getName());
        oldAgency.setAddress(request.getAddress());
        oldAgency.setPhoneNumber(request.getPhoneNumber());
        oldAgency.setStatus(request.getStatus());
        return oldAgency;
    }

    private void validateRequest(AddAgencyRequest request)throws Exception{
        if(StringUtils.isEmpty(request.getCode())){
            throw new Exception("Agency code cannot null");
        }
        if(StringUtils.isEmpty(request.getName())){
            throw new Exception("Agency name cannot null");
        }
        if(StringUtils.isEmpty(request.getAddress())){
            throw new Exception("Agency address cannot null");
        }
        if(StringUtils.isEmpty(request.getPhoneNumber())){
            throw new Exception("Agency phone number cannot null");
        }
    }

    private void validateRequest(EditAgencyRequest agencyRequest, Agency oldAgency )throws Exception{
        if(agencyRequest.getCode().equals(oldAgency.getCode())
                && !agencyRequest.getName().equals(oldAgency.getName())){
            checkAgencyName(agencyRequest.getName());

        }else if(!agencyRequest.getCode().equals(oldAgency.getCode())
                && agencyRequest.getName().equals(oldAgency.getName())){
            checkAgencyCode(agencyRequest.getCode());

        }else if(!agencyRequest.getCode().equals(oldAgency.getCode())
                && !agencyRequest.getName().equals(oldAgency.getName())){
            checkAgencyCodeAndName(agencyRequest.getCode(),agencyRequest.getName());
        }
    }

    private void checkAgencyName(String name)throws Exception{
        if(isExistName(name)){
            throw new Exception("Agency name is exist");
        }
    }

    private void checkAgencyCode(String code)throws Exception{
        if(isExistCode(code)){
            throw new Exception("Agency code is exist");
        }
    }

    private void checkAgencyCodeAndName(String code, String name)throws Exception{
        if(isExistCode(code) && isExistName(name)){
            throw new Exception("Agency code and name is exist");
        }else if(!isExistCode(code) && isExistName(name)){
            throw new Exception("Agency name is exist");
        }else if(isExistCode(code) && !isExistName(name)){
            throw new Exception("Agency code is exist");
        }
    }

    private boolean isExistCode(String code){
        List<Agency> agencies = agencyRepository.findByCode(code);
        if(agencies.size() >= 1){
            return true;
        }
        return false;
    }

    private boolean isExistName(String name){
        List<Agency> agencies = agencyRepository.findAgenciesByName(name);
        if(agencies.size() >= 1){
            return true;
        }
        return false;
    }
}
