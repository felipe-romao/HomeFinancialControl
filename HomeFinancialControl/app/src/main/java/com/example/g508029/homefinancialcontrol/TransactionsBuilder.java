package com.example.g508029.homefinancialcontrol;

import android.util.Log;

import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.model.TransactionsYearly;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class TransactionsBuilder {
    private TransactionRepository repository;

    public TransactionsBuilder(TransactionRepository repository){
        this.repository = repository;
    }

    public TransactionsMonthly buildTransactionsMonthly(int month, int year){
        List<Transaction> transactions = this.repository.getAllTransactionsByMonth(month, year);
        Log.d(TAG, "buildTransactionsMonthly: size transactions: " + transactions.size());
        return new TransactionsMonthly(month, year, transactions);
    }

    public TransactionsYearly buildTransactionsYearly(int initialMonth, int endMonth, int year){
        int count = initialMonth;
        List<TransactionsMonthly> transactionsMonthlies = new ArrayList<>();
        while (count <= endMonth ){
            transactionsMonthlies.add(this.buildTransactionsMonthly(count, year));
            count++;
        }
        return new TransactionsYearly(year, transactionsMonthlies);
    }
}
