package com.commoditymanagement.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.commoditymanagement.core.data.Role;
import com.commoditymanagement.core.data.User;
import com.commoditymanagement.userservice.repository.RoleRepository;
import com.commoditymanagement.userservice.repository.UserRepository;
import com.commoditymanagement.userservice.request.UserRequest;
import com.commoditymanagement.userservice.response.UserResponse;
import com.commoditymanagement.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public List<UserResponse> findAll() {
        List<UserResponse> listUser = new ArrayList<>();
        List<User> userEntity = userRepository.findAll();
        for(User item : userEntity){
            UserResponse dto = new UserResponse();
            dto.setId(item.getId());
            dto.setEmail(item.getEmail());
            dto.setAddress(item.getAddress());
            dto.setGender(item.getGender());
            dto.setRoleCode(item.getRole().getCode());
            dto.setFullName(item.getFullName());
            dto.setPhoneNumber(item.getPhoneNumber());
            dto.setStatus(item.getStatus());
            listUser.add(dto);
        }

        return listUser;
    }

	@Override
	public UserResponse findById(Long id) {
    	User user = userRepository.findById(id).orElse(null);
		UserResponse dto = new UserResponse();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setAddress(user.getAddress());
		dto.setGender(user.getGender());
		dto.setRoleCode(user.getRole().getCode());
		dto.setFullName(user.getFullName());
		dto.setPhoneNumber(user.getPhoneNumber());
		dto.setStatus(user.getStatus());
		return dto;
	}

	@Override
	public User findByIdFromImportBill(Long id) {
		User user = userRepository.findById(id).orElse(null);
		return user;
	}

	@Override
	public User findByEmail(String email) {
    	List<User> list = userRepository.findByEmail(email);
		return list.get(0);
	}

	@Override
	public void addUser(UserRequest userRequest) throws Exception {
		Role role = getRoleCode(userRequest.getRoleCode());
		getEmail(userRequest.getEmail());
		User user = new User();
		user.setFullName(userRequest.getFullName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setGender(userRequest.getGender());
		user.setPhoneNumber(userRequest.getPhoneNumber());
		user.setRole(role);
		user.setAddress(userRequest.getAddress());
		userRepository.save(user);
	}
	
	private Role getRoleCode(String code)throws Exception {
		if(StringUtils.isEmpty(code)) {
			throw new Exception("Role cannot null");
		}
		List<Role> role = roleRepository.findByCode(code);
		if(role.size() < 1) {
			throw new Exception("Role is not exist");
		}
		
		return role.get(0);
	}
	
	private void getEmail(String email) throws Exception {
		if(isExistEmail(email)) {
			throw new Exception("Email is exist");
		}	
	}
	
	private boolean isExistEmail(String email) {
		List<User> userEmail = userRepository.findByEmail(email);
		if(userEmail.size() >= 1) {
			return true;
		}
		return false;
	}
}
