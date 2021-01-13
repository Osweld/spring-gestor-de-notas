package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.Career;

public interface CareerService {
	
	public Career getCareer(Long careerId);
	public List<Career> getallCareer();
	public Career saveCareer(Career career);
	public void deleteCareer(Long careerId);
	

}
