package com.example.g508029.homefinancialcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g508029.homefinancialcontrol.DB.SQLiteTransactionRepository;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.adpter.TransactionYearlyReportAdpter;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.presenter.TransactionYearlyReportPresent;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionsMonthlyModelView;

import java.util.List;
import java.util.Locale;

public class TransactionYearlyReportActivity extends AppCompatActivity implements TransactionYearlyReportPresent.ITransactionYearlyReportView{

    private EditText yearEditText;
    private Button searchButton;
    private ListView transactionMonthylistView;
    private TextView incomeYearlyTextView;
    private TextView expenseYearlyTextView;
    private TextView balanceYearlyTextView;
    private FormatHelper formatHelper;
    private TransactionRepository transactionRepository;
    private TransactionYearlyReportPresent presenter;
    private TransactionYearlyReportAdpter transactionsYearlyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_yearly_report);
        this.getItemsFromActivity();
        this.initialize();
        this.initializeItemsEvent();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void setYearSearch(String year) {
        this.yearEditText.setText(year);
    }

    @Override
    public String getYearSearch() {
        return this.yearEditText.getText().toString();
    }

    @Override
    public void setIncomeYearlyValue(String value) {
        this.incomeYearlyTextView.setText(value);
    }

    @Override
    public void setExpenseYearlyValue(String value) {
        this.expenseYearlyTextView.setText(value);
    }

    @Override
    public void setBalanceYearlyValue(String value) {
        this.balanceYearlyTextView.setText(value);
    }

    @Override
    public void setTransactionsMonthlyValueList(List<TransactionsMonthlyModelView> modelViews) {
        this.transactionsYearlyAdapter = new TransactionYearlyReportAdpter(this, modelViews);
        this.transactionMonthylistView.setAdapter(this.transactionsYearlyAdapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void getItemsFromActivity() {
        yearEditText = findViewById(R.id.transaction_yearly_rpt_year_editText);
        searchButton = findViewById(R.id.transaction_yearly_rpt_search_button);
        transactionMonthylistView = findViewById(R.id.transaction_yearly_rpt_monthly_resume_listView);
        incomeYearlyTextView = findViewById(R.id.transaction_yearly_rpt_income_value_textview);
        expenseYearlyTextView = findViewById(R.id.transaction_yearly_rpt_expense_value_textview);
        balanceYearlyTextView = findViewById(R.id.transaction_yearly_rpt_balance_value_textview);
    }

    private void initialize(){
        Locale mLocale = new Locale("pt", "BR");
        this.formatHelper = new FormatHelper(mLocale);
        this.transactionRepository = new SQLiteTransactionRepository(this);
        this.presenter = new TransactionYearlyReportPresent(this, transactionRepository, formatHelper);
        this.presenter.initialize();
    }

    private void initializeItemsEvent(){
        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFindTransactionByYear();
            }
        });
    }
}
