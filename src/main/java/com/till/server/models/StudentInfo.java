package com.till.server.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;


@DynamoDBTable(tableName = "StudentInfo")
public class StudentInfo implements Serializable {
    String id;
    String studentId;
    String familyId;
    String dateUpdate;
    String dateOfBirth;
    String firstLanguage;
    String homeLanguage;
    String gradeLevel;
    Boolean isNewStudent;
    String strongSubjects;
    String weakSubjects;
    String healthDetails;
    String additionalInfo;

    public StudentInfo() {}

    public StudentInfo(String id, String studentId, String familyId, String dateUpdate,
                       String dateOfBirth, String firstLanguage, String homeLanguage,
                       String gradeLevel, Boolean isNewStudent, String strongSubjects,
                       String weakSubjects, String healthDetails, String additionalInfo) {
        this.id = id;
        this.studentId = studentId;
        this.familyId = familyId;
        this.dateUpdate = dateUpdate;
        this.dateOfBirth = dateOfBirth;
        this.firstLanguage = firstLanguage;
        this.homeLanguage = homeLanguage;
        this.gradeLevel = gradeLevel;
        this.isNewStudent = isNewStudent;
        this.strongSubjects = strongSubjects;
        this.weakSubjects = weakSubjects;
        this.healthDetails = healthDetails;
        this.additionalInfo = additionalInfo;
    }

    @DynamoDBHashKey(attributeName="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @DynamoDBAttribute
    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    @DynamoDBAttribute
    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @DynamoDBAttribute
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @DynamoDBAttribute
    public String getFirstLanguage() {
        return firstLanguage;
    }

    public void setFirstLanguage(String firstLanguage) {
        this.firstLanguage = firstLanguage;
    }

    @DynamoDBAttribute
    public String getHomeLanguage() {
        return homeLanguage;
    }

    public void setHomeLanguage(String homeLanguage) {
        this.homeLanguage = homeLanguage;
    }

    @DynamoDBAttribute
    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    @DynamoDBAttribute
    public Boolean getNewStudent() {
        return isNewStudent;
    }

    public void setNewStudent(Boolean newStudent) {
        isNewStudent = newStudent;
    }

    @DynamoDBAttribute
    public String getStrongSubjects() {
        return strongSubjects;
    }

    public void setStrongSubjects(String strongSubjects) {
        this.strongSubjects = strongSubjects;
    }

    @DynamoDBAttribute
    public String getWeakSubjects() {
        return weakSubjects;
    }

    public void setWeakSubjects(String weakSubjects) {
        this.weakSubjects = weakSubjects;
    }

    @DynamoDBAttribute
    public String getHealthDetails() {
        return healthDetails;
    }

    public void setHealthDetails(String healthDetails) {
        this.healthDetails = healthDetails;
    }

    @DynamoDBAttribute
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
