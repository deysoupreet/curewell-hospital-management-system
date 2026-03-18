package com.curewell.hospital_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "doctors"
)
public class Doctor {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Integer id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "emailId", nullable = false, unique = true)
    private String emailId;
}
