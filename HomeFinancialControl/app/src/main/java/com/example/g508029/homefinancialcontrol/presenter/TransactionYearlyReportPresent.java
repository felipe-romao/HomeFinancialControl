package com.example.g508029.homefinancialcontrol.presenter;

import android.util.Log;

import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.TransactionsBuilder;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;
import com.example.g508029.homefinancialcontrol.model.TransactionsYearly;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionsMonthlyModelView;

import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

public class TransactionYearlyReportPresent {
    public interface ITransactionYearlyReportView{
        void setYearSearch(String year);
        String getYearSearch();
        void setIncomeYearlyValue(String value);
        void setExpenseYearlyValue(String value);
        void setBalanceYearlyValue(String value);
        void setTransactionsMonthlyValueList(List<TransactionsMonthlyModelView> modelViews);
        void showMessage(String message);
    }

    private ITransactionYearlyReportView view;
    private TransactionRepository repository;
    private FormatHelper formatHelper;

    public TransactionYearlyReportPresent(ITransactionYearlyReportView view, TransactionRepository repository, FormatHelper formatHelper) {
        this.view = view;
        this.repository = repository;
        this.formatHelper = formatHelper;
    }

    public void initialize(){
        try {
            int yearCurrent = Calendar.getInstance().get(Calendar.YEAR);
            this.view.setYearSearch(String.valueOf(yearCurrent));
            this.onFindTransactionByYear();
        } catch (Exception ex){
            this.view.showMessage("Não foi possível inicializar o relatório: " + ex.getMessage());
        }
    }

    public void onFindTransactionByYear(){
        try {
            int yearSearch = Integer.valueOf(this.view.getYearSearch());
            Log.d(TAG, "onFindTransactionByYear: year search: " + yearSearch);
            TransactionsBuilder transactionsBuilder = new TransactionsBuilder(this.repository);
            TransactionsYearly transactionsYearly = transactionsBuilder.buildTransactionsYearly(1, 12, yearSearch);

            String incomeYearlyValue = this.formatHelper.fromDoubleToCurrencyString(transactionsYearly.getYearlyIncome());
            String expenseYearlyValue = this.formatHelper.fromDoubleToCurrencyString(transactionsYearly.getYearlyExpense());
            String balanceYearlyValue = this.formatHelper.fromDoubleToCurrencyString(transactionsYearly.getYearlyBalanceValue());
            List<TransactionsMonthlyModelView> modelViews = TransactionHelper.toTransactionMonthlyModelViewList(transactionsYearly.getTransactionsMonthlies(), formatHelper);

            this.view.setIncomeYearlyValue(incomeYearlyValue);
            this.view.setExpenseYearlyValue(expenseYearlyValue);
            this.view.setBalanceYearlyValue(balanceYearlyValue);
            this.view.setTransactionsMonthlyValueList(modelViews);

        } catch (Exception ex){
            ex.printStackTrace();
            Log.d(TAG, "onFindTransactionByYear: listview: " + ex.getCause().getMessage());
            this.view.showMessage("Ocorreu um erro ao gerar o relatório: " + ex.getMessage());
        }
    }
}
