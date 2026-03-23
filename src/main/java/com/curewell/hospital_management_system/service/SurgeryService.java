package com.curewell.hospital_management_system.service;

import com.curewell.hospital_management_system.dto.SurgeryRequestDTO;
import com.curewell.hospital_management_system.dto.SurgeryResponseDTO;
import com.curewell.hospital_management_system.entity.Surgery;

public interface SurgeryService {
    SurgeryResponseDTO createSurgeryFromDTO(SurgeryRequestDTO request);

    Surgery createSurgery(Surgery surgery);
}