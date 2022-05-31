package com.example.SeamHealthAssessment.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Address addressId;
}
