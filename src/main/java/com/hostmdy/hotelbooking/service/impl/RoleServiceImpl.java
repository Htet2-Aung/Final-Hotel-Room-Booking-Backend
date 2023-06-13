package com.hostmdy.hotelbooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.hotelbooking.domain.security.Role;
import com.hostmdy.hotelbooking.repository.RoleRepository;
import com.hostmdy.hotelbooking.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
	
	private final RoleRepository roleRepository;

	@Override
	public Optional<Role> findById(Long id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return (List<Role>) roleRepository.findAll();
	}

}
