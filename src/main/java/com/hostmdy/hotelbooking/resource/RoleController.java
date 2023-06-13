package com.hostmdy.hotelbooking.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.hotelbooking.domain.security.Role;
import com.hostmdy.hotelbooking.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
	
	private final RoleService roleService;
	
	@GetMapping("/allRoles")
	public List<Role> getAllRole(){
		return roleService.findAll();
	}

}
