package com.example.g508029.homefinancialcontrol;

import com.example.g508029.homefinancialcontrol.model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.INCOME_DESCRIPTION;

public abstract class DataTestHelper {
    public static Transaction createTransactionSimple(){
        Transaction transaction = new Transaction(EXPENSE_DESCRIPTION);
        transaction.setCategory("CATEGORY_TEST");
        transaction.setDate(new Date(2019,1,1));
        transaction.setDescription("DESCRIPTION_TEST");
        transaction.setPaymentMode("PAYMENT_MODE");
        transaction.setValue(10.50);
        return transaction;
    }

    public static List<Transaction> createTransactionList(){
        Transaction transaction1 = new Transaction(EXPENSE_DESCRIPTION);
        transaction1.setCategory("CATEGORY_TEST_1");
        transaction1.setDate(new Date(119,0,2));
        transaction1.setDescription("DESCRIPTION_TEST_1");
        transaction1.setPaymentMode("PAYMENT_MODE_1");
        transaction1.setValue(10.50);

        Transaction transaction2 = new Transaction(INCOME_DESCRIPTION);
        transaction2.setCategory("CATEGORY_TEST_2");
        transaction2.setDate(new Date(119,1,28));
        transaction2.setDescription("DESCRIPTION_TEST_2");
        transaction2.setPaymentMode("PAYMENT_MODE_2");
        transaction2.setValue(18.0);

        Transaction transaction3 = new Transaction(EXPENSE_DESCRIPTION);
        transaction3.setCategory("CATEGORY_TEST_3");
        transaction3.setDate(new Date(118,6,18));
        transaction3.setDescription("DESCRIPTION_TEST_3");
        transaction3.setPaymentMode("PAYMENT_MODE_3");
        transaction3.setValue(8.74);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);

        return transactions;
    }

    public static List<Transaction> createTransactionsYearly(){
        Transaction transaction1 = new Transaction(EXPENSE_DESCRIPTION);
        transaction1.setCategory("CATEGORY_EXPENSE_1");
        transaction1.setDate(new Date(118,6,18));
        transaction1.setDescription("DESCRIPTION_EXPENSE_TEST_1");
        transaction1.setPaymentMode("PAYMENT_MODE_1");
        transaction1.setValue(8.74);

        Transaction transaction2 = new Transaction(EXPENSE_DESCRIPTION);
        transaction2.setCategory("CATEGORY_EXPENSE_1");
        transaction2.setDate(new Date(118,6,20));
        transaction2.setDescription("DESCRIPTION_EXPENSE_TEST_2");
        transaction2.setPaymentMode("PAYMENT_MODE_1");
        transaction2.setValue(20.50);

        Transaction transaction3 = new Transaction(INCOME_DESCRIPTION);
        transaction3.setCategory("CATEGORY_INCOME_1");
        transaction3.setDate(new Date(118,6,21));
        transaction3.setDescription("DESCRIPTION_INCOME_TEST_1");
        transaction3.setPaymentMode("PAYMENT_MODE_1");
        transaction3.setValue(50.9);

        Transaction transaction4 = new Transaction(EXPENSE_DESCRIPTION);
        transaction4.setCategory("CATEGORY_EXPENSE_3");
        transaction4.setDate(new Date(118,7,10));
        transaction4.setDescription("DESCRIPTION_EXPENSE_TEST_3");
        transaction4.setPaymentMode("PAYMENT_MODE_1");
        transaction4.setValue(10.9);

        Transaction transaction5 = new Transaction(INCOME_DESCRIPTION);
        transaction5.setCategory("CATEGORY_INCOME_2");
        transaction5.setDate(new Date(118,7,23));
        transaction5.setDescription("DESCRIPTION_INCOME_TEST_2");
        transaction5.setPaymentMode("PAYMENT_MODE_1");
        transaction5.setValue(100.0);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);

        return transactions;
    }
}
