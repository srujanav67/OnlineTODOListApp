package com.deloitte.ca.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.deloitte.ca.entity.User;
import com.deloitte.ca.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{	
	User save(UserRegistrationDto registrationDto);
	User findByUsername(String username);
}
