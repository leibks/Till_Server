package com.till.server.repositories;

import com.till.server.models.StudentPerformance;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface StudentPerformanceRepository extends CrudRepository<StudentPerformance, String> {
    List<StudentPerformance> findStudentPerformancesByTeacherIdAndStudentId(String teacherId, String studentId);
    List<StudentPerformance> findStudentPerformancesByTeacherId(String teacherId);
}
