package com.till.server.controllers;

import com.till.server.models.Family;
import com.till.server.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/families")
public class FamilyController {

    FamilyRepository familyRepository;

    public FamilyController() {}

    @Autowired
    public FamilyController(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    @GetMapping(path="/findAll")
    public List<Family> findAllFamilies() {
        System.out.println("find all families");
        return (List<Family>) this.familyRepository.findAll();
    }

    @GetMapping(path="/", params = {"studentId"})
    public Family findFamilyByStudentId(@RequestParam("studentId") String studentId) {
        return this.familyRepository.findFamilyByStudentIdsContains(studentId);
    }
}
