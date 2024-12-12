package com.cg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.PatientDTO;
import com.cg.entity.Patient;
import com.cg.service.IPatientService;

@RequestMapping("/api")
@RestController
public class PatientController {
	@Autowired
	IPatientService patientservice;
	
	//http://localhost:8082/api/patients
	@GetMapping("/patients")
	public List<Patient> getPatients()
	{
		return patientservice.findAll();
	}
	
	//http://localhost:8082/api/patients/id
	@GetMapping("/Patient/{id}")
	public Optional<Patient> findPatientById(@PathVariable int id){
		return patientservice.findPatientById(id);	
	}
	
//	//http://localhost:8082/api/patients
//	@PostMapping("/patients")
//	public PatientDTO createPatient(@Validated @RequestBody PatientDTO patientdto) {
//		Patient patient=patientdto.toEntity();
//		Patient createdPatient=patientservice.CreatePatient(patient);
//		return PatientDTO.fromEntity(createdPatient);
//	}
	
	//http://localhost:8082/api/patients
	@PostMapping("/patients")
	public Patient createPatients(@Validated @RequestBody Patient patient) {
		return patientservice.CreatePatient(patient);
	}
	
	//http://localhost:8082/api/patients/
	@DeleteMapping("/patients/{id}")
	public boolean deletePatientById(@PathVariable int id) {
		 return patientservice.DeletePatientbyId(id);
		
	}
	
	//http://localhost:8082/api/patients
	@PutMapping("/patients")
	public Patient UpdatePatient(@RequestBody Patient p) {
		return patientservice.UpdateData(p);
	}
	
	@GetMapping(path = "/PatientsAuth", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Patient> getPatients(@ RequestHeader("Authorization") String token){
		System.out.println("Hello");
		if (patientservice.hasPermission(token))
			  return patientservice.findAll();
		else
			return null;
	    }

}