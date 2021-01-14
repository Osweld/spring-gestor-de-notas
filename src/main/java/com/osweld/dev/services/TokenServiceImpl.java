package com.osweld.dev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osweld.dev.models.entity.Token;
import com.osweld.dev.models.repository.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService{
	//Falta ver si va a hacer necesario un token repository
	//Probablemente si ya que al momento de recibir el codigo mandado por email aqui pasarias a
	//active el usuario
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public Token getTokenByUserId(Long userId) {
		return null;
	}

	@Override
	public Token SaveToken(Token token) {
		return null;
	}

}
