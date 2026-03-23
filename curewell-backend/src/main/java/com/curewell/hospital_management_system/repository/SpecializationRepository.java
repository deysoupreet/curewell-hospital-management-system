package com.curewell.hospital_management_system.repository;

import com.curewell.hospital_management_system.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
}