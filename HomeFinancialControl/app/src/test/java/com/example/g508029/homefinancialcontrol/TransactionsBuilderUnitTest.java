package com.example.g508029.homefinancialcontrol;

import android.content.Context;
import android.support.test.filters.LargeTest;

import com.example.g508029.homefinancialcontrol.DB.DBHelper;
import com.example.g508029.homefinancialcontrol.DB.SQLiteTransactionRepository;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.model.TransactionsYearly;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertNotNull;


@LargeTest
@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class TransactionsBuilderUnitTest {
    private TransactionRepository repository;
    private Context context;
    private DBHelper dbHelper;

    @Before
    public void setUp(){
        this.context    = RuntimeEnvironment.systemContext;
        this.dbHelper   = new DBHelper(this.context);
        this.repository = new SQLiteTransactionRepository(context, dbHelper);

        this.dbHelper.clearDbAndRecreate();
        this.populateDatabase();

    }

    @Test
    public void testPreConditions() {
        assertNotNull(this.repository);
        assertNotNull(this.context);
        assertNotNull(this.dbHelper);
    }

    @Test
    public void buildTransactionsMonthly_CreateBuilder_TransactionsMonthlyBuilded(){
        TransactionsBuilder builder = new TransactionsBuilder(repository);

        TransactionsMonthly transactionsMonthly = builder.buildTransactionsMonthly(7, 2018);
        HashMap<String, Double> categoriesTotalizer = transactionsMonthly.getCategoriesTotalizer();

        Assert.assertEquals(7         , transactionsMonthly.getMonth());
        Assert.assertEquals(2018      , transactionsMonthly.getYear());
        Assert.assertEquals("29.24"   , String.format("%.2f",transactionsMonthly.getMonthlyExpense()));
        Assert.assertEquals("50.90"   , String.format("%.2f",transactionsMonthly.getMonthlyIncome()));
        Assert.assertEquals("21.66"   , String.format("%.2f",transactionsMonthly.getMonthlyBalanceValue()));
        Assert.assertEquals(1         , categoriesTotalizer.size());

        for (String category: categoriesTotalizer.keySet()){
            Assert.assertEquals("CATEGORY_EXPENSE_1"    , category);
            Assert.assertEquals("29.24"                 , String.format("%.2f",categoriesTotalizer.get(category)));
        }
    }

    @Test
    public void buildTransactionsYearly_CreateBuilder_TransactionsYearlyBuilded(){
        TransactionsBuilder builder = new TransactionsBuilder(repository);

        TransactionsYearly transactionsYearly = builder.buildTransactionsYearly(7, 8, 2018);

        Assert.assertEquals(2018      , transactionsYearly.getYear());
        Assert.assertEquals("40.14"   , String.format("%.2f",transactionsYearly.getYearlyExpense()));
        Assert.assertEquals("150.90"  , String.format("%.2f",transactionsYearly.getYearlyIncome()));
        Assert.assertEquals("110.76"  , String.format("%.2f",transactionsYearly.getYearlyBalanceValue()));
        Assert.assertEquals(1         , transactionsYearly.getTransactionsMonthlies().get(0).getCategoriesTotalizer().size());
        Assert.assertEquals(1         , transactionsYearly.getTransactionsMonthlies().get(1).getCategoriesTotalizer().size());
    }

    private void populateDatabase(){
        List<Transaction> transactions = DataTestHelper.createTransactionsYearly();
        for (Transaction transaction: transactions){
            this.repository.addTransaction(transaction);
        }
    }
}
