package com.br.fsc.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;

import com.br.fsc.controllers.AutheticationController;
import com.br.fsc.security.JwtConfigurer;
import com.br.fsc.security.JwtTokenProvider;


@EnableWebSecurity
@Configuration
public class SecurityConfig{
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Bean
	AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration)throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();		
		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		return passwordEncoder;
	}
	
	/*
	@Bean
	protected BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;	
	}
	*/
	
    @Bean
     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        .httpBasic().disable()
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        		.authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/auth/signin", "/api/auth/refresh/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/api/person/**").authenticated()
                .requestMatchers("/api/book/**").permitAll()
                .requestMatchers("/users").denyAll()    
            )
        		.cors()
        		.and()
        		.apply(new JwtConfigurer(tokenProvider))
        		.and()
        		.build();
        
        
        
    }
    
 

    @Bean
     WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/auth/**");
    }




 
}
