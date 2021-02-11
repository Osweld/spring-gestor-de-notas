package com.osweld.dev.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osweld.dev.models.entity.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long>{

    Optional<Token> findByToken(String token);

}
