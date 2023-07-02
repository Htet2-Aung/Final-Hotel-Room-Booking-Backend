package com.hostmdy.hotelbooking.resource;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.hotelbooking.config.JwtTokenProvider;
import com.hostmdy.hotelbooking.domain.User;
import com.hostmdy.hotelbooking.domain.security.Role;
import com.hostmdy.hotelbooking.domain.security.UserRoles;
import com.hostmdy.hotelbooking.payload.JwtLoginSuccessResponse;
import com.hostmdy.hotelbooking.payload.LoginRequest;
import com.hostmdy.hotelbooking.service.MapValidationErrorService;
import com.hostmdy.hotelbooking.service.RoleService;
import com.hostmdy.hotelbooking.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
public class UserController {

	private static final String TOKEN_PREFIX = "Bearer ";

	private final UserService userService;
	private final RoleService roleService;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;
	private final MapValidationErrorService mapErrorService;
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {

	ResponseEntity<?> errorResponse = mapErrorService.validate(result);
		
		Optional<User> userOpt = userService.findByUsername(loginRequest.getUsername());
		
		if(userOpt.isEmpty())
			return new ResponseEntity<String>("user not found",HttpStatus.NOT_FOUND);
		
		if(errorResponse != null)
			return errorResponse;
 
		
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = TOKEN_PREFIX+tokenProvider.generateToken(authentication);
		
		List<String> roleList = userOpt.get().getUserRoles().stream().map(ur -> ur.getRole().getName()).toList();
		
		return ResponseEntity.ok(new JwtLoginSuccessResponse(true,jwt,userOpt.get(),roleList));
		
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
		Optional<User> userOpt = userService.findByUsername(username);
		if (userOpt.isEmpty()) {
			return new ResponseEntity<String>("Username isn't found!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(userOpt.get(), HttpStatus.OK);

	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		Optional<User> userOpt = userService.findById(id);
		if (userOpt.isEmpty()) {
			return new ResponseEntity<String>("Username isn't found!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(userOpt.get(), HttpStatus.OK);

	}

	@PostMapping("/create")
	public ResponseEntity<?> register(@Valid @RequestBody User user) {

		Optional<Role> roleOpt = roleService.findById(1L);

		if (roleOpt.isEmpty()) {
			return new ResponseEntity<String>("role is not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Set<UserRoles> userRoles = new HashSet<>();
		userRoles.add(new UserRoles(user, roleOpt.get()));

		return new ResponseEntity<User>(userService.createUser(user, userRoles), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllUser() {

		List<User> userList = userService.findAllUser();

		if (userList.isEmpty())
			return new ResponseEntity<String>("no user found", HttpStatus.NOT_FOUND);

		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<User> update(@RequestBody User user) {

		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);

	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<User> updatePassword(@RequestBody User user) {

		return new ResponseEntity<User>(userService.updatePassword(user), HttpStatus.OK);
		 
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
		userService.deleteById(id);
		return new ResponseEntity<String>("User Id-" + id + " is deleted!", HttpStatus.OK);

	}

}
