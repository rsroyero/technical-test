package org.example.service;

import org.example.entity.RetirementFund;
import org.example.entity.Subscription;
import org.example.entity.User;
import org.example.model.SubscriptionHistoryDTO;
import org.example.repository.RetirementFundRepository;
import org.example.repository.SubscriptionRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionService {

    @Autowired
    private RetirementFundRepository fundRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;


    public SubscriptionService(UserRepository userRepository, RetirementFundRepository fundRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public String subscribeUserToFund(String userId, String fundId) {
        User user = userRepository.getUserById(userId);
        RetirementFund fund = fundRepository.getFundById(fundId);

        if (user.getBalance() <= fund.getMinimumAmount()) {
            return "Insufficient funds.";
        }

        user.setBalance(user.getBalance() - fund.getMinimumAmount());
        String subscriptionId = UUID.randomUUID().toString();
        Subscription subscription = new Subscription(subscriptionId, userId, fundId, true);
        subscriptionRepository.saveSubscription(subscription);
        user.getActiveFunds().add(fundId);
        userRepository.saveUser(user);

        return "Subscription successful!";
    }

    public String cancelSubscription(String userId, String fundId) {
        User user = userRepository.getUserById(userId);
        RetirementFund fund = fundRepository.getFundById(fundId);

        user.setBalance(user.getBalance() + fund.getMinimumAmount());
        user.getActiveFunds().remove(fundId);
        user.getCancelledFunds().add(fundId);

        Subscription subscription = subscriptionRepository.getUserSubscriptions(userId).stream()
                .filter(sub -> sub.getFundId().equals(fundId) && sub.isActive())
                .findFirst()
                .orElse(null);

        if (subscription != null) {
            subscription.setActive(false);
            subscriptionRepository.saveSubscription(subscription);
        }

        userRepository.saveUser(user);

        return "Subscription cancelled!";
    }

    public List<SubscriptionHistoryDTO> getUserSubscriptionHistory(String userId) {
        List<SubscriptionHistoryDTO> history = new ArrayList<>();

        User user = userRepository.getUserById(userId);

        List<Subscription> subscriptions = subscriptionRepository.getUserSubscriptions(userId);

        for (Subscription subscription : subscriptions) {
            RetirementFund fund = fundRepository.getFundById(subscription.getFundId());

            SubscriptionHistoryDTO dto = new SubscriptionHistoryDTO(
                    user.getName(),
                    fund.getName(),
                    fund.getMinimumAmount(),
                    subscription.isActive()
            );

            history.add(dto);
        }

        return history;
    }
}
