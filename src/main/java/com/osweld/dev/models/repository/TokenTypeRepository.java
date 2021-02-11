package com.osweld.dev.models.repository;


import com.osweld.dev.models.entity.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenTypeRepository extends JpaRepository<TokenType,Long> {
}
