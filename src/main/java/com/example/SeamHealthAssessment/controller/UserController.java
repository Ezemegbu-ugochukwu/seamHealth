package com.example.SeamHealthAssessment.controller;

import com.example.SeamHealthAssessment.dto.DoctorDTO;
import com.example.SeamHealthAssessment.dto.EditAddressDTO;
import com.example.SeamHealthAssessment.dto.RegistrationDTO;
import com.example.SeamHealthAssessment.service.AddressService;
import com.example.SeamHealthAssessment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/seamHealth/user")
public class UserController {

    private final DoctorService doctorService;
    private final AddressService addressService;

    @Autowired
    public UserController(DoctorService doctorService, AddressService addressService) {
        this.doctorService = doctorService;
        this.addressService = addressService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping("/register")
    public ResponseEntity<DoctorDTO> registerNewDoctor(@Valid @RequestBody RegistrationDTO registrationDTO) {
        return doctorService.registerDoctor(registrationDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getASingleDoctor(@PathVariable("id") Long id){
        return doctorService.getASingleDoctor(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EditAddressDTO> editDoctorAddress(@Valid @PathVariable("id") Long id, @RequestBody EditAddressDTO editAddressDTO){
        return addressService.editDoctorAddress(id, editAddressDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") Long id){
        doctorService.deleteDoctor(id);
        return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
    }
}
