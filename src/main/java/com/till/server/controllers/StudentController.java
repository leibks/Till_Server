package com.till.server.controllers;

import com.till.server.models.Student;
import com.till.server.models.StudentInfo;
import com.till.server.models.StudentPerformance;
import com.till.server.repositories.StudentInfoRepository;
import com.till.server.repositories.StudentPerformanceRepository;
import com.till.server.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    // create a new student performance by getting the json data
    // if the performance exist, it will update the performance
    @RequestMapping(path = "/performances/", method=RequestMethod.POST, headers="Accept=application/json")
    public StudentPerformance createStudent(@RequestBody StudentPerformance performance) {
        return performanceRepository.save(performance);
    }

    @GetMapping(path = "/performances/findAll")
    public List<StudentPerformance> findAllStudentPerformances() {
        return (List<StudentPerformance>) this.performanceRepository.findAll();
    }

    @GetMapping(path = "/performances/", params = {"teacherId", "studentId"})
    public List<StudentPerformance> findAllStudentPerformancesByTeacherId(@RequestParam("teacherId")String teacherId,
                                                                          @RequestParam("studentId")String studentId) {
        List<StudentPerformance> performances = this.performanceRepository.findStudentPerformancesByTeacherIdAndStudentId(teacherId, studentId);
        ArrayList<StudentPerformance> list = new ArrayList<>(performances);
        list.sort((o1, o2) -> {
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/dd", Locale.ENGLISH);
            try {
                calendar1.setTime(sdf.parse(o1.getUpdateDate()));
                calendar2.setTime(sdf.parse(o2.getUpdateDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long time1 = calendar1.getTimeInMillis();
            long time2 = calendar2.getTimeInMillis();
            return Long.compare(time2, time1);
        });
        // list.subList(0, 7);
        if (list.size() > 6) {
            return list.subList(0, 6);
        } else {
            return list;
        }
    }

    @GetMapping(path="info", params = {"studentId"})
    public StudentInfo findStudentInformationByStudentId(@RequestParam("studentId") String studentId) {
        return this.studentInfoRepository.findStudentInfoByStudentId(studentId);
    }

}
