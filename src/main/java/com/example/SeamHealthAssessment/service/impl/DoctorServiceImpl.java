package com.example.SeamHealthAssessment.service.impl;


import com.example.SeamHealthAssessment.dto.DoctorDTO;
import com.example.SeamHealthAssessment.dto.RegistrationDTO;
import com.example.SeamHealthAssessment.entity.Address;
import com.example.SeamHealthAssessment.entity.Doctor;
import com.example.SeamHealthAssessment.exception.UserNotFoundException;
import com.example.SeamHealthAssessment.repository.AddressRepository;
import com.example.SeamHealthAssessment.repository.DoctorRepository;
import com.example.SeamHealthAssessment.service.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final AddressRepository addressRepository;
    private ModelMapper mapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, AddressRepository addressRepository, ModelMapper mapper) {
        this.doctorRepository = doctorRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctorList = doctorRepository.findAll()
                .stream()
                .map(this::mapToDoctorDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(doctorList, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<DoctorDTO> registerDoctor(RegistrationDTO registrationDto) {
        Optional<Doctor> foundDoctor = doctorRepository.findByEmail(registrationDto.getEmail());
        if (foundDoctor.isPresent()) {
            throw new UserNotFoundException("Email already exist");
        }
        Doctor doctor = mapToEntity(registrationDto);
        Doctor newDoctor = doctorRepository.saveAndFlush(doctor);

        Address address = Address.builder()
                .doctor(newDoctor)
                .homeAddress(registrationDto.getHomeAddress())
                .lga(registrationDto.getLga())
                .state(registrationDto.getState())
                .build();

        addressRepository.save(address);
        return new ResponseEntity<>(mapToDoctorDto(newDoctor), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<DoctorDTO> getASingleDoctor(Long id) {
        Doctor foundDoctor = doctorRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No User Found"));
        return new ResponseEntity<>(mapToDoctorDto(foundDoctor), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteDoctor(Long id) {
        Optional<Doctor> foundDoctor = doctorRepository.findById(id);
        if (foundDoctor.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        doctorRepository.delete(foundDoctor.get());
        return ResponseEntity.status(HttpStatus.OK).body("User with id " + id + " deleted successfully");
    }

    private DoctorDTO mapToDoctorDto(Doctor doctor) {
        return mapper.map(doctor, DoctorDTO.class);
    }

    public Doctor mapToEntity(RegistrationDTO registrationDto) {
        return mapper.map(registrationDto, Doctor.class);
    }

}
