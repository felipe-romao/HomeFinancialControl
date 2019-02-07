package com.example.g508029.homefinancialcontrol.helper;

import com.example.g508029.homefinancialcontrol.Constants;
import com.example.g508029.homefinancialcontrol.DB.ICategoryRepository;
import com.example.g508029.homefinancialcontrol.DB.IPaymentModeRepository;
import com.example.g508029.homefinancialcontrol.model.Category;
import com.example.g508029.homefinancialcontrol.model.PaymentMode;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionsMonthlyModelView;

import java.util.ArrayList;
import java.util.List;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.INCOME_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.MMMMyyyy_DATE_FORMAT_PATTERN;

public final class TransactionHelper {
    public static List<String> getTransactionTypes(){
        List<String> transactionTypes = new ArrayList<>();
        transactionTypes.add(INCOME_DESCRIPTION);
        transactionTypes.add(EXPENSE_DESCRIPTION);
        return transactionTypes;
    }

    public static List<String> getTransactionCategory(String transactionType, ICategoryRepository repository){
        ArrayList<String> categories = new ArrayList<>();
        for (Category category: repository.getAll()){
            if(category.getTypeMovement().equals(transactionType)){
                categories.add(category.getDescription());
            }
        }
        return categories;
    }

    public static List<String> getTransactionKind(IPaymentModeRepository repository){
        ArrayList<String> kinds = new ArrayList<>();
        for (PaymentMode paymentMode: repository.getAll()){
            kinds.add(paymentMode.getMode());
        }
        return kinds;
    }

    public static List<TransactionModelView> toTransactionsModelViewList(List<Transaction> transactions, FormatHelper formatHelper){
        List<TransactionModelView> transactionModelViews = new ArrayList<>();
        for (Transaction transaction: transactions){
            transactionModelViews.add(TransactionHelper.toTransactionModelView(transaction, formatHelper));
        }
        return transactionModelViews;
    }

    public static TransactionModelView toTransactionModelView(Transaction transaction, FormatHelper formatHelper){
        TransactionModelView modelView = new TransactionModelView();
        modelView.setCategory(transaction.getCategory());
        modelView.setType(transaction.getType());
        modelView.setValue(formatHelper.fromDoubleToCurrencyString(transaction.getValue()));
        modelView.setTypeSymbol(Constants.getTransactionSymbol(transaction.getType()));
        return modelView;
    }

    public static List<TransactionsMonthlyModelView> toTransactionMonthlyModelViewList(List<TransactionsMonthly> transactionsMonthlies, FormatHelper formatHelper){
        List<TransactionsMonthlyModelView> monthlyModelViews = new ArrayList<>();
        for(TransactionsMonthly transactionsMonthly: transactionsMonthlies){
            monthlyModelViews.add(TransactionHelper.toTransactionMonthlyModelView(transactionsMonthly, formatHelper));
        }
        return monthlyModelViews;
    }

    public static TransactionsMonthlyModelView toTransactionMonthlyModelView(TransactionsMonthly transactionsMonthly, FormatHelper formatHelper){
        TransactionsMonthlyModelView modelView = new TransactionsMonthlyModelView();
        modelView.setExpenseValue(formatHelper.fromDoubleToCurrencyString(transactionsMonthly.getMonthlyExpense()));
        modelView.setIncomeValue(formatHelper.fromDoubleToCurrencyString(transactionsMonthly.getMonthlyIncome()));
        modelView.setBalanceValue(formatHelper.fromDoubleToCurrencyString(transactionsMonthly.getMonthlyBalanceValue()));
        modelView.setDate(formatHelper.fromDateToString(MMMMyyyy_DATE_FORMAT_PATTERN, transactionsMonthly.getPeriod()));
        return modelView;
    }
}
