package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.Role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByCode(String code);
}
