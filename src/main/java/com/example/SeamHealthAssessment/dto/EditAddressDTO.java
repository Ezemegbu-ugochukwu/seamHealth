package com.example.SeamHealthAssessment.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditAddressDTO {
    private String homeAddress;
    private String lga;
    private String state;
}
