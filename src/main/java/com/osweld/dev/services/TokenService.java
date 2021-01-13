package com.osweld.dev.services;

import com.osweld.dev.models.entity.Token;

public interface TokenService {
	
	public Token getTokenByUserId(Long userId);
	public Token SaveToken(Token token);
	

}
