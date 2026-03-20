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

        // Fetch existing surgeries
        List<Surgery> existingSurgeries =
                surgeryRepository.findByDoctorIdAndDate(
                        surgery.getDoctor().getId(),
                        surgery.getDate()
                );

        // Check overlap
        for (Surgery existing : existingSurgeries) {

            if (!(surgery.getEndTime().isBefore(existing.getStartTime()) ||
                  surgery.getStartTime().isAfter(existing.getEndTime()))) {

                throw new RuntimeException("Doctor already has a surgery in this time slot");
            }
        }

        return surgeryRepository.save(surgery);
    }
}