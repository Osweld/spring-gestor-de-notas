package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.Semester;

public interface SemesterService {
	
	public Semester getSemester(Long semesterId,Long userId);
	public List<Semester> getAllSemesterByUserId(Long userId);
	public Semester saveSemester(Semester semester,Long userId,Long cycleId,Long yearId);
	public void deleteSemester(Long semesterId, Long userId);
	

}
