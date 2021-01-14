package com.osweld.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.models.entity.Activity;
import com.osweld.dev.models.entity.ActivityNumber;
import com.osweld.dev.models.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService{
	
	@Autowired
	private ActivityRepository activityRepository;

	@Override
	@Transactional(readOnly = true)
	public Activity getActivity(Long activityId) {
		return activityRepository.findById(activityId).orElse(null);
	}

	@Override
	public List<Activity> getAllActivity() {
		return activityRepository.findAll();
	}

	

}
