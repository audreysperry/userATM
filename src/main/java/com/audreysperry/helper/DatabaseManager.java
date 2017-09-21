package com.audreysperry.helper;

import com.audreysperry.models.BankTransaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private Connection conn;
    private Statement statement;


    public DatabaseManager(Connection conn) throws SQLException {
        this.conn = conn;
        this.statement = conn.createStatement();

    }


    public void createTransactionHistoryTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE transactionHistory (id INTEGER PRIMARY KEY, amount DOUBLE, transactionType STRING);");
    }

    public void dropTransactionHistoryTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS transactionHistory;");
    }
    public void insertIntoTransactionHistoryTable(double amount, String transactionType) throws SQLException {
        // Vulnerable to SQL injections
        String sqlString = String.format("INSERT INTO transactionHistory (amount, transactionType) VALUES (%f, '%s');", amount, transactionType);
        statement.executeUpdate(sqlString);
    }

    public void insertIntoTransactionHistoryTable(BankTransaction bankTransaction) throws SQLException{
        insertIntoTransactionHistoryTable(bankTransaction.getAmount(), bankTransaction.getTransactionType());

    }

    public double getAccountBalance() throws SQLException {
         ResultSet rs = statement.executeQuery("SELECT SUM (amount) FROM transactionHistory");
         float amount = rs.getFloat(1);
         return amount;
    }
}
