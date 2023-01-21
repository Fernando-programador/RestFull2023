package com.br.fsc.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.fsc.exceptions.security.InvalidJwtAuthenticationException;
import com.br.fsc.valueObject_v1.security.TokenVO;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {

	
	@Value("${security.jwt.token.secrety-key:secret}")
	private String secretKey = "secret";

	
	@Value("${security.jwt.token.expire-lenght:3600000}")
	private long validityInMilliseconds= 3600000;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	Algorithm algorithm = null; 
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}

	public TokenVO createAccessToken(String username, List<String> roles) {
		Date now  = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		
		var accessToken = getAccessToken(username, roles, now, validity);
		var refreshToken = getRefreshToken(username, roles, now);
		
		return new TokenVO(username, true, now, validity, accessToken, refreshToken);
	}

	/*
	 * issuerUrl ->captura a url do servidor
	 */
	private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
		String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();		
				return JWT.create()
						.withClaim("roles", roles)
						.withIssuedAt(now)
						.withExpiresAt(validity)
						.withSubject(username)
						.withIssuer(issuerUrl)
						.sign(algorithm)
						.strip();
	}

	/*
	 * validityRefreshToken -> estou pegando o valor do toke que é por uma hora e
	 * estou mutiplicando por 3, assim o refresh token vale por 3horas
	 */
	private String getRefreshToken(String username, List<String> roles, Date now) {
		
		Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3)); 
		
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(validityRefreshToken)
				.withSubject(username)
				.sign(algorithm)
				.strip();
	}

	/*
	 * cria a autenticação
	 * userDetails vai trazer o nome do usuario
	 */
	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJWT = decodedToken(token); 
		
		UserDetails userDetails = this.userDetailsService
				.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	/*
	 * este metodo vai ler o meu token descriptografando
	 */
	private DecodedJWT decodedToken(String token) {
		
		Algorithm algori = Algorithm.HMAC256(secretKey.getBytes());
		
		JWTVerifier verifier = JWT.require(algori).build();
		
		DecodedJWT jwt = verifier.verify(token);
		
		return jwt;
	}
	
	/*metodo para tirar a palavra Bearer do token
	 * o bearer token vem assim Bearer sdfmsdmfç...
	 * então para resgatar apenas o toque tirando o Bearer 
	 * para tirar vou usar o subString
	 */
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		}else
		return null;
	}
	
	/*
	 * metodo para validar o token
	 */
	public boolean validateToken(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		try {
			if(decodedJWT.getExpiresAt().before(new Date())) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new InvalidJwtAuthenticationException("Expired or invalid token!");
		}
	}

}
