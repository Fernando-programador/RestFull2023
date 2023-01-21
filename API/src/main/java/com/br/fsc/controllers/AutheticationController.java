package com.br.fsc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.fsc.models.User;
import com.br.fsc.repository.UserRepository;
import com.br.fsc.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AutheticationController {
/*	
	private UserRepository userRepository;
	
	private JwtUtils jwtUtils;
	
	private AutheticationManager autheticationManager;
	
	private UserService userService;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> authenticate(@RequestBody User user){
		return ResponseEntity.ok(jwtUtils.genereteToken(userService.loadUserByUsername(user)));
		
	}
	
	*/

}
