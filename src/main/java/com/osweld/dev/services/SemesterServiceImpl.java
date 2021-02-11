package com.osweld.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osweld.dev.models.entity.Cycle;
import com.osweld.dev.models.entity.Semester;
import com.osweld.dev.models.entity.User;
import com.osweld.dev.models.entity.Year;
import com.osweld.dev.models.repository.SemesterRepository;

@Service
public class SemesterServiceImpl implements SemesterService{
	
	@Autowired
	private SemesterRepository semesterRepository;

	@Override
	public Semester getSemester(Long semesterId, Long userId) {
		if(semesterId == null || userId == null) return null;
		return semesterRepository.findById(semesterId, userId);
	}

	@Override
	public List<Semester> getAllSemesterByUserId(Long userId) {
		if(userId == null) return null;
		return semesterRepository.findByUser(userId);
	}

	@Override
	public Semester saveSemester(Semester semester, Long cycleId, Long yearId, Long userId) {
		if(userId == null || cycleId == null || yearId == null) return null;
		semester.setUser(new User(userId));
		semester.setCycle(new Cycle(cycleId));
		semester.setYear(new Year(yearId));
		return semesterRepository.save(semester);
	}

	@Override
	public void deleteSemester(Long semesterId, Long userId) {
		Semester semester = semesterRepository.findById(semesterId, userId);
		semesterRepository.delete(semester);
	}

}
