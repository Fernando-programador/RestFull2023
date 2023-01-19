package com.br.fsc.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.fsc.exceptions.ResourceNotFoundException;
import com.br.fsc.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private Logger logger = Logger.getLogger(UserService.class.getName());
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	public UserService() {
	}

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("finding a one user by name" + username + "!!! -> encontrando um usuário e o seu nome");
		
		var entity = userRepository.findByUsername(username);
		
		if (entity != null) {
			return entity;
		}else {
			throw new ResourceNotFoundException("username " + username + " not found!!!");
		}
		
	}
	
}
