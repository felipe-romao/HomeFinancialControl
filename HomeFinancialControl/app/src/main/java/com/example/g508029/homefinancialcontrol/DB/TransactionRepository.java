package com.example.g508029.homefinancialcontrol.DB;

import com.example.g508029.homefinancialcontrol.model.InfoTransactionGrouped;
import com.example.g508029.homefinancialcontrol.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    void addTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction);
    Transaction getTransactionById(String id);
    List<Transaction> getAllTransactionsByMonth(int month, int year);
    List<Transaction> getAllTransactionsByMonthAndType(int month, int year, String type);
    List<InfoTransactionGrouped> getPaymentModeSumByMonth(int month, int year);
    void deleteTransaction(String id);
    List<Transaction> getLastTransactions(int quantity);
    List<Transaction> getTransactionsByPaymentModeAndMonthAndYear(String paymentMode, int monthSelected, int year);
    List<Transaction> getTransactionsByCategoryAndMonthAndYear(String category, int monthSelected, int year);
}
