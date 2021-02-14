package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.SubjectsPerSemester;
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
		if(maxPercent(subjectsPerSemesterId,userId,assignment)) return null;
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

	@Override
	@Transactional
	public Assignment updateAssignment(Assignment assignment, Long userId) {

		if(assignment == null || userId == null) return null;
		if(maxPercent(assignment.getSubjectsPerSemester().getId(),userId,assignment)) return null;
		SubjectsPerSemester subjectsPerSemester = subjectsPerSemesterRepository.findById(assignment.getSubjectsPerSemester().getId(), userId);
		if(subjectsPerSemester == null) return null;
		return assignmentRepository.save(assignment);
	}


	private Boolean maxPercent(Long subjectsPerSemesterId, Long userId,Assignment assignment){
		int totalPercent = 0;
		List<Assignment> assignmentList = assignmentRepository.findBySubjectsPerSemester(subjectsPerSemesterId, userId);

		totalPercent = assignmentList.stream().mapToInt(i -> {
			return i.getId() == assignment.getId() ? 0 : i.getPercent();
		}).sum() + assignment.getPercent();

		if(totalPercent > 100){
			return true;
		}
		return false;
	}


}
