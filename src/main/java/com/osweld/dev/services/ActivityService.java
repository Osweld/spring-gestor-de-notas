package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.Activity;

public interface ActivityService {
	
	public Activity getActivity(Long activityId);
	public List<Activity> getAllActivity();

}
