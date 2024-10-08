package org.example.repository;

import org.example.entity.RetirementFund;
import org.example.entity.Subscription;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;

@Repository
public class SubscriptionRepository {

    private final DynamoDbTable<Subscription> subscriptionTable;

    public SubscriptionRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.subscriptionTable = enhancedClient.table("Subscriptions", TableSchema.fromBean(Subscription.class));
    }

    public List<Subscription> getUserSubscriptions(String userId) {
        return subscriptionTable.scan().items().stream()
                .filter(subscription -> subscription.getUserId().equals(userId))
                .toList();
    }

    public void saveSubscription(Subscription subscription) {
        subscriptionTable.putItem(subscription);
    }

}
