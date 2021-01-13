package com.osweld.dev.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osweld.dev.models.entity.Token;

public interface TokenRepository extends JpaRepository<Token,Long>{

}
