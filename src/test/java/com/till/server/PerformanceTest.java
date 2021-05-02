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
import com.till.server.models.StudentPerformance;
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
public class PerformanceTest {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Before
    public void setup() throws Exception {

//        CreateTableRequest tableRequest = dynamoDBMapper
//                .generateCreateTableRequest(StudentPerformance.class);
//        tableRequest.setProvisionedThroughput(
//                new ProvisionedThroughput(5L, 5L));
//        CreateTableResult table = amazonDynamoDB.createTable(tableRequest);
//        System.out.println("Success.  Table status: " + table.getTableDescription());
    }

    @Test
    public void testStudentPerformanceDB() throws IOException {
        JsonParser parser = new JsonFactory()
                .createParser(new File("src/test/resources/studentPerformance.json"));

        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();

        ObjectNode currentNode;

        while (iter.hasNext()) {

            currentNode = (ObjectNode) iter.next();

            String studentId = currentNode.path("Student ID").asText();
            String teacherId = currentNode.path("Teacher ID").asText();
            String performDate = currentNode.path("Performance Date").asText();
            String participation = currentNode.path("Participation").asText();
            String behavior = currentNode.path("Behavior").asText();
            String teamwork = currentNode.path("Teamwork").asText();
            String assignment = currentNode.path("Assignment").asText();
            String pNote = currentNode.path("Note for Participation").asText();
            String bNote = currentNode.path("Note for Behavior").asText();
            String tNote = currentNode.path("Note for Teamwork").asText();
            String aNote = currentNode.path("Note for Assignment").asText();

            String performanceId = teacherId + "+" + studentId + "+" + performDate;

            try {
                dynamoDBMapper.save(new StudentPerformance(performanceId, studentId, teacherId,
                        performDate, participation, behavior, teamwork, assignment, pNote, bNote,
                        tNote, aNote));
                System.out.println("Put Student Performance succeeded: " + studentId );

            }
            catch (Exception e) {
                System.err.println("Unable to add student performance: " + studentId);
                System.err.println(e.getMessage());
                break;
            }
        }
        parser.close();
    }
}
