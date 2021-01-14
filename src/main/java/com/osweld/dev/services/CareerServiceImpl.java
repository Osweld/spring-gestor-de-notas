package com.osweld.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osweld.dev.models.entity.Career;
import com.osweld.dev.models.repository.CareerRepository;

@Service
public class CareerServiceImpl  implements CareerService{
	
	@Autowired
	private CareerRepository careerRepository;

	@Override
	@Transactional(readOnly = true)
	public Career getCareer(Long careerId) {
		return careerRepository.findById(careerId).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Career> getallCareer() {
		return careerRepository.findAll();
	}

	@Override
	@Transactional
	public Career saveCareer(Career career) {
		return careerRepository.save(career);
	}

	@Override
	@Transactional
	public void deleteCareer(Long careerId) {
		careerRepository.deleteById(careerId);
		
	}

}
