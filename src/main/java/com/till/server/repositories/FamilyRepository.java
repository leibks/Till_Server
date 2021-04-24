package com.till.server.repositories;

import com.till.server.models.Family;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface FamilyRepository extends CrudRepository<Family, String> {
    Family findFamilyByStudentIdsContains(String studentId);
}
