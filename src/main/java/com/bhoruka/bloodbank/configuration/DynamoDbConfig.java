package com.bhoruka.bloodbank.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.bhoruka.bloodbank")
public class DynamoDbConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDbEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAwsAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAwsSecretKey;

    /**
     * Provides a configured Dynamo DB client.
     *
     * @return provides a Dynamo DB client
     */
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB
                = new AmazonDynamoDBClient(amazonAwsCredentials());

        if (!StringUtils.isEmpty(amazonDynamoDbEndpoint)) {
            amazonDynamoDB.setEndpoint(amazonDynamoDbEndpoint);
        }

        return amazonDynamoDB;
    }

    /**
     * Method which construct a credentials object using the credentials configured in the properties.
     *
     * @return AWS credentials configured in properties
     */
    @Bean
    public AWSCredentials amazonAwsCredentials() {
        return new BasicAWSCredentials(
                amazonAwsAccessKey, amazonAwsSecretKey);
    }
}
