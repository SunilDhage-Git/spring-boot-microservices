package com.springboot.microservices.CitizenService.Respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.springboot.microservices.CitizenService.Entity.Citizen;

public interface CitizenRepository extends CrudRepository<Citizen, Integer>{

	public List<Citizen> findByVaccinationCenterId(Integer id);
}
