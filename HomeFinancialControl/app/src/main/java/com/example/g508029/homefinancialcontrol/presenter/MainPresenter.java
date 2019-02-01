package com.example.g508029.homefinancialcontrol.presenter;

import android.util.Log;

import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.TransactionsBuilder;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static com.example.g508029.homefinancialcontrol.Constants.LAST_TRANSACTIONS_COUNTER;
import static com.example.g508029.homefinancialcontrol.Constants.MMMMyyyy_DATE_FORMAT_PATTERN;

public class MainPresenter {

    public interface IMainView{
        void setMonthlyIncomeValue(String value);
        void setMonthlyExpenseValue(String value);
        void setMonthlyBalanceValue(String value);
        void setMonthlyBalanceDate(String date);
        void setSummaryCategory(String category);
        void setLastTransactions(List<TransactionModelView> transactions);
        void setCategoryChart(HashMap<String, Double> values);
    }

    private IMainView view;
    private TransactionRepository repository;
    private FormatHelper formatHelper;

    public MainPresenter(IMainView view, TransactionRepository repository, FormatHelper formatHelper) {
        this.view = view;
        this.repository = repository;
        this.formatHelper = formatHelper;
    }

    public void initialize(){
        Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));
        int monthCurrent  = calendar.get(Calendar.MONTH) + 1 ;
        int yearCurrent   = calendar.get(Calendar.YEAR);
        Date dateCurrent  = calendar.getTime();

        Log.d(TAG, "initialize: month: " + monthCurrent);
        Log.d(TAG, "initialize: year: " + yearCurrent);
        Log.d(TAG, "initialize: date: " + dateCurrent);

        TransactionsBuilder transactionsBuilder = new TransactionsBuilder(repository);
        TransactionsMonthly transactionsMonthlyCurrent = transactionsBuilder.buildTransactionsMonthly(monthCurrent, yearCurrent);

        String periodText = this.formatHelper.fromDateToString(MMMMyyyy_DATE_FORMAT_PATTERN, dateCurrent);
        String monthlyIncomeValue = this.formatHelper.fromDoubleToCurrencyString(transactionsMonthlyCurrent.getMonthlyIncome());
        String monthlyExpenseValue = this.formatHelper.fromDoubleToCurrencyString(transactionsMonthlyCurrent.getMonthlyExpense());
        String monthlyBalanceValue = this.formatHelper.fromDoubleToCurrencyString(transactionsMonthlyCurrent.getMonthlyBalanceValue());
        HashMap<String, Double> chartValuesHashMap = transactionsMonthlyCurrent.getCategoriesTotalizer();
        List<Transaction> lastTransactions = this.repository.getLastTransactions(LAST_TRANSACTIONS_COUNTER);

        Log.d(TAG, "initialize: monthly income: " + monthlyIncomeValue);
        Log.d(TAG, "initialize: monthly expense: " + monthlyExpenseValue);
        Log.d(TAG, "initialize: monthly balance: " + monthlyBalanceValue);
        Log.d(TAG, "initialize: monthly chars keysets: " + chartValuesHashMap.keySet());


        this.view.setMonthlyBalanceDate(periodText);
        this.view.setSummaryCategory(periodText);
        this.view.setMonthlyIncomeValue(monthlyIncomeValue);
        this.view.setMonthlyExpenseValue(monthlyExpenseValue);
        this.view.setMonthlyBalanceValue(monthlyBalanceValue);
        this.view.setCategoryChart(chartValuesHashMap);
        this.view.setLastTransactions(TransactionHelper.toTransactionsModelViewList(lastTransactions, this.formatHelper));
    }
}
