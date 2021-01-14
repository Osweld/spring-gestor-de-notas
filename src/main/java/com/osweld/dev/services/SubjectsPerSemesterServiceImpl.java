package com.osweld.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.models.entity.Semester;
import com.osweld.dev.models.entity.SubjectsPerCareer;
import com.osweld.dev.models.entity.SubjectsPerSemester;
import com.osweld.dev.models.repository.SemesterRepository;
import com.osweld.dev.models.repository.SubjectsPerSemesterRepository;

@Service
public class SubjectsPerSemesterServiceImpl implements SubjectsPerSemesterService{
	
	@Autowired
	private SubjectsPerSemesterRepository subjectsPerSemesterRepository;
	
	@Autowired
	private SemesterRepository semesterRepository;

	@Override
	@Transactional(readOnly = true)
	public SubjectsPerSemester getSubjectsPerSemester(Long subjectsPerSemesterId, Long userId) {
		if(subjectsPerSemesterId == null || userId == null) return null;
		return subjectsPerSemesterRepository.findById(subjectsPerSemesterId, userId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SubjectsPerSemester> getAllSubjectsPerSemesterBySemesterId(Long semesterId, Long userId) {
		if(semesterId == null || userId == null) return null;
		return subjectsPerSemesterRepository.findBySemester(semesterId, userId);
	}

	@Override
	@Transactional
	public SubjectsPerSemester saveSubjectsPerSemester(SubjectsPerSemester subjectsPerSemester,
			Long subjectsPerCareerId, Long semesterId, Long userId) {
		if(subjectsPerCareerId == null || semesterId == null || userId == null) return null;
		Semester semester = semesterRepository.findById(semesterId, userId);
		subjectsPerSemester.setSemester(semester);
		subjectsPerSemester.setSubjectsPerCareer(new SubjectsPerCareer(subjectsPerCareerId));
		
		return subjectsPerSemesterRepository.save(subjectsPerSemester);
	}

	@Override
	@Transactional
	public void deleteSubjectsPerSemester(Long subjectsPerSemesterId, Long userId) {
		SubjectsPerSemester subjectsPerSemester = subjectsPerSemesterRepository.findById(subjectsPerSemesterId, userId);
		subjectsPerSemesterRepository.delete(subjectsPerSemester);
		
	}

}
