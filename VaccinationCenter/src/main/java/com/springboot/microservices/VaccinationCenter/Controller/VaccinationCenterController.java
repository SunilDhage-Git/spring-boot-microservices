package com.springboot.microservices.VaccinationCenter.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.microservices.VaccinationCenter.Entity.VaccinationCenter;
import com.springboot.microservices.VaccinationCenter.Model.Citizen;
import com.springboot.microservices.VaccinationCenter.Model.VaccinationResponse;
import com.springboot.microservices.VaccinationCenter.Repositories.VaccinationCenterRepo;




@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

	@Autowired
	private VaccinationCenterRepo repo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter) {
		
		VaccinationCenter newVaccinationCenter = repo.save(vaccinationCenter);
		
		return new ResponseEntity<VaccinationCenter>(newVaccinationCenter, HttpStatus.OK);
	}
	
	
	@GetMapping(path="/id/{id}")
	@HystrixCommand(fallbackMethod = "handleCitizenDownTime")
	public ResponseEntity<VaccinationResponse> getAllData(@PathVariable Integer id) {
		
		VaccinationResponse response = new VaccinationResponse();
		
		// 1. Get vaccination center details
		
		VaccinationCenter centers = repo.findById(id).get();
		response.setCenter(centers);
		
		// 2. Get all citizens to register above vaccination center
		
		List<Citizen> listOfCitizens = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/" + id, List.class);
		
		response.setCitizens(listOfCitizens);
		
		return new ResponseEntity<VaccinationResponse>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<VaccinationResponse> handleCitizenDownTime(@PathVariable Integer id){

		VaccinationResponse response = new VaccinationResponse();
		
		// 1. Get vaccination center details
		
		VaccinationCenter centers = repo.findById(id).get();
		response.setCenter(centers);
		
		return new ResponseEntity<VaccinationResponse>(response, HttpStatus.OK);
	}
}
