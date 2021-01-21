package com.osweld.dev.auth.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osweld.dev.auth.services.JWTService;
import com.osweld.dev.auth.services.JWTServiceImpl;
import com.osweld.dev.auth.user.AuthUser;
import com.osweld.dev.models.entity.User;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	private JWTService jwtService;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login","POST"));
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = obtainPassword(request);
		String password = obtainPassword(request);
		
		if(username == null && password == null) {
			User user;
			try {
				user = new  ObjectMapper().readValue(request.getInputStream(), User.class);
				username = user.getUsername();
				password = user.getPassword();
			}catch( JsonParseException e) {
				e.printStackTrace();
			}catch( JsonMappingException e) {
				e.printStackTrace();
			}catch( IOException e) {
				e.printStackTrace();
			}
		}
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username.trim(),password);
		
		return authenticationManager.authenticate(authToken);
	}


	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = jwtService.create(authResult);
		response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX+token);
		AuthUser user = (AuthUser) authResult.getPrincipal();
		Map<String,Object> body = new HashMap<String,Object>();
		body.put("token", token);
		body.put("user", user);
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");
		
	}


	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String,Object> body = new HashMap<String,Object>();
		body.put("message", "Error: usuario o contraseña incorrecto");
		body.put("error",failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
	}
		
	
	
	
	
	
	
	
	
}
