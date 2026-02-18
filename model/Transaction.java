package model;

import java.time.LocalDateTime;

public class Transaction {

    private int transactionId;
    private int userId;
    private String type; // CREDIT / DEBIT / TRANSFER
    private double amount;
    private String description;
    private LocalDateTime dateTime;

    // No-argument constructor
    public Transaction() {
    }

    // Parameterized constructor
    public Transaction(int transactionId, int userId, String type,
                       double amount, String description, LocalDateTime dateTime) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}