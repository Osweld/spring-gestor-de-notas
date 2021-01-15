package com.osweld.dev.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.auth.user.AuthUser;
import com.osweld.dev.models.entity.User;
import com.osweld.dev.models.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) throw new UsernameNotFoundException("El usuario: "+username+" no existe en el sistema");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRol().getRol()));
		
		if(authorities.isEmpty()) throw new UsernameNotFoundException("El usuario no tiene roles asignados");
		
		return new AuthUser(user.getId(),user.getUsername(),user.getPassword(),user.getActive(), true,true,true,authorities);
	}

}
