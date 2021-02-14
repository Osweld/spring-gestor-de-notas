package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.Semester;

public interface SemesterService {
	
	public Semester getSemester(Long semesterId,Long userId);
	public List<Semester> getAllSemesterByUserId(Long userId);
	public Semester saveSemester(Long cycleId,Long yearId,Long userId);
	public void deleteSemester(Long semesterId, Long userId);
	

}
