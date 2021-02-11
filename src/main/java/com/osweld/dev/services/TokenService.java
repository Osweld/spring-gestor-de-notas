package com.osweld.dev.services;

import com.osweld.dev.models.entity.Token;

public interface TokenService {

	public Token getToken(String token);
	public Token createToken(Long userId,Long tokenTypeId);
	public Token activatedToken(Token token);

}
