package com.osweld.dev.services;

import java.util.List;

import com.osweld.dev.models.entity.Year;

public interface YearService {

	public Year getYear(long yearId);
	public List<Year> getAllYear();
	
}
