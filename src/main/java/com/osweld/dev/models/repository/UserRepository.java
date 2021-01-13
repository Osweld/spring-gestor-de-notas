package com.osweld.dev.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osweld.dev.models.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
