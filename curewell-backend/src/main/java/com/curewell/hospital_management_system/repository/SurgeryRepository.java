package com.curewell.hospital_management_system.repository;

import com.curewell.hospital_management_system.entity.Surgery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
    List<Surgery> findByDoctorIdAndDate(Long doctorId, LocalDate date);

    List<Surgery> findByDoctorId(Long doctorId);
}