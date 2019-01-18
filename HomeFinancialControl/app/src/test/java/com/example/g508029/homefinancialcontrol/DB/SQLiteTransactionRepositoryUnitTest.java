package com.example.g508029.homefinancialcontrol.DB;

import android.content.Context;
import android.support.test.filters.LargeTest;
import android.test.AndroidTestCase;

import com.example.g508029.homefinancialcontrol.model.Transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;

@LargeTest
@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class SQLiteTransactionRepositoryUnitTest extends AndroidTestCase {
    private TransactionRepository repository;
    private Context context;
    private DBHelper dbHelper;

    @Before
    public void setUp(){
        this.context    = RuntimeEnvironment.systemContext;
        this.dbHelper   = new DBHelper(this.context);
        this.repository = new SQLiteTransactionRepository(context, dbHelper);

        this.dbHelper.clearDbAndRecreate();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(this.repository);
        assertNotNull(this.context);
        assertNotNull(this.dbHelper);
    }

    @Test
    public void addTransaction_getAllTransactionAdded_transactionAddedSuccess(){
        Transaction transactionAdded = new Transaction(EXPENSE_DESCRIPTION);
        this.repository.addTransaction(transactionAdded);

        List<Transaction> transactions = this.repository.getAllTransactionsByMonth(1);

        assertEquals(1, transactions.size());
        assertEquals(transactionAdded.getId()           , transactions.get(0).getId());
        assertEquals(transactionAdded.getCategory()     , transactions.get(0).getCategory());
        assertEquals(transactionAdded.getDescription()  , transactions.get(0).getDescription());
        assertEquals(transactionAdded.getDate()         , transactions.get(0).getDate());
        assertEquals(transactionAdded.getPaymentMode()  , transactions.get(0).getPaymentMode());
        assertEquals(transactionAdded.getType()         , transactions.get(0).getType());
        assertEquals(transactionAdded.getValue()        , transactions.get(0).getValue());
    }
}
