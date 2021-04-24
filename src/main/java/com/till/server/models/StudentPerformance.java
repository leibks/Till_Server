package com.till.server.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;

@DynamoDBTable(tableName = "StudentPerformances")
public class StudentPerformance implements Serializable  {
    private String id;
    private String studentId;
    private String teacherId;
    private String updateDate;
    private String participation;
    private String behavior;
    private String teamwork;
    private String assignment;
    private String notes;

    public StudentPerformance(){}

    public StudentPerformance(String id, String studentId, String teacherId, String updateDate,
                              String participation, String behavior, String teamwork,
                              String assignment, String notes) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.updateDate = updateDate;
        this.participation = participation;
        this.behavior = behavior;
        this.teamwork = teamwork;
        this.assignment = assignment;
        this.notes = notes;
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
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @DynamoDBAttribute
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @DynamoDBAttribute
    public String getParticipation() {
        return participation;
    }

    public void setParticipation(String participation) {
        this.participation = participation;
    }

    @DynamoDBAttribute
    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    @DynamoDBAttribute
    public String getTeamwork() {
        return teamwork;
    }

    public void setTeamwork(String teamwork) {
        this.teamwork = teamwork;
    }

    @DynamoDBAttribute
    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    @DynamoDBAttribute
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
