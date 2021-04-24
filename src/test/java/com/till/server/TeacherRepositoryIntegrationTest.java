package com.till.server;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.till.server.models.Teacher;
import com.till.server.repositories.TeacherRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@WebAppConfiguration
@TestPropertySource("classpath:application.properties")
@ContextConfiguration
public class TeacherRepositoryIntegrationTest {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    TeacherRepository teacherRepository;

    @Before
    public void setup() throws Exception {

//        CreateTableRequest tableRequest = dynamoDBMapper
//                .generateCreateTableRequest(Teacher.class);
//        tableRequest.setProvisionedThroughput(
//                new ProvisionedThroughput(5L, 5L));
//        CreateTableResult table = amazonDynamoDB.createTable(tableRequest);
//        System.out.println("Success.  Table status: " + table.getTableDescription());
    }

    @Test
    public void testTeacherDB() throws IOException {
        JsonParser parser = new JsonFactory()
                .createParser(new File("src/test/resources/teachers.json"));

        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();

        ObjectNode currentNode;

        while (iter.hasNext()) {

            currentNode = (ObjectNode) iter.next();

            String id = currentNode.path("Teacher ID").asText();
            String userName = currentNode.path("Username").asText();
            String password = currentNode.path("Password").asText();
            String firstName = currentNode.path("First Name").asText();
            String middleName = currentNode.path("Middle Name").asText();
            String lastName = currentNode.path("Last Name").asText();
            String classId = currentNode.path("Class ID").asText();
            String summary = currentNode.path("Summary / Background").asText();
            String email = currentNode.path("Email Address").asText();
            String phone = currentNode.path("Phone Number").asText();


            try {
                dynamoDBMapper.save(new Teacher(id, userName, password, firstName,
                        middleName, lastName, classId, summary, email, phone));
                System.out.println("Put Teacher succeeded: " + userName );

            }
            catch (Exception e) {
                System.err.println("Unable to add teacher: " + userName);
                System.err.println(e.getMessage());
                break;
            }
        }
        parser.close();
    }
}
