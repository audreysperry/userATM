package com.audreysperry;

import java.sql.Connection;
import java.sql.DriverManager;
import com.audreysperry.helper.DatabaseManager;
import com.audreysperry.helper.UI;
import com.audreysperry.models.BankTransaction;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Scanner scanner = new Scanner(System.in);
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:Bank.db")) {
            DatabaseManager dbm = new DatabaseManager(conn);

            while(true) {
                System.out.println("=====--=====--====--=====");
                System.out.println("Welcome to your super friendly local bank");
                System.out.println("What would you like to do today? ");
                System.out.println("(1) Make a Deposit");
                System.out.println("(2) Make a Withdrawal");
                System.out.println("(3) How much do I have in the bank? ");
                System.out.println("(4) I forgot why I came. Leave the bank.");
                String response = scanner.nextLine();

                if (response.equals("1")) {
                    BankTransaction myTransaction = UI.newDeposit();
                    dbm.insertIntoTransactionHistoryTable(myTransaction);
                } else if (response.equals("2")) {
                    BankTransaction myTransaction = UI.newWithdrawal(dbm);
                    dbm.insertIntoTransactionHistoryTable(myTransaction);
                } else if (response.equals("3")) {
                    double accountBalance = dbm.getAccountBalance();
                    System.out.println(accountBalance);

                } else if (response.equals("4")) {
                    break;
                }

            }

        } catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Database error");

        }
    }
}
