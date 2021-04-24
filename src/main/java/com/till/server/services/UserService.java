package com.till.server.services;

import com.till.server.models.Student;
import com.till.server.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private StudentRepository repository;

    @Autowired
    public UserService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        List<Student> res = new ArrayList<>();
        res.add(new Student());
        res.add(new Student());

        return res;
    }
}
