package com.curewell.hospital_management_system.service.impl;

import com.curewell.hospital_management_system.dto.SurgeryRequestDTO;
import com.curewell.hospital_management_system.entity.Surgery;
import com.curewell.hospital_management_system.repository.DoctorRepository;
import com.curewell.hospital_management_system.repository.SpecializationRepository;
import com.curewell.hospital_management_system.repository.SurgeryRepository;
import com.curewell.hospital_management_system.service.SurgeryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurgeryServiceImpl implements SurgeryService {

    private final SurgeryRepository surgeryRepository;
    private final DoctorRepository doctorRepository;
    private final SpecializationRepository specializationRepository;

    public SurgeryServiceImpl(SurgeryRepository surgeryRepository,
                              DoctorRepository doctorRepository,
                              SpecializationRepository specializationRepository) {

        this.surgeryRepository = surgeryRepository;
        this.doctorRepository = doctorRepository;
        this.specializationRepository = specializationRepository;
    }

    @Override
    public Surgery createSurgeryFromDTO(SurgeryRequestDTO request) {

        Surgery surgery = new Surgery();

        var doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        surgery.setDoctor(doctor);

        var specialization = specializationRepository.findById(request.getSpecializationId())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));
        surgery.setSpecialization(specialization);

        if (request.getAssistingDoctorIds() != null) {
            var assistingDoctors = doctorRepository.findAllById(request.getAssistingDoctorIds());
            surgery.setAssistingDoctors(assistingDoctors);
        }

        surgery.setDate(request.getDate());
        surgery.setStartTime(request.getStartTime());
        surgery.setEndTime(request.getEndTime());

        return createSurgery(surgery);
    }

    @Override
    public Surgery createSurgery(Surgery surgery) {

        validateDoctorSchedule(surgery.getDoctor().getId(), surgery);

        if (surgery.getAssistingDoctors() != null) {
            for (var doctor : surgery.getAssistingDoctors()) {
                validateDoctorSchedule(doctor.getId(), surgery);
            }
        }

        return surgeryRepository.save(surgery);
    }

    private void validateDoctorSchedule(Long doctorId, Surgery surgery) {

        List<Surgery> existingSurgeries =
                surgeryRepository.findByDoctorIdAndDate(doctorId, surgery.getDate());

        for (Surgery existing : existingSurgeries) {

            boolean isOverlapping =
                    !(surgery.getEndTime().isBefore(existing.getStartTime()) ||
                      surgery.getStartTime().isAfter(existing.getEndTime()));

            if (isOverlapping) {
                throw new RuntimeException(
                        "Doctor with ID " + doctorId + " has a conflicting surgery"
                );
            }
        }
    }
}