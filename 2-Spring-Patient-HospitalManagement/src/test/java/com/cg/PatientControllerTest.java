package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.controller.PatientController;
import com.cg.entity.Patient;
import com.cg.service.IPatientService;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
	
	@Mock
	private IPatientService patientService;
	
	@InjectMocks
	private PatientController patientController;
	
	private Patient patient;
	
	@BeforeEach
	void setUp()
	{
		patient=new Patient(1,"sahil",987463782,"pune");
	}
	
	@Test
    void testGetAllDoctors() {
        when(patientService.findAll()).thenReturn(Arrays.asList(patient));

        var patients = patientController.getPatients();
        assertFalse(patients.isEmpty());
        assertEquals(1, patients.size());
        assertEquals("sahil", patients.get(0).getName());
    }
	
	@Test
    void testGetByDoctorId() {
        when(patientService.findPatientById(1)).thenReturn(Optional.of(patient));

        var result = patientController.findPatientById(1);
        assertTrue(result.isPresent());
        assertEquals("sahil", result.get().getName());
    }

   @Test
    void testCreateMyDoctor() {
        when(patientService.CreatePatient(patient)).thenReturn(patient);

        Patient createdPatient = patientController.createPatients(patient);
        assertNotNull(createdPatient);
        assertEquals("sahil", createdPatient.getName());
    }

   @Test
   void testDeleteMyDoctor() {
       // Mocking the deleteDoctor method to return true
       when(patientService.DeletePatientbyId(1)).thenReturn(true);

       // Call the controller method
       patientController.deletePatientById(1);

       // Verify that deleteDoctor was called exactly once with ID 1
       verify(patientService, times(1)).DeletePatientbyId(1);
   }

   @Test
    void testUpdateMyProduct()  {
        when(patientService.UpdateData(patient)).thenReturn(patient);

        Patient updatedPatient = patientController.UpdatePatient(patient);
        assertNotNull(updatedPatient);
        assertEquals("sahil", updatedPatient.getName());
    }
	
}