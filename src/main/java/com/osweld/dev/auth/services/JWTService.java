package com.osweld.dev.auth.services;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;

public interface JWTService {

	public String create(Authentication auth) throws IOException;
	public boolean validate(String token);
	public Claims getClaims(String token);
	public String refreshToken(Long id,String username,Collection<? extends GrantedAuthority> authorities) throws IOException;
	public String getUsername(String token);
	public Long getId(String token);
	public Date getExpiration(String token);
	public String resolve(String token);
	public Collection<? extends GrantedAuthority> getAuthorities(String token) throws IOException;
	
}
