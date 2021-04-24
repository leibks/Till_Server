package com.till.server.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.io.Serializable;

@DynamoDBTable(tableName = "Students")
public class Student implements Serializable {

    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private GenderType gender;
    private String familyId;
    private String teacherIdList;

    public Student() {
    }

    public Student(String id, String firstName, String middleName, String lastName,
                   int age, GenderType gender, String familyId, String teacherIdList) {
        this.id = id;
        this.middleName = middleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.familyId = familyId;
        this.teacherIdList = teacherIdList;
    }

    @DynamoDBHashKey(attributeName="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DynamoDBAttribute
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @DynamoDBAttribute
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DynamoDBAttribute
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute
    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    @DynamoDBAttribute
    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    @DynamoDBAttribute
    public String getTeacherIdList() {
        return teacherIdList;
    }

    public void setTeacherIdList(String teacherIdList) {
        this.teacherIdList = teacherIdList;
    }
}
