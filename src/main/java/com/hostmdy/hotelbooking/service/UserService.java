package com.hostmdy.hotelbooking.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hostmdy.hotelbooking.domain.User;
import com.hostmdy.hotelbooking.domain.security.UserRoles;

public interface UserService {
	
	Optional<User> findByUsername (String username);
	
	Optional<User> findById(Long id);
	
	User saveUser(User user);
	
	User createUser(User user, Set<UserRoles> userRoles);
	
	void deleteById(Long id);
	
	User updateUser(User user);
	
	User updatePassword(User user);
	
	List<User> findAllUser();
}
