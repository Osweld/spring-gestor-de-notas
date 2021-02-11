package com.osweld.dev.services;

import com.osweld.dev.models.entity.TokenType;
import com.osweld.dev.models.entity.User;
import com.osweld.dev.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osweld.dev.models.entity.Token;
import com.osweld.dev.models.repository.TokenRepository;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService{
	// 1 active 2 password
	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public Token getToken(String token) {
		return tokenRepository.findByToken(token).orElse(null);
	}


	@Override
	public Token createToken(Long userId,Long tokenTypeId) {

		UUID uuid = UUID.randomUUID();
		Date date = new Date(System.currentTimeMillis()+(1000*60*15));
		Token token = tokenRepository.save(new Token(uuid.toString(),false,date,new User(userId),new TokenType(tokenTypeId)));
		return token;
	}

	@Override
	public Token activatedToken(Token token) {
		return tokenRepository.save(token);
	}


}
