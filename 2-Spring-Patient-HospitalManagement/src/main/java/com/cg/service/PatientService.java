package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Patient;
import com.cg.feign.AuthFeign;
import com.cg.repository.PatientRepository;

@Service
public class PatientService implements IPatientService {
	@Autowired
	PatientRepository patrepo;
	@Override
	public List<Patient> findAll() {
		return patrepo.findAll();
	}
	public Optional<Patient> findPatientById(int id) {
		return patrepo.findById(id);
	}
	
	public Patient CreatePatient(Patient p) {
		return patrepo.save(p);
	}
	
	public boolean DeletePatientbyId(int id) {
		if (patrepo.existsById(id)) {
	        patrepo.deleteById(id);;
	         return true;
	    } else {
	    	 return false;
	    }
			
	}
	
	public Patient UpdateData(Patient p) {	
		return patrepo.save(p);
	}
	
	@Autowired(required=true)
	AuthFeign authFeign;
	
	@Override
	public boolean hasPermission(String token) {
		boolean flag=authFeign.getTokenValidity(token);
		System.out.println("Flag Token:"+flag);
		return flag;
	}

}



