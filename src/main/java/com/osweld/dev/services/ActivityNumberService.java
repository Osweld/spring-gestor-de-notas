package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.ActivityNumber;

public interface ActivityNumberService {

	public ActivityNumber getActivityNumber(Long activityNumberId);
	public List<ActivityNumber> getAllActivityNumber();
}
