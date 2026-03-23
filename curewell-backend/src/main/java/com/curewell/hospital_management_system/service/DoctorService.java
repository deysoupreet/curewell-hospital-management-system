package com.curewell.hospital_management_system.service;

import com.curewell.hospital_management_system.entity.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor createDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor getDoctorById(Long id);

    void deleteDoctor(Long id);
}