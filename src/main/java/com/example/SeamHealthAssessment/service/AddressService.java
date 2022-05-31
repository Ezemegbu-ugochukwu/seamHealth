package com.example.SeamHealthAssessment.service;

import com.example.SeamHealthAssessment.dto.EditAddressDTO;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    ResponseEntity<EditAddressDTO> editDoctorAddress(Long id, EditAddressDTO EditAddressDto);
}
