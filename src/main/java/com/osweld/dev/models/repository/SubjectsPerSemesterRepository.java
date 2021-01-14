package com.osweld.dev.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.osweld.dev.models.entity.SubjectsPerSemester;

public interface SubjectsPerSemesterRepository extends JpaRepository<SubjectsPerSemester,Long>{
	
	@Query(value = "SELECT sps FROM SubjectsPerSemester sps WHERE sps.semester.id = ?1 AND sps.semester.user.id = ?2")
	public List<SubjectsPerSemester> findBySemester(Long SemesterId,Long userId);
	@Query(value = "SELECT sps FROM SubjectsPerSemester sps WHERE sps.id = ?1 AND sps.semester.user.id = ?2")
	public SubjectsPerSemester findById(Long subjectsPerSemesterId,Long userId);
	

}
