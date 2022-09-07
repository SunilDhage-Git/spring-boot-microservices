package com.springboot.microservices.VaccinationCenter.Model;

import java.util.List;

import com.springboot.microservices.VaccinationCenter.Entity.VaccinationCenter;

public class VaccinationResponse {
	
	private VaccinationCenter center;
	
	private List<Citizen> citizens;

	public VaccinationCenter getCenter() {
		return center;
	}

	public void setCenter(VaccinationCenter center) {
		this.center = center;
	}

	public List<Citizen> getCitizens() {
		return citizens;
	}

	public void setCitizens(List<Citizen> citizens) {
		this.citizens = citizens;
	}
	
	

}
