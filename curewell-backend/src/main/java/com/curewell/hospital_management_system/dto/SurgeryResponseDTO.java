package com.curewell.hospital_management_system.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "doctorName",
        "assistingDoctorNames",
        "specialization",
        "date",
        "startTime",
        "endTime"
})

@Data
public class SurgeryResponseDTO {

    private Long id;

    private String doctorName;
    private List<String> assistingDoctorNames;

    private String specialization;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}