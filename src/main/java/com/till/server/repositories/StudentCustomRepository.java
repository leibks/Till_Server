package com.till.server.repositories;

import com.till.server.models.Student;

import java.util.List;

public interface StudentCustomRepository {

    List<Student> findStudentsByTeacherId(String teacherId);
}
