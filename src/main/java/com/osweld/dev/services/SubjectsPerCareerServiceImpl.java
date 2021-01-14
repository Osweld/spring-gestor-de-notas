package com.osweld.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.models.entity.SubjectsPerCareer;
import com.osweld.dev.models.repository.SubjectsPerCareerRepository;

@Service
public class SubjectsPerCareerServiceImpl implements SubjectsPerCareerService{
	
	@Autowired
	private 
	SubjectsPerCareerRepository subjectsPerCareerRepository;

	@Override
	@Transactional(readOnly = true)
	public SubjectsPerCareer getSubjectsPerCareer(Long subjectsPerCareerId) {
		return subjectsPerCareerRepository.findById(subjectsPerCareerId).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SubjectsPerCareer> getAllSubjectsPerCareer() {
		return subjectsPerCareerRepository.findAll();
	}

}
