package com.osweld.dev.auth.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osweld.dev.auth.mixin.SimpleGrantedAuthorityMixin;
import com.osweld.dev.auth.user.AuthUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements JWTService{

	//Logger log = LoggerFactory.getLogger(getClass());
	
	public static final String SECRET = Base64Utils.encodeToString("wGestorNotasw".getBytes());
	public static final Long EXPIRATION_DATE = 1000*60*60*3L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "authorization";

	@Override
	public String create(Authentication auth) throws IOException {
		AuthUser user  = (AuthUser) auth.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(authorities));
		//Agregar primero claims despues id y subject si no, no va a funcionar correctamente
		String token = Jwts.builder().setClaims(claims).setId(user.getId().toString())
				.setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_DATE))
				.compact();
		return token;
	}

	@Override
	public boolean validate(String token) {
		try {
			getClaims(token);
			return true;
		}catch(JwtException | IllegalArgumentException e) {
			return false;
		}
		
		
	}

	@Override
	public Claims getClaims(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(resolve(token)).getBody();
		return claims;
	}

	@Override
	public String refreshToken(Long id, String username, Collection<? extends GrantedAuthority> authorities) throws IOException {
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(authorities));
		//Agregar primero claims despues id y subject si no, no va a funcionar correctamente
		String token = Jwts.builder().setClaims(claims).setId(id.toString())
				.setSubject(username).signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_DATE))
				.compact();
		return token;
	}

	@Override
	public String getUsername(String token) {
		
		return getClaims(token).getSubject();
	}

	@Override
	public Long getId(String token) {
			return Long.parseLong(getClaims(token).getId());
	}

	@Override
	public Date getExpiration(String token) {
		return getClaims(token).getExpiration();
	}

	@Override
	public String resolve(String token) {
		if(token == null || !token.startsWith(TOKEN_PREFIX)) return null;
		return token.replace(TOKEN_PREFIX, "");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(String token) throws IOException {
		Object roles = getClaims(token).get("authorities");
		Collection<? extends GrantedAuthority> authorities = 
				Arrays.asList(new ObjectMapper()
						.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
		return authorities;
	}

}
