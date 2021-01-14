package com.osweld.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.models.entity.Activity;
import com.osweld.dev.models.entity.ActivityNumber;
import com.osweld.dev.models.entity.Assignment;
import com.osweld.dev.models.repository.AssignmentRepository;
import com.osweld.dev.models.repository.SubjectsPerSemesterRepository;
@Service
public class AssignmentServiceImpl implements AssignmentService{
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private SubjectsPerSemesterRepository subjectsPerSemesterRepository;

	@Override
	@Transactional(readOnly = true)
	public Assignment getAssignment(Long assignmentId, Long userId) {
		if(assignmentId == null || userId == null) return null;
		return assignmentRepository.findById(assignmentId, userId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Assignment> getAllAssignmentBySubjectsPerSemesterId(Long subjectsPerSemesterId, Long userId) {
		if(subjectsPerSemesterId == null || userId == null) return null;
		return assignmentRepository.findBySubjectsPerSemester(subjectsPerSemesterId, userId);
	}

	@Override
	@Transactional
	public Assignment saveAssignment(Assignment assignment, Long activityId, Long activityNumberId,
			Long subjectsPerSemesterId, Long userId) {
		if(activityId == null || activityNumberId == null || subjectsPerSemesterId == null) return null;
		assignment.setSubjectsPerSemester(subjectsPerSemesterRepository.findById(subjectsPerSemesterId, userId));
		assignment.setActivity(new Activity(activityId));
		assignment.setActivityNumber(new ActivityNumber(activityNumberId));
		return assignmentRepository.save(assignment);
	}

	@Override
	@Transactional
	public void deleteAssignment(Long assignmentId, Long userId) {
		Assignment assignment = assignmentRepository.findById(assignmentId, userId);
		assignmentRepository.delete(assignment);
	}

	

}
