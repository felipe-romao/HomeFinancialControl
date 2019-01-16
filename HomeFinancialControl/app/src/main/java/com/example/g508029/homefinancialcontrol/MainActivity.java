package com.example.g508029.homefinancialcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g508029.homefinancialcontrol.DB.SQLiteTransactionRepository;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.model.Category;
import com.example.g508029.homefinancialcontrol.model.PaymentMode;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.helper.MainActivityHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.INCOME_DESCRIPTION;

public class MainActivity extends AppCompatActivity {
    private Button expenseButton;
    private Button incomeButton;
    private Button transactionNewButton;
    private LinearLayout monthly_balance_layout;
    private MainActivityHelper mainActivityHelper;
    private FormatHelper formatHelper;
    private TransactionRepository transactionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale mLocale = new Locale("pt", "BR");
        this.formatHelper = new FormatHelper(mLocale);
        this.mainActivityHelper     = new MainActivityHelper(this, this.formatHelper);
        this.expenseButton          = findViewById(R.id.main_expense_button);
        this.incomeButton           = findViewById(R.id.main_income_button);
        this.transactionNewButton   = findViewById(R.id.main_new_movement_button);
        this.monthly_balance_layout = findViewById(R.id.main_monthly_balance_linear_layout);
        this.transactionRepository  = new SQLiteTransactionRepository(this);

        //this.mainActivityHelper.putValuesInActivity(this.createTransactionSummary());
        this.initializeItemsEvent();


        //registerForContextMenu(findViewById(R.id.main_last_movements_listview));
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mainActivityHelper.putValuesInActivity(this.createTransactionSummary());
    }

    private void initializeItemsEvent(){
        monthly_balance_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Test", Toast.LENGTH_LONG).show();
            }
        });

        transactionNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransactionManagerActivity.class);
                startActivity(intent);
            }
        });

        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransactionManagerActivity.class);
                intent.putExtra("TRANSCATION_TYPE_SELECTED", EXPENSE_DESCRIPTION);
                startActivity(intent);
            }
        });

        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransactionManagerActivity.class);
                intent.putExtra("TRANSCATION_TYPE_SELECTED", INCOME_DESCRIPTION);
                startActivity(intent);
            }
        });
    }

    private TransactionsMonthly createTransactionSummary(){
        //String id, String type, String description, double value, Date date, Category category, PaymentMode paymentMode)
//        Calendar c = Calendar.getInstance();
//        Category category = new Category("ID", "ALIMENTACAO", "DESPESA");
//        PaymentMode paymentMode = new PaymentMode("ID", "DINHEIRO");
//        Transaction transaction = new Transaction("TRANS_ID", "DESPESA", "", 10.0, c.getTime(), "ALIMENTACAO", "DINHEIRO");
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(transaction);

        TransactionsMonthly transactionsMonthly = new TransactionsMonthly(11, 2018, this.transactionRepository.getAllTransactionsByMonth(11));
        return transactionsMonthly;
    }
}
