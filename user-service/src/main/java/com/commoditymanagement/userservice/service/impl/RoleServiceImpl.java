package com.commoditymanagement.userservice.service.impl;

import com.commoditymanagement.core.data.Role;
import com.commoditymanagement.userservice.repository.RoleRepository;
import com.commoditymanagement.userservice.response.RoleResponse;
import com.commoditymanagement.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleResponse> getAllRole() {
        List<RoleResponse> listResponse = new ArrayList<>();
        List<Role> listEntity = roleRepository.findAll();
        for(Role item : listEntity){
            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setId(item.getId());
            roleResponse.setCode(item.getCode());
            roleResponse.setName(item.getName());
            listResponse.add(roleResponse);
        }
        return listResponse;
    }
}
