package com.commoditymanagement.userservice.service;

import com.commoditymanagement.core.data.User;
import com.commoditymanagement.userservice.request.UserRequest;
import com.commoditymanagement.userservice.response.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll();

    UserResponse findById(Long id);

    User findByIdFromImportBill(Long id);

    User findByEmail(String email);

    void addUser(UserRequest userRequest) throws Exception;
}
