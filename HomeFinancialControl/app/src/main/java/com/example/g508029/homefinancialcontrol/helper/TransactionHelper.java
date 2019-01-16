package com.example.g508029.homefinancialcontrol.helper;

import java.util.ArrayList;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.INCOME_DESCRIPTION;

public final class TransactionHelper {
    public static ArrayList<String> getTransactionCategory(String transactionType){
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

    public static ArrayList<String> getTransactionKind(String transactionType){
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
}
