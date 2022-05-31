package com.example.SeamHealthAssessment.service.impl;

import com.example.SeamHealthAssessment.dto.EditAddressDTO;
import com.example.SeamHealthAssessment.entity.Address;
import com.example.SeamHealthAssessment.exception.AddressNotFoundException;
import com.example.SeamHealthAssessment.repository.AddressRepository;
import com.example.SeamHealthAssessment.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private ModelMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<EditAddressDTO> editDoctorAddress(Long id, EditAddressDTO editDoctorAddress) {
         Optional<Address> foundAddress = addressRepository.findAddressByDoctor_DoctorId(id);
         if (foundAddress.isPresent()) {
          foundAddress.get().setHomeAddress(editDoctorAddress.getHomeAddress());
          foundAddress.get().setLga(editDoctorAddress.getLga());
          foundAddress.get().setState(editDoctorAddress.getState());
          Address newAddress = addressRepository.save(foundAddress.get());
          return new ResponseEntity<>(mapToDto(newAddress), HttpStatus.OK);
         }else {
             throw new AddressNotFoundException("Address not found");
         }
    }

    public EditAddressDTO mapToDto(Address address){
        EditAddressDTO editAddressDto = mapper.map(address, EditAddressDTO.class);
        return editAddressDto;
    }
}
