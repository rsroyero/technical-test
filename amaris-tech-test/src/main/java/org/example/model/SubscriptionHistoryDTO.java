package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionHistoryDTO {
    private String userName;
    private String fundName;
    private double minimumAmount;
    private boolean active;
}