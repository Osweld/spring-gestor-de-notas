package com.osweld.dev.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.osweld.dev.models.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment,Long>{
	
	@Query(value = "SELECT a FROM Assignment a WHERE a.subjectsPerSemester.id = ?1 AND a.subjectsPerSemester.semester.user.id = ?2")
	public List<Assignment> findBySubjectsPerSemester(Long subjectsPerSemesterId,Long userId);
	@Query(value = "SELECT a FROM Assignment a WHERE a.id = ?1 AND a.subjectsPerSemester.semester.user.id = ?2")
	public Assignment findById(Long assignmentId,Long userId);

}
