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

    private int month;
    private int year;
    private double monthlyIncome;
    private double monthlyExpense;
    private HashMap<String, Double> categoriesTotalizer;

    private List<Transaction> transactions;

    public TransactionsMonthly(int month, int year, List<Transaction> transactions){
        this.period = new Date(year - 1900, month - 1, 1);
        this.transactions = transactions;
        this.month = month;
        this.year = year;
        this.initializeValues();
    }

    public int getMonth(){
        return this.month;
    }

    public int getYear(){
        return this.year;
    }

    public double getMonthlyBalanceValue(){
        return this.getMonthlyIncome() - this.getMonthlyExpense();
    }

    public Date getPeriod() {
        return this.period;
    }

    public double getMonthlyIncome() {
        return this.monthlyIncome;
    }

    public double getMonthlyExpense() {
        return this.monthlyExpense;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public HashMap<String, Double> getCategoriesTotalizer(){
        return this.categoriesTotalizer;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    public List<Transaction> getlastTransactions(int count){
        return null;
    }

    private void initializeValues(){
        double monthlyExpense = 0.0;
        double monthlyIncome = 0.0;
        HashMap<String, Double> maps = new HashMap<String, Double>();

        for(Transaction transaction : this.transactions){
            if (transaction.getType().equals(EXPENSE_DESCRIPTION)){
                monthlyExpense += transaction.getValue();

                String key = transaction.getCategory();
                double value = transaction.getValue();
                if(maps.containsKey(key))
                    value += maps.get(key);
                maps.put(key, value);
            }
            if (transaction.getType().equals(INCOME_DESCRIPTION)) {
                monthlyIncome += transaction.getValue();
            }
        }

        this.monthlyIncome = monthlyIncome;
        this.monthlyExpense = monthlyExpense;
        this.categoriesTotalizer = maps;
    }
}
