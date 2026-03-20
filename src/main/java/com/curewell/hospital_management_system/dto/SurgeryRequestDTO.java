package com.curewell.hospital_management_system.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class SurgeryRequestDTO {

    private Long doctorId;
    private List<Long> assistingDoctorIds;
    private Long specializationId;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}