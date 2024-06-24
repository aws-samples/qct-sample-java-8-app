package com.bhoruka.bloodbank.dao.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "Inventory")
public class Inventory {

    @DynamoDBHashKey
    private String bloodGroup;

    @DynamoDBAttribute
    private Integer wbc;

    @DynamoDBAttribute
    private Integer pc;

    @DynamoDBAttribute
    private Integer ffp;

    @DynamoDBAttribute
    private Integer paedFfp;

    @DynamoDBAttribute
    private Integer plateletCone;

    @DynamoDBAttribute
    private Integer cryoPpt;

    @DynamoDBAttribute
    private Integer cfp;

    @DynamoDBAttribute
    private String remarks;
}
