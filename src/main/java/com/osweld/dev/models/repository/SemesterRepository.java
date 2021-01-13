package com.osweld.dev.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osweld.dev.models.entity.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Long>{

}
