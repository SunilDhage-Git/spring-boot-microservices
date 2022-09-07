package com.springboot.microservices.CitizenService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservices.CitizenService.Entity.Citizen;
import com.springboot.microservices.CitizenService.Respositories.CitizenRepository;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
	
	@Autowired
	CitizenRepository repo;

	@RequestMapping(path="/hi")
	public String sayHello() {
		return "Hello";
	}
	
	@RequestMapping(path="/id/{id}")
	public ResponseEntity<List<Citizen>> getCitizensById(@PathVariable int id) {
		
		List<Citizen> citizenList = repo.findByVaccinationCenterId(id);
		
		return new ResponseEntity<List<Citizen>>(citizenList, HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen) {
		
		Citizen citizen = repo.save(newCitizen);
		
		return new ResponseEntity<Citizen>(citizen, HttpStatus.OK);
	}
}
