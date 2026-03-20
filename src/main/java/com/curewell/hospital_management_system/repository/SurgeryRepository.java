package com.curewell.hospital_management_system.repository;

import com.curewell.hospital_management_system.entity.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
}