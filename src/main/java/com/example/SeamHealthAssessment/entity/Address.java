package com.example.SeamHealthAssessment.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String homeAddress;
    private String lga;
    private String state;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId")
    private Doctor doctor;
}
