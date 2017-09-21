package com.audreysperry.helper;

import com.audreysperry.models.BankTransaction;

import java.sql.SQLException;
import java.util.Scanner;

public class UI {

    public static BankTransaction newDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much money would you like to deposit");
        double amount = scanner.nextDouble();
        String transactionType = "deposit";
        System.out.println("Thank you for your deposit.");
        return new BankTransaction(amount, transactionType);


    }

    public static BankTransaction newWithdrawal(DatabaseManager dbm) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much would you like to withdraw today? ");
        double positiveAmount = scanner.nextDouble();
        double amount = -+positiveAmount;
        String transactionType = "withdrawal";

        if (positiveAmount > dbm.getAccountBalance()) {
            System.out.println("Sorry you don't have enough money in your account.");
            System.out.println("Your account has: $" + dbm.getAccountBalance());
            System.out.println("How much would you like to withdraw?");
            amount = -scanner.nextDouble();
            return new BankTransaction(amount, transactionType);
        } else {
            return new BankTransaction(amount, transactionType);
        }
    }

}
