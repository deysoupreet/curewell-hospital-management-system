package com.curewell.hospital_management_system.controller;

import com.curewell.hospital_management_system.entity.Specialization;
import com.curewell.hospital_management_system.repository.SpecializationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specializations")
public class SpecializationController {

    private final SpecializationRepository specializationRepository;

    public SpecializationController(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    // POST → Create specialization
    @PostMapping
    public Specialization createSpecialization(@RequestBody Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    // GET → Fetch all
    @GetMapping
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }
}