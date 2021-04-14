package com.commoditymanagement.userservice.service;

import com.commoditymanagement.userservice.request.UserRequest;
import com.commoditymanagement.userservice.response.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();

    void addUser(UserRequest userRequest) throws Exception;
}
