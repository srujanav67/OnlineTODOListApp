package com.deloitte.ca.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.deloitte.ca.entity.Role;
import com.deloitte.ca.entity.User;
import com.deloitte.ca.repository.RoleRepository;
import com.deloitte.ca.repository.UserRepository;
import com.deloitte.ca.service.UserService;
import com.deloitte.ca.web.dto.UserRegistrationDto;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		
		 Set<Role> rs = new HashSet<Role>();
	        rs.add(roleRepository.findOneByName("USER"));
	        
		User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), 
				registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()), 
				rs);
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getname(), user.getPassword(), grantedAuthorities);
		
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
