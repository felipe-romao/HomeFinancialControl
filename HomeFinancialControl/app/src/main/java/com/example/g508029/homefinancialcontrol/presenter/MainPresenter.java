package com.example.g508029.homefinancialcontrol.presenter;

import android.util.Log;

import com.example.g508029.homefinancialcontrol.CategoryGroupedListGenerator;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.TransactionsBuilder;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;
import com.example.g508029.homefinancialcontrol.model.CategoryGrouped;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;
import com.example.g508029.homefinancialcontrol.service.IExternalService;
import com.example.g508029.homefinancialcontrol.system.IFileSystem;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
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
        void setCategoryChart(List<CategoryGrouped> values);
    }

    private IMainView view;
    private TransactionRepository repository;
    private FormatHelper formatHelper;
    private IExternalService externalService;

    public MainPresenter(IMainView view, TransactionRepository repository, FormatHelper formatHelper, IExternalService externalService) {
        this.view = view;
        this.repository = repository;
        this.formatHelper = formatHelper;
        this.externalService = externalService;
    }

    public void initialize(){
        Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));
        int monthCurrent  = calendar.get(Calendar.MONTH) + 1 ;
        int yearCurrent   = calendar.get(Calendar.YEAR);
        Date dateCurrent  = calendar.getTime();

        TransactionsBuilder transactionsBuilder = new TransactionsBuilder(repository);
        TransactionsMonthly transactionsMonthlyCurrent = transactionsBuilder.buildTransactionsMonthly(monthCurrent, yearCurrent);
        List<Transaction> expenseTransactions = this.repository.getAllTransactionsByMonthAndType(monthCurrent, yearCurrent, EXPENSE_DESCRIPTION);

        String periodText = this.formatHelper.fromDateToString(MMMMyyyy_DATE_FORMAT_PATTERN, dateCurrent);
        String monthlyIncomeValue = this.formatHelper.fromDoubleToCurrencyString(transactionsMonthlyCurrent.getMonthlyIncome());
        String monthlyExpenseValue = this.formatHelper.fromDoubleToCurrencyString(transactionsMonthlyCurrent.getMonthlyExpense());
        String monthlyBalanceValue = this.formatHelper.fromDoubleToCurrencyString(transactionsMonthlyCurrent.getMonthlyBalanceValue());
        CategoryGroupedListGenerator categoryGroupedListGenerator = new CategoryGroupedListGenerator(expenseTransactions);
        List<Transaction> lastTransactions = this.repository.getLastTransactions(LAST_TRANSACTIONS_COUNTER);

        List<CategoryGrouped> generate = categoryGroupedListGenerator.generate();
        this.view.setMonthlyBalanceDate(periodText);
        this.view.setSummaryCategory(periodText);
        this.view.setMonthlyIncomeValue(monthlyIncomeValue);
        this.view.setMonthlyExpenseValue(monthlyExpenseValue);
        this.view.setMonthlyBalanceValue(monthlyBalanceValue);
        this.view.setCategoryChart(generate);
        this.view.setLastTransactions(TransactionHelper.toTransactionsModelViewList(lastTransactions, this.formatHelper));
    }

    public void onDownloaderTransactionExternalFile(String path){
        TransactionsBuilder transactionsBuilder = new TransactionsBuilder(repository);
        TransactionsMonthly transactionsMonthly = transactionsBuilder.buildTransactionsMonthly(02, 2019);
        //this.externalService.exportTransactionsMonthly(path + "/transacoes.xls", transactionsMonthly);
    }
}
