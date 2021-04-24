package com.till.server.repositories;

import com.till.server.models.Student;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
    List<Student> getStudentsByTeacherIdListContains(String teacherId);
}
