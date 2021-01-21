package com.osweld.dev.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osweld.dev.models.entity.SubjectsPerCareer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectsPerCareerRepository extends JpaRepository<SubjectsPerCareer,Long>{

    @Query(value = "SELECT spc FROM SubjectsPerCareer spc WHERE spc.career.id = ?1 AND spc.cycle.id = ?2")
    public List<SubjectsPerCareer> findByCareerAndCycle(Long idCareer,Long idCycle);

}
