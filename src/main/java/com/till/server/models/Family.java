package com.till.server.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Families")
public class Family {
    String id;
    String studentIds;
    String userName;
    String password;
    String languagePrefer;
    String primaryContactRelation;
    String primaryContactName;
    String primaryEmail;
    String primaryPhone;
    String primaryPreferCommunicateWay;
    String secondContactRelation;
    String secondContactName;
    String secondEmail;
    String secondPhone;
    String secondPreferCommunicateWay;
    String religion;
    String additional;

    public Family(){}

    public Family(String id, String studentIds, String userName, String password,
                  String languagePrefer, String primaryContactRelation, String primaryContactName, String primaryEmail,
                  String primaryPhone, String primaryPreferCommunicateWay, String secondContactRelation,
                  String secondContactName, String secondEmail, String secondPhone, String secondPreferCommunicateWay,
                  String religion, String additional) {
        this.id = id;
        this.studentIds = studentIds;
        this.userName = userName;
        this.password = password;
        this.languagePrefer = languagePrefer;
        this.primaryContactRelation = primaryContactRelation;
        this.primaryContactName = primaryContactName;
        this.primaryEmail = primaryEmail;
        this.primaryPhone = primaryPhone;
        this.primaryPreferCommunicateWay = primaryPreferCommunicateWay;
        this.secondContactRelation = secondContactRelation;
        this.secondContactName = secondContactName;
        this.secondEmail = secondEmail;
        this.secondPhone = secondPhone;
        this.secondPreferCommunicateWay = secondPreferCommunicateWay;
        this.religion = religion;
        this.additional = additional;
    }

    @DynamoDBHashKey(attributeName="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    @DynamoDBAttribute
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDBAttribute
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBAttribute
    public String getLanguagePrefer() {
        return languagePrefer;
    }

    public void setLanguagePrefer(String languagePrefer) {
        this.languagePrefer = languagePrefer;
    }

    @DynamoDBAttribute
    public String getPrimaryContactName() {
        return primaryContactName;
    }

    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }

    @DynamoDBAttribute
    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    @DynamoDBAttribute
    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    @DynamoDBAttribute
    public String getPrimaryPreferCommunicateWay() {
        return primaryPreferCommunicateWay;
    }

    public void setPrimaryPreferCommunicateWay(String primaryPreferCommunicateWay) {
        this.primaryPreferCommunicateWay = primaryPreferCommunicateWay;
    }

    @DynamoDBAttribute
    public String getSecondContactName() {
        return secondContactName;
    }

    public void setSecondContactName(String secondContactName) {
        this.secondContactName = secondContactName;
    }

    @DynamoDBAttribute
    public String getSecondEmail() {
        return secondEmail;
    }

    public void setSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
    }

    @DynamoDBAttribute
    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    @DynamoDBAttribute
    public String getSecondPreferCommunicateWay() {
        return secondPreferCommunicateWay;
    }

    public void setSecondPreferCommunicateWay(String secondPreferCommunicateWay) {
        this.secondPreferCommunicateWay = secondPreferCommunicateWay;
    }

    @DynamoDBAttribute
    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    @DynamoDBAttribute
    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    @DynamoDBAttribute
    public String getPrimaryContactRelation() {
        return primaryContactRelation;
    }

    public void setPrimaryContactRelation(String primaryContactRelation) {
        this.primaryContactRelation = primaryContactRelation;
    }

    @DynamoDBAttribute
    public String getSecondContactRelation() {
        return secondContactRelation;
    }

    public void setSecondContactRelation(String secondContactRelation) {
        this.secondContactRelation = secondContactRelation;
    }
}
