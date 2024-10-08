package org.example.repository;

import org.example.entity.RetirementFund;
import org.example.entity.User;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
public class UserRepository {

    private final DynamoDbTable<User> userTable;

    public UserRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.userTable = enhancedClient.table("Users", TableSchema.fromBean(User.class));
    }

    public User getUserById(String id) {
        Key key = Key.builder().partitionValue(id).build();
        return userTable.getItem(r -> r.key(key));
    }

    public void saveUser(User user) {
        userTable.putItem(user);
    }
}
