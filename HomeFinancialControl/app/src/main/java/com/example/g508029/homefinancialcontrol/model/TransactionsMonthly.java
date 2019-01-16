package com.example.g508029.homefinancialcontrol.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.INCOME_DESCRIPTION;

public class TransactionsMonthly {
    private Date period;
    private List<Transaction> transactions;

    public TransactionsMonthly(int month, int year, List<Transaction> transactions){
        this.period = new Date(year - 1900, month - 1, 1);
        this.transactions = transactions;
    }

    public double getMonthlyBalanceValue(){
        return this.getMonthlyIncome() - this.getMonthlyExpense();
    }

    public Date getPeriod() {
        return this.period;
    }

    public double getMonthlyIncome() {
        double monthlyIncome = 0.0;
        for(Transaction transaction : this.transactions){
            if (!transaction.getType().equals(INCOME_DESCRIPTION)) continue;
            monthlyIncome += transaction.getValue();
        }
        return monthlyIncome;
    }

    public double getMonthlyExpense() {
        double monthlyExpense = 0.0;
        for(Transaction transaction : this.transactions){
            if (!transaction.getType().equals(EXPENSE_DESCRIPTION)) continue;
            monthlyExpense += transaction.getValue();
        }
        return monthlyExpense;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    public List<Transaction> getlastTransactions(int count){
        List<Transaction> lastTransactions = new ArrayList<Transaction>();

        if(this.transactions == null || this.transactions.size() == 0){
            return lastTransactions;
        }
        int pos=this.transactions.size()-1;
        int itemsAdded = 0;
        while (pos >= 0 && count > itemsAdded) {
            lastTransactions.add(this.transactions.get(pos));
            pos--;
            itemsAdded++;
        }
        return lastTransactions;
    }

    public HashMap<String, Double> getCategoriesTotalizer(){
        HashMap<String, Double> maps = new HashMap<String, Double>();
        for(Transaction transaction: transactions){
            if(transaction.getType().equals(EXPENSE_DESCRIPTION)){
                String key   = transaction.getCategory();
                double value = transaction.getValue();
                if(maps.containsKey(key))
                    value += maps.get(key);
                maps.put(key, value);
            }
        }
        return maps;
    }
}
