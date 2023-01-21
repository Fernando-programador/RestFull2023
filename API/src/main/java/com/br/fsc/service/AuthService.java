package com.br.fsc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.fsc.repository.UserRepository;
import com.br.fsc.security.JwtTokenProvider;
import com.br.fsc.valueObject_v1.security.AccountCredentialsVO;
import com.br.fsc.valueObject_v1.security.TokenVO;

@Service
public class AuthService {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialsVO data) {
		try {
			var username  = data.getUsername(); //extraindo o username de accounrCredentialsVO
			var password  = data.getPassword(); //extraindo o password de accounrCredentialsVO
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password)); // tentando fazer o login
			var user = userRepository.findByUsername(username); // faço a busca de um usuario
			var tokenResponse = new TokenVO();
			
			if (user != null) {
				tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles()); // crio um token de acesso
			}else {
				throw new UsernameNotFoundException("Username " + username + " not found!");
			}
			return ResponseEntity.ok(tokenResponse);
			
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username/password suplied!");
		}
	}
	
	
	
	
	
	
	
	
}
