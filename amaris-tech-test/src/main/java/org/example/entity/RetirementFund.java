package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
@Builder
public class RetirementFund {

    private String id;
    private String name;
    private String category;
    private Integer minimumAmount;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

}
