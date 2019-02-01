package com.example.g508029.homefinancialcontrol.helper;

import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.util.ArrayList;
import java.util.List;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.INCOME_DESCRIPTION;

public final class TransactionHelper {
    public static List<String> getTransactionCategory(String transactionType){
        ArrayList<String> categories = new ArrayList<>();

        switch (transactionType) {
            case INCOME_DESCRIPTION :
                categories.add("Emprestimo");
                categories.add("Salário");
                categories.add("Vendas");
                break;
            case EXPENSE_DESCRIPTION:
                categories.add("Alimentação");
                categories.add("Educação");
                categories.add("Combustível");
                categories.add("Diversão");
                break;
        }
        return categories;
    }

    public static List<String> getTransactionKind(String transactionType){
        ArrayList<String> kinds = new ArrayList<>();

        switch (transactionType) {
            case "RECEITA" :
                kinds.add("Cartão");
                kinds.add("Dinheiro");
                kinds.add("Poupança");
                break;
            case "DESPESA":
                kinds.add("Cartão");
                kinds.add("Dinheiro");
                kinds.add("Poupança");
                break;
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
        return modelView;
    }
}
