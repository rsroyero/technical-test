package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
@Builder
public class User {

    private String id;
    private String name;
    private Double balance;
    private List<String> activeFunds = new ArrayList<>();
    private List<String> cancelledFunds = new ArrayList<>();

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
