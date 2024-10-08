package org.example.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.example.entity.RetirementFund;
import org.example.entity.Subscription;
import org.example.model.SubscriptionHistoryDTO;
import org.example.model.SubscriptionRequest;
import org.example.service.RetirementFundService;
import org.example.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribeToFund(@RequestBody SubscriptionRequest request) {
        subscriptionService.subscribeUserToFund(request.getUserId(), request.getFundId());
        return new ResponseEntity<>("successful", HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelSubscription(@RequestBody SubscriptionRequest request) {
        subscriptionService.cancelSubscription(request.getUserId(), request.getFundId());
        return new ResponseEntity<>("successful", HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<SubscriptionHistoryDTO>> getUserSubscriptionHistory(@RequestParam String userId) {
        List<SubscriptionHistoryDTO> subscriptions = subscriptionService.getUserSubscriptionHistory(userId);
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
}
