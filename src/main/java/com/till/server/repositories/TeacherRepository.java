package com.till.server.repositories;

import com.till.server.models.Teacher;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, String> {

    Teacher findTeacherByUserName(String userName);

    Teacher findTeacherByEmail(String email);

    List<Teacher> findTeachersByClassId(String classId);
}
