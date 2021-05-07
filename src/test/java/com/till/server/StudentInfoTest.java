package com.till.server;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
//import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
//import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.till.server.models.StudentInfo;
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
public class StudentInfoTest {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Before
    public void setup() throws Exception {

//        CreateTableRequest tableRequest = dynamoDBMapper
//                .generateCreateTableRequest(StudentInfo.class);
//        tableRequest.setProvisionedThroughput(
//                new ProvisionedThroughput(5L, 5L));
//        CreateTableResult table = amazonDynamoDB.createTable(tableRequest);
//        System.out.println("Success.  Table status: " + table.getTableDescription());
    }

    @Test
    public void testStudentInfoDB() throws IOException {
        JsonParser parser = new JsonFactory()
                .createParser(new File("src/test/resources/studentInfo.json"));

        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();

        ObjectNode currentNode;
        int increment = 0;

        while (iter.hasNext()) {

            currentNode = (ObjectNode) iter.next();

            String infoId = String.valueOf(increment);
            increment++;
            String studentId = currentNode.path("Student ID").asText();
            String familyId = currentNode.path("Family ID").asText();
            String dateUpdate = currentNode.path("Date Updated").asText();
            String dateOfBirth = currentNode.path("Date of Birth").asText();
            String firstLan = currentNode.path("First Language").asText();
            String homeLan = currentNode.path("Language Spoken at Home").asText();
            String gradeLevel = currentNode.path("Grade Level").asText();
            Boolean isNewStudent  = !currentNode.path("New Student Indicator").asText().equals("0");
            String strongSub = currentNode.path("Strongest Subjects").asText();
            String weakSub = currentNode.path("Weakest Subjects").asText();
            String health = currentNode.path("Health Details").asText();
            String additional = currentNode.path("Additional Information").asText();

            try {
                dynamoDBMapper.save(new StudentInfo(infoId, studentId, familyId,
                        dateUpdate, dateOfBirth, firstLan, homeLan, gradeLevel, isNewStudent,
                        strongSub, weakSub, health, additional));
                System.out.println("Put Student Information succeeded: " + studentId );

            }
            catch (Exception e) {
                System.err.println("Unable to add student information: " + studentId);
                System.err.println(e.getMessage());
                break;
            }
        }
        parser.close();
    }
}
