package com.example.g508029.homefinancialcontrol.presenter.modelView;

public class TransactionsMonthlyModelView {
    private String date;
    private String incomeValue;
    private String expenseValue;
    private String balanceValue;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(String incomeValue) {
        this.incomeValue = incomeValue;
    }

    public String getExpenseValue() {
        return expenseValue;
    }

    public void setExpenseValue(String expenseValue) {
        this.expenseValue = expenseValue;
    }

    public String getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(String balanceValue) {
        this.balanceValue = balanceValue;
    }
}
