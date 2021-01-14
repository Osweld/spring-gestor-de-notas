package com.osweld.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.models.entity.Year;
import com.osweld.dev.models.repository.YearRepository;
@Service
public class YearServiceImpl implements YearService{
	
	@Autowired
	private YearRepository yearRepository;

	@Override
	@Transactional(readOnly = true)
	public Year getYear(long yearId) {
		return yearRepository.findById(null).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Year> getAllYear() {
		return yearRepository.findAll();
	}

}
