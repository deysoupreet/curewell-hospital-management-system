package com.curewell.hospital_management_system.service.impl;

import com.curewell.hospital_management_system.entity.Doctor;
import com.curewell.hospital_management_system.repository.DoctorRepository;
import com.curewell.hospital_management_system.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {

        if (doctorRepository.existsByEmailId(doctor.getEmailId())) {
            throw new RuntimeException("Doctor with this email already exists");
        }

        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }
}