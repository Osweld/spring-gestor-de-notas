package com.osweld.dev.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osweld.dev.models.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUsername(String username);
	@Query(value = "SELECT u FROM User u WHERE u.person.email =  ?1")
	public User findByEmail(String email);
	@Query(value = "SELECT u FROM User u WHERE u.username =  ?1 or u.person.email = ?2")
	public User findByUsernameAndEmail(String username,String email);

}
