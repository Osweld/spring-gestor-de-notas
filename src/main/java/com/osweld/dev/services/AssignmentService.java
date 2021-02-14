package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.Assignment;

public interface AssignmentService {

	public Assignment getAssignment(Long assignmentId,Long userId);
	public List<Assignment> getAllAssignmentBySubjectsPerSemesterId(Long subjectsPerSemesterId,Long userId);
	public Assignment saveAssignment(Assignment assignment,Long activityId,Long activityNumberId,Long subjectsPerSemesterId,Long userId);
	public void deleteAssignment(Long assignmentId,Long userId);
	public  Assignment updateAssignment(Assignment assignment,Long userId);
}
