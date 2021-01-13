package com.osweld.dev.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osweld.dev.models.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long>{

}
