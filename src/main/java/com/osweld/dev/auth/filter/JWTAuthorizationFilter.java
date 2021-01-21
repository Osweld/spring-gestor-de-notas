package com.osweld.dev.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.osweld.dev.auth.services.JWTService;
import com.osweld.dev.auth.services.JWTServiceImpl;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	private JWTService jwtService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
			JWTService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
	}
	
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header  = request.getHeader(JWTServiceImpl.HEADER_STRING);
		if(!requiresAutehtication(header)) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authtoken = null;
		
		if(jwtService.validate(header)) {
			authtoken = new UsernamePasswordAuthenticationToken(jwtService.getId(header), null, jwtService.getAuthorities(header));
		}
		
		SecurityContextHolder.getContext().setAuthentication(authtoken);
		chain.doFilter(request, response);
	}




	protected Boolean requiresAutehtication(String header) {
		if(header == null || !header.startsWith(JWTServiceImpl.TOKEN_PREFIX)) return false;
		return true;
	}

}
