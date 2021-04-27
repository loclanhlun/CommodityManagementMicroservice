package com.commoditymanagement.userservice.service;

import com.commoditymanagement.core.data.User;
import com.commoditymanagement.userservice.request.UserRequest;
import com.commoditymanagement.userservice.request.edit.EditUserRequest;
import com.commoditymanagement.userservice.response.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<UserResponse> findAll(String name, String status);

    UserResponse findById(Long id) throws Exception;

    User findByIdFromImportBill(Long id);

    User findByEmail(String email);

    void addUser(UserRequest userRequest) throws Exception;

    void updateUser(EditUserRequest request) throws Exception;

    void removeUser(Long id, String email) throws Exception;
}
