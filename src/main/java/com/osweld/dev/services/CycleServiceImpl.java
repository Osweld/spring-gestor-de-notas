package com.osweld.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.models.entity.Cycle;
import com.osweld.dev.models.repository.CycleRepository;
@Service
public class CycleServiceImpl implements CycleService{
	
	@Autowired
	private CycleRepository cycleRepository;

	@Override
	@Transactional(readOnly = true)
	public Cycle getCycle(Long cycleId) {
		return cycleRepository.findById(cycleId).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cycle> getAllCycle() {
		return cycleRepository.findAll();
	}

}
