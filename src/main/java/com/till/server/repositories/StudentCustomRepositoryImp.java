package com.till.server.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.till.server.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@EnableScan
@Repository
public class StudentCustomRepositoryImp implements StudentCustomRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepository.class);
    private DynamoDBMapper mapper;

    @Autowired
    public StudentCustomRepositoryImp(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Student> findStudentsByTeacherId(String teacherId) {
        HashMap<String, AttributeValue> eav = new HashMap<>();
        eav.put(":teacherIdList", new AttributeValue().withS(teacherId));

        LOGGER.info("find students by teacher ID: " + teacherId);

        DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
                .withIndexName("teacherId-index")
                .withConsistentRead(false)
                .withKeyConditionExpression("teacherId = :teacherId")
                .withExpressionAttributeValues(eav);

        List<Student> iList = mapper.query(Student.class, queryExpression);

        return iList;
    }
}
