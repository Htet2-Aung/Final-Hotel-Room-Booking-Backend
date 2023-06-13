package com.hostmdy.hotelbooking.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.hotelbooking.domain.security.Role;

public interface RoleService {
	Optional<Role> findById(Long id);
	
	List<Role> findAll();
}
