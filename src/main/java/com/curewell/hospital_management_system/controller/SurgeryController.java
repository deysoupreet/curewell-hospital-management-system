package com.curewell.hospital_management_system.controller;

import com.curewell.hospital_management_system.dto.SurgeryRequestDTO;
import com.curewell.hospital_management_system.dto.SurgeryResponseDTO;
import com.curewell.hospital_management_system.entity.Surgery;
import com.curewell.hospital_management_system.service.SurgeryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surgeries")
public class SurgeryController {

    private final SurgeryService surgeryService;

    public SurgeryController(SurgeryService surgeryService) {
        this.surgeryService = surgeryService;
    }

    @PostMapping
    public SurgeryResponseDTO createSurgery(@RequestBody SurgeryRequestDTO request) {
        return surgeryService.createSurgeryFromDTO(request);
    }

    @GetMapping
    public List<SurgeryResponseDTO> getAllSurgeries() {
        return surgeryService.getAllSurgeries();
    }
}