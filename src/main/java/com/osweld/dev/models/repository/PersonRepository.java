package com.osweld.dev.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osweld.dev.models.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
