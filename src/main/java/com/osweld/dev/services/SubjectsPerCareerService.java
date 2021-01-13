package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.SubjectsPerCareer;

public interface SubjectsPerCareerService {
	
	public SubjectsPerCareer getSubjectsPerCareer(Long subjectsPerCareerId);
	public List<SubjectsPerCareer> getAllSubjectsPerCareer();

}
