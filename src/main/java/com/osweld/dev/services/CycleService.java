package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.Cycle;

public interface CycleService {
	
	public Cycle getCycle(Long cycleId);
	public List<Cycle> getAllCycle();

}
