package com.springboot.microservices.VaccinationCenter.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.microservices.VaccinationCenter.Entity.VaccinationCenter;

public interface VaccinationCenterRepo extends JpaRepository<VaccinationCenter, Integer> {

	
}
