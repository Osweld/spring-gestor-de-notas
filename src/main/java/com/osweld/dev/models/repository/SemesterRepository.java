package com.osweld.dev.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.osweld.dev.models.entity.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Long>{
	
	@Query(value = "SELECT s FROM Semester s WHERE s.user.id = ?1")
	public List<Semester> findByUser(Long userId);
	@Query(value = "SELECT s FROM Semester s WHERE s.id = ?1 and s.user.id = ?2")
	public Semester findById(Long id,Long userId);
//	@Query(value = "SELECT s FROM Semester s JOIN FETCH s.user u WHERE s.id = ?1")
//	public List<Semester> findByUser(Long userId);
//	@Query(value = "SELECT s FROM Semester s JOIN FETCH s.user u ON s.id = ?1 AND u.id = ?2")
//	public Semester findById(Long id,Long userId);

}
