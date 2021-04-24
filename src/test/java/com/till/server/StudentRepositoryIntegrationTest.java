package com.till.server;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.till.server.models.GenderType;
import com.till.server.models.Student;
import com.till.server.repositories.StudentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@WebAppConfiguration
@TestPropertySource("classpath:application.properties")
@ContextConfiguration
public class StudentRepositoryIntegrationTest {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    StudentRepository studentRepository;

    @Before
    public void setup() throws Exception {

//        CreateTableRequest tableRequest = dynamoDBMapper
//                .generateCreateTableRequest(Student.class);
//        tableRequest.setProvisionedThroughput(
//                new ProvisionedThroughput(5L, 5L));
//        CreateTableResult table = amazonDynamoDB.createTable(tableRequest);
//        System.out.println("Success.  Table status: " + table.getTableDescription());
    }

    @Test
    public void testStudentDB() throws IOException {
        JsonParser parser = new JsonFactory()
                .createParser(new File("src/test/resources/students.json"));

        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();

        ObjectNode currentNode;

        while (iter.hasNext()) {

            currentNode = (ObjectNode) iter.next();

            String studentId = currentNode.path("Student ID").asText();
            String firstName = currentNode.path("First Name").asText();
            String middleName = currentNode.path("Middle Name").asText();
            String lastName = currentNode.path("Last Name").asText();
            int age = currentNode.path("Age").asInt();
            GenderType gender = GenderType.valueOf(currentNode.path("Gender")
                    .asText().toUpperCase(Locale.ROOT));
            String familyId = currentNode.path("Family ID").asText();
            String teacherId = currentNode.path("Teacher IDs").asText();

            try {
                dynamoDBMapper.save(new Student(studentId, firstName, middleName,
                        lastName, age, gender, familyId, teacherId));
                System.out.println("Put Student succeeded: " + studentId );

            }
            catch (Exception e) {
                System.err.println("Unable to add student: " + studentId);
                System.err.println(e.getMessage());
                break;
            }
        }
        parser.close();
    }

    @After
    public void cleanUp() throws Exception {
//        DeleteTableRequest tableRequest = dynamoDBMapper.generateDeleteTableRequest(Student.class);
//        try {
//            System.out.println("Attempting to delete table; please wait...");
//            amazonDynamoDB.deleteTable(tableRequest);
//            System.out.print("Success.");
//
//        }
//        catch (Exception e) {
//            System.err.println("Unable to delete table: ");
//            System.err.println(e.getMessage());
//        }
    }
}
