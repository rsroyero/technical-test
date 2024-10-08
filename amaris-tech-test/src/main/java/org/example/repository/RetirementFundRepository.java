package org.example.repository;

import org.example.entity.RetirementFund;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;

@Repository
public class RetirementFundRepository {

    private final DynamoDbTable<RetirementFund> retirementFundTable;

    public RetirementFundRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.retirementFundTable = enhancedClient.table("RetirementFund", TableSchema.fromBean(RetirementFund.class));
    }

    public List<RetirementFund> getAllFunds() {
        return retirementFundTable.scan().items().stream().toList();
    }

    public RetirementFund getFundById(String id) {
        Key key = Key.builder().partitionValue(id).build();
        return retirementFundTable.getItem(r -> r.key(key));
    }
}
