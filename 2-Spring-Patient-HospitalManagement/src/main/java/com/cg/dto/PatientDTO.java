package com.cg.dto;

import com.cg.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
  private int id;
  private String name;
  private int phone;
  private String address;
  
  public static PatientDTO fromEntity(Patient patient) {
	return new PatientDTO(patient.getId(),patient.getName(),patient.getPhone(),patient.getAddress());	  
  }
  
  public Patient toEntity() {
	  return new Patient(this.id,this.name,this.phone,this.address);
  }
}
