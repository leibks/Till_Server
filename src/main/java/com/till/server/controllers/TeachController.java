package com.till.server.controllers;

import com.till.server.models.Teacher;
import com.till.server.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/teachers")
public class TeachController {

    TeacherRepository teacherRepository;

    public TeachController() {}

    @Autowired
    public TeachController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping(path = "/{id}")
    public Optional<Teacher> findTeacherById(@PathVariable("id") String id) {
        return teacherRepository.findById(id);
    }

    // equal to @GetMapping("/findAll")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Teacher> findAll() {
        return (List<Teacher>) teacherRepository.findAll();
    }

    @GetMapping(path = "/", params = {"userName"})
    public Teacher findTeacherByUserName(@RequestParam("userName") String username) {
        return teacherRepository.findTeacherByUserName(username);
    }

    @GetMapping(path = "/", params = {"email"})
    public Teacher findTeacherByEmail(@RequestParam("email") String email) {
        return teacherRepository.findTeacherByEmail(email);
    }

    @GetMapping(path = "/", params = {"classId"})
    public List<Teacher> findTeachersByClassId(@RequestParam("classId") String classId) {
        return teacherRepository.findTeachersByClassId(classId);
    }

}
