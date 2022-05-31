package com.example.SeamHealthAssessment.service;


import com.example.SeamHealthAssessment.dto.DoctorDTO;
import com.example.SeamHealthAssessment.dto.RegistrationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {
    ResponseEntity<List<DoctorDTO>> getAllDoctors();
    ResponseEntity<DoctorDTO> registerDoctor(RegistrationDTO registrationDto);
    ResponseEntity<DoctorDTO> getASingleDoctor(Long id);
    ResponseEntity<String> deleteDoctor(Long id);
}
