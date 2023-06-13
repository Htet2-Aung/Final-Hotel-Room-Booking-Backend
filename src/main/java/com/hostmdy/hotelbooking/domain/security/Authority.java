package com.hostmdy.hotelbooking.domain.security;

import org.springframework.security.core.GrantedAuthority;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Authority implements GrantedAuthority{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4833807538211977997L;
	private final String authority;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		
		return authority;
	}

}
