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
import com.till.server.models.Family;
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
public class FamilyTest {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Before
    public void setup() throws Exception {

//        CreateTableRequest tableRequest = dynamoDBMapper
//                .generateCreateTableRequest(Family.class);
//        tableRequest.setProvisionedThroughput(
//                new ProvisionedThroughput(5L, 5L));
//        CreateTableResult table = amazonDynamoDB.createTable(tableRequest);
//        System.out.println("Success.  Table status: " + table.getTableDescription());
    }

    @Test
    public void testFamilyDB() throws IOException {
        JsonParser parser = new JsonFactory()
                .createParser(new File("src/test/resources/family.json"));

        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();

        ObjectNode currentNode;

        while (iter.hasNext()) {

            currentNode = (ObjectNode) iter.next();

            String familyId = currentNode.path("Family ID").asText();
            String studentId = currentNode.path("Student IDs").asText();
            String userName = currentNode.path("Username").asText();
            String password = currentNode.path("Password").asText();
            String lanPrefer = currentNode.path("Language Preferred").asText();
            String pRelation = currentNode.path("Primary Contact Relationship").asText();
            String pContactName = currentNode.path("Primary Contact Name").asText();
            String pEmail = currentNode.path("Primary Email Address").asText();
            String pPhone = currentNode.path("Primary Phone Number").asText();
            String pPreferCom = currentNode.path("Primary Preferred Communication Method").asText();
            String sRelation = currentNode.path("Second Contact Relationship").asText();
            String sContactName = currentNode.path("Secondary Contact Name").asText();
            String sEmail = currentNode.path("Secondary Email Address").asText();
            String sPhone = currentNode.path("Secondary Phone Number").asText();
            String sPreferCom = currentNode.path("Secondary Preferred Communication Method").asText();
            String religion = currentNode.path("Religion").asText();
            String additional = currentNode.path("Additional").asText();

            try {
                dynamoDBMapper.save(new Family(familyId, studentId, userName, password, lanPrefer,
                        pRelation, pContactName, pEmail, pPhone, pPreferCom, sRelation, sContactName, sEmail,
                        sPhone, sPreferCom, religion, additional));
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
