package com.example.g508029.homefinancialcontrol.DB;

import com.example.g508029.homefinancialcontrol.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    void addTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction);
    Transaction getTransactionById(String id);
    List<Transaction> getAllTransactionsByMonth(int month, int year);
    void deleteTransaction(String id);
    List<Transaction> getLastTransactions(int quantity);
}
