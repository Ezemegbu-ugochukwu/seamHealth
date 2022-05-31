package com.example.SeamHealthAssessment.dto;


import com.example.SeamHealthAssessment.entity.Address;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
