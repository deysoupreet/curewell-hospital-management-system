package com.curewell.hospital_management_system.service.impl;

import com.curewell.hospital_management_system.entity.Surgery;
import com.curewell.hospital_management_system.repository.SurgeryRepository;
import com.curewell.hospital_management_system.service.SurgeryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurgeryServiceImpl implements SurgeryService {

    private final SurgeryRepository surgeryRepository;

    public SurgeryServiceImpl(SurgeryRepository surgeryRepository) {
        this.surgeryRepository = surgeryRepository;
    }

    @Override
    public Surgery createSurgery(Surgery surgery) {

        // Validate primary doctor
        validateDoctorSchedule(surgery.getDoctor().getId(), surgery);

        // Validate assisting doctors
        if (surgery.getAssistingDoctors() != null) {
            for (var doctor : surgery.getAssistingDoctors()) {
                validateDoctorSchedule(doctor.getId(), surgery);
            }
        }

        return surgeryRepository.save(surgery);
    }

    // Helper method to check overlap
    private void validateDoctorSchedule(Long doctorId, Surgery surgery) {

        List<Surgery> existingSurgeries =
                surgeryRepository.findByDoctorIdAndDate(doctorId, surgery.getDate());

        for (Surgery existing : existingSurgeries) {

            // Overlap condition
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