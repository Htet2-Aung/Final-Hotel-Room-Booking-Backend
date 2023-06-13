package com.hostmdy.hotelbooking.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostmdy.hotelbooking.domain.User;
import com.hostmdy.hotelbooking.domain.security.UserRoles;
import com.hostmdy.hotelbooking.exception.UsernameAlreadyExistsException;
import com.hostmdy.hotelbooking.repository.RoleRepository;
import com.hostmdy.hotelbooking.repository.UserRepository;
import com.hostmdy.hotelbooking.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User createUser(User user, Set<UserRoles> userRoles)throws UsernameAlreadyExistsException {
		// TODO Auto-generated method stub
		
		Optional<User> userOpt = findByUsername(user.getUsername());
		
		if(userOpt.isPresent()) {
			throw new UsernameAlreadyExistsException("Username already exist!");
		}
		
		userRoles.forEach(ur -> roleRepository.save(ur.getRole()));
		
		user.getUserRoles().addAll(userRoles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return saveUser(user);
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
		userRepository.deleteById(id);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		
		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

}
