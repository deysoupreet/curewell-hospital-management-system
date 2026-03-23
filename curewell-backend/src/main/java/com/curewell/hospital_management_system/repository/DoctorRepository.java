package com.curewell.hospital_management_system.repository;

import com.curewell.hospital_management_system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    boolean existsByEmailId(String emailId);
}
