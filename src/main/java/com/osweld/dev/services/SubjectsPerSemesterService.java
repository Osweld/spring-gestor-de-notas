package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.SubjectsPerSemester;

public interface SubjectsPerSemesterService {
	
	public SubjectsPerSemester getSubjectsPerSemester(Long subjectsPerSemesterId,Long userId);
	public List<SubjectsPerSemester> getAllSubjectsPerSemesterBySemesterId(Long semesterId,Long userId);
	public SubjectsPerSemester saveSubjectsPerSemester(Long subjectsPerCareerId,Long semesterId,Long userId);
	public void deleteSubjectsPerSemester(Long subjectsPerSemesterId, Long userId);

}
