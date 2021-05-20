package com.commoditymanagement.userservice.service;



import com.commoditymanagement.userservice.request.add.AddAgencyRequest;
import com.commoditymanagement.userservice.request.edit.EditAgencyRequest;
import com.commoditymanagement.userservice.request.get.SearchByNameAndStatus;
import com.commoditymanagement.userservice.response.AgencyResponse;

import java.util.List;

public interface AgencyService {
    List<AgencyResponse> findAllAgency() throws Exception;

    AgencyResponse findById(Long id) throws Exception;

    List<AgencyResponse> searchAllAgency(SearchByNameAndStatus request) throws Exception;

    void save(AddAgencyRequest request) throws Exception;

    void update(EditAgencyRequest request) throws Exception;

    void remove(Long id) throws Exception;

}
