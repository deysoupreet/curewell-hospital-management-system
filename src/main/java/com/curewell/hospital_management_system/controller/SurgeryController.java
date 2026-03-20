package com.curewell.hospital_management_system.controller;

import com.curewell.hospital_management_system.entity.Surgery;
import com.curewell.hospital_management_system.service.SurgeryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surgeries")
public class SurgeryController {

    private final SurgeryService surgeryService;

    public SurgeryController(SurgeryService surgeryService) {
        this.surgeryService = surgeryService;
    }

    @PostMapping
    public Surgery createSurgery(@RequestBody Surgery surgery) {
        return surgeryService.createSurgery(surgery);
    }
}