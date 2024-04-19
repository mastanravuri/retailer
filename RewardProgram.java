package com.ravuri.spring.example;

import java.util.HashMap;
import java.util.Map;

public class RewardProgram {
    
    public static class Transaction {
        String customerId;
        int month;
        double amount;
        
        public Transaction(String customerId, int month, double amount) {
            this.customerId = customerId;
            this.month = month;
            this.amount = amount;
        }
    }
    
    public static void calculateRewardPoints(Map<String, Integer> rewardPointsPerCustomer, Transaction[] transactions) {
        for (Transaction transaction : transactions) {
            int points = 0;
            double amountOver100 = Math.max(0, transaction.amount - 100);
            double amountOver50 = Math.max(0, transaction.amount - 50);
            
            //Update the point if transaction amount > 100 then 2 points plus > 50 then 1 point for each dollor
            points += (int) (2 * amountOver100 + amountOver50);
            
            // Update reward points for the customer
            rewardPointsPerCustomer.put(transaction.customerId, rewardPointsPerCustomer.getOrDefault(transaction.customerId, 0) + points);
        }
    }
    
    public static void main(String[] args) {
        Transaction[] transactions = {
            new Transaction("customer1", 1, 120),
            new Transaction("customer2", 1, 80),
            new Transaction("customer1", 2, 150),
            new Transaction("customer2", 2, 60),
            new Transaction("customer1", 3, 90),
            new Transaction("customer2", 3, 110)
        };
        
        Map<String, Integer> rewardPointsPerCustomer = new HashMap<>();
        
        calculateRewardPoints(rewardPointsPerCustomer, transactions);
        
        // Display reward points earned per customer per month
        for (Map.Entry<String, Integer> entry : rewardPointsPerCustomer.entrySet()) {
            System.out.println("Customer " + entry.getKey() + " total reward points: " + entry.getValue());
        }
    }
}
