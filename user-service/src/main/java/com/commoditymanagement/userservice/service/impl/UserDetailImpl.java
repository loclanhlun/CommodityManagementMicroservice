package com.commoditymanagement.userservice.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.commoditymanagement.core.data.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserDetailImpl implements UserDetails{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String email;
	
	private String roleCode;
	
	private String fullName;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	

	public UserDetailImpl(Long id, String email, String password,String fullName, String roleCode, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.roleCode = roleCode;
		this.authorities = authorities;
	}
	
	public static UserDetailImpl build(User user) {
		Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getCode()));
		return new UserDetailImpl(user.getId(),
				user.getEmail(),
				user.getPassword(),
				user.getFullName(),
				user.getRole().getCode(),
				authorities);
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailImpl user =  (UserDetailImpl) o;
		return Objects.equals(id,user.id);
	}
	
}
