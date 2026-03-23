package com.curewell.hospital_management_system.service.impl;

import com.curewell.hospital_management_system.entity.Doctor;
import com.curewell.hospital_management_system.entity.Surgery;
import com.curewell.hospital_management_system.exception.ResourceNotFoundException;
import com.curewell.hospital_management_system.exception.GlobalExceptionHandler;
import com.curewell.hospital_management_system.repository.DoctorRepository;
import com.curewell.hospital_management_system.service.DoctorService;
import com.curewell.hospital_management_system.repository.SurgeryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final SurgeryRepository surgeryRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository,
            SurgeryRepository surgeryRepository) {
        this.doctorRepository = doctorRepository;
        this.surgeryRepository = surgeryRepository;
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
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    @Override
    public void deleteDoctor(Long id) {

        List<Surgery> surgeries = surgeryRepository.findByDoctorId(id);

        if (!surgeries.isEmpty()) {
            throw new RuntimeException("Cannot delete doctor assigned to surgeries");
        }

        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        existing.setFirstName(doctor.getFirstName());
        existing.setLastName(doctor.getLastName());
        existing.setEmailId(doctor.getEmailId());

        return doctorRepository.save(existing);
    }
}