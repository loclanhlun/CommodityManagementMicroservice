package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByStatus(int status);

	List<User> findByEmail(String email);
}
