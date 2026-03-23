package com.curewell.hospital_management_system.service.impl;

import com.curewell.hospital_management_system.dto.SurgeryRequestDTO;
import com.curewell.hospital_management_system.dto.SurgeryResponseDTO;
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
    public SurgeryResponseDTO createSurgeryFromDTO(SurgeryRequestDTO request) {

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

        Surgery saved = createSurgery(surgery);

        return mapToDTO(saved);
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

        List<Surgery> existingSurgeries = surgeryRepository.findByDoctorIdAndDate(doctorId, surgery.getDate());

        for (Surgery existing : existingSurgeries) {

            boolean isOverlapping = !(surgery.getEndTime().isBefore(existing.getStartTime()) ||
                    surgery.getStartTime().isAfter(existing.getEndTime()));

            if (isOverlapping) {
                throw new RuntimeException(
                        "Doctor with ID " + doctorId + " has a conflicting surgery");
            }
        }
    }

    private SurgeryResponseDTO mapToDTO(Surgery surgery) {

        SurgeryResponseDTO dto = new SurgeryResponseDTO();

        dto.setId(surgery.getId());

        dto.setDoctorName(
                surgery.getDoctor().getFirstName() + " " +
                        surgery.getDoctor().getLastName());

        dto.setSpecialization(
                surgery.getSpecialization().getName());

        dto.setDate(surgery.getDate());
        dto.setStartTime(surgery.getStartTime());
        dto.setEndTime(surgery.getEndTime());

        if (surgery.getAssistingDoctors() != null) {
            var names = surgery.getAssistingDoctors().stream()
                    .map(doc -> doc.getFirstName() + " " + doc.getLastName())
                    .toList();

            dto.setAssistingDoctorNames(names);
        }

        return dto;
    }

    @Override
    public List<SurgeryResponseDTO> getAllSurgeries() {

        return surgeryRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
}