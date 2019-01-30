package com.example.g508029.homefinancialcontrol.model;

import java.util.List;

public class TransactionsYearly {
    private int year;
    private double yearlyIncome;
    private double yearlyExpense;
    private List<TransactionsMonthly> transactionsMonthlies;

    public TransactionsYearly(int year, List<TransactionsMonthly> transactionsMonthlies){
        this.year = year;
        this.transactionsMonthlies = transactionsMonthlies;
        this.initializeValues();
    }

    public double getYearlyIncome(){
        return this.yearlyIncome;
    }

    public double getYearlyExpense(){
        return this.yearlyExpense;
    }

    public int getYear(){
        return this.year;
    }

    public List<TransactionsMonthly> getTransactionsMonthlies() {
        return transactionsMonthlies;
    }

    public double getYearlyBalanceValue(){
        return this.yearlyIncome - this.yearlyExpense;
    }

    private void initializeValues(){
        double yearIncome = 0.0;
        double yearExpense = 0.0;

        for (TransactionsMonthly transactionsMonthly: transactionsMonthlies){
            yearExpense += transactionsMonthly.getMonthlyExpense();
            yearIncome += transactionsMonthly.getMonthlyIncome();
        }

        this.yearlyExpense = yearExpense;
        this.yearlyIncome = yearIncome;
    }
}
