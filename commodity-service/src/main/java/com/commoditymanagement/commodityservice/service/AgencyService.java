package com.commoditymanagement.commodityservice.service;

import com.commoditymanagement.commodityservice.request.add.AddAgencyRequest;
import com.commoditymanagement.commodityservice.request.edit.EditAgencyRequest;
import com.commoditymanagement.commodityservice.response.AgencyResponse;

import java.util.List;

public interface AgencyService {
    List<AgencyResponse> findAllAgency() throws Exception;

    AgencyResponse findById(Long id) throws Exception;

    void save(AddAgencyRequest request) throws Exception;

    void update(EditAgencyRequest request, Long id) throws Exception;

    void remove(Long id) throws Exception;

}
