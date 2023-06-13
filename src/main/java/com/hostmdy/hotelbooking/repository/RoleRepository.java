package com.hostmdy.hotelbooking.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.hotelbooking.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
