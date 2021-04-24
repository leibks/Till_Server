package com.till.server.controllers;

import com.till.server.models.Student;
import com.till.server.models.StudentInfo;
import com.till.server.models.StudentPerformance;
import com.till.server.repositories.StudentInfoRepository;
import com.till.server.repositories.StudentPerformanceRepository;
import com.till.server.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/students")
public class StudentController {

    StudentRepository studentRepository;
    StudentPerformanceRepository performanceRepository;
    StudentInfoRepository studentInfoRepository;

    public StudentController() {}

    @Autowired
    public StudentController(StudentRepository studentRepository,
                             StudentPerformanceRepository performanceRepository,
                             StudentInfoRepository studentInfoRepository) {
        this.studentRepository = studentRepository;
        this.performanceRepository = performanceRepository;
        this.studentInfoRepository = studentInfoRepository;
    }

    @GetMapping("/findAll")
    public List<Student> findAllStudents() {
        return (List<Student>) this.studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Student> findStudentById(@PathVariable("id") String id) {
        return this.studentRepository.findById(id);
    }

    @GetMapping(path = "/", params = {"teacherId"})
    public List<Student> findStudentsByTeacherId(@RequestParam("teacherId")String teacherId) {
        return this.studentRepository.getStudentsByTeacherIdListContains(teacherId);
    }

    // create a new student by getting json data
    @RequestMapping(path = "/", method=RequestMethod.POST, headers="Accept=application/json")
    public Student createStudent(@RequestBody Student student) {
        return this.studentRepository.save(student);
    }


    @GetMapping(path = "/performances/findAll")
    public List<StudentPerformance> findAllStudentPerformances() {
        return (List<StudentPerformance>) this.performanceRepository.findAll();
    }

    @GetMapping(path = "/performances/", params = {"teacherId", "studentId"})
    public StudentPerformance findAllStudentPerformancesByTeacherId(@RequestParam("teacherId")String teacherId,
                                                                          @RequestParam("studentId")String studentId) {
        return this.performanceRepository.findStudentPerformanceByTeacherIdAndStudentId(teacherId, studentId);
    }

    @GetMapping(path="info", params = {"studentId"})
    public StudentInfo findStudentInformationByStudentId(@RequestParam("studentId") String studentId) {
        return this.studentInfoRepository.findStudentInfoByStudentId(studentId);
    }

}
