package com.osweld.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.models.entity.ActivityNumber;
import com.osweld.dev.models.repository.ActivityNumberRepository;

@Service
public class ActivityNumberServiceImpl implements ActivityNumberService{
	
	@Autowired
	private ActivityNumberRepository activityNumberRepository;

	@Override
	@Transactional(readOnly = true)
	public ActivityNumber getActivityNumber(Long activityNumberId) {
		return activityNumberRepository.findById(activityNumberId).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ActivityNumber> getAllActivityNumber() {
		return activityNumberRepository.findAll();
	}

}
