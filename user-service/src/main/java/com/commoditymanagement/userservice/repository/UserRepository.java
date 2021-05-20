package com.commoditymanagement.userservice.repository;

import com.commoditymanagement.core.data.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByStatus(int status);

	List<User> findByEmail(String email);

	@Query(value = "select u from User u where u.fullName like %:fullName%")
	List<User> findByFullNameLike(@Param("fullName") String fullName);

	@Query(value = "select * from User u where u.fullName like %:fullName% and u.status = :status", nativeQuery = true)
	List<User> findByFullNameLikeAndStatus(@Param("fullName") String fullName, @Param("status") int status);


}
