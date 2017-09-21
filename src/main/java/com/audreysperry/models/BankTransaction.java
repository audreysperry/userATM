package com.audreysperry.models;

public class BankTransaction {
    private int id;
    private double amount;
    private String transactionType;


    public BankTransaction(double amount, String transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
