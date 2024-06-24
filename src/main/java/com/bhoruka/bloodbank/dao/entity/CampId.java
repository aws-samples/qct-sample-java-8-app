package com.bhoruka.bloodbank.dao.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampId implements Serializable {

    private static final long serialVersionUID = 1L;

    @DynamoDBHashKey
    private String id;

    @DynamoDBRangeKey
    private String partnerId;

}
