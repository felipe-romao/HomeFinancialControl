package com.example.g508029.homefinancialcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.g508029.homefinancialcontrol.DB.SQLiteTransactionRepository;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.helper.ChartHelper;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.presenter.MainPresenter;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;
import com.github.mikephil.charting.charts.PieChart;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;
import static com.example.g508029.homefinancialcontrol.Constants.INCOME_DESCRIPTION;

public class MainActivity extends AppCompatActivity implements MainPresenter.IMainView{

    private TextView monthlyIncomeValueTextView;
    private TextView monthlyExpenseValueTextView;
    private TextView monthlyBalanceValueTextView;
    private TextView monthlyBalanceDateTextView;
    private TextView summaryCategoryTextView;
    private ListView lastMovementsListView;
    private PieChart categoryChart;
    private Button expenseButton;
    private Button incomeButton;
    private Button transactionNewButton;
    private LinearLayout monthly_balance_layout;
    private LinearLayout lastTranscationLayout;

    private FormatHelper formatHelper;
    private TransactionRepository transactionRepository;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //registerForContextMenu(findViewById(R.id.main_last_movements_listview));
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.startUpConfig();
    }

    @Override
    public void setMonthlyIncomeValue(String value) {
        this.monthlyIncomeValueTextView.setText(value);
    }

    @Override
    public void setMonthlyExpenseValue(String value) {
        this.monthlyExpenseValueTextView.setText(value);
    }

    @Override
    public void setMonthlyBalanceValue(String value) {
        this.monthlyBalanceValueTextView.setText(value);
    }

    @Override
    public void setMonthlyBalanceDate(String date) {
        this.monthlyBalanceDateTextView.setText(date);
    }

    @Override
    public void setSummaryCategory(String category) {
        this.summaryCategoryTextView.setText(category);
    }

    @Override
    public void setLastTransactions(List<TransactionModelView> transactions) {
        this.lastTranscationLayout.removeAllViewsInLayout();
        LayoutInflater inflater = LayoutInflater.from(this);

        for(TransactionModelView modelView: transactions){
            View view = inflater.inflate(R.layout.list_last_transactions, null, false);

            TextView transactionCategoryTextView = view.findViewById(R.id.last_transaction_category_textview);
            TextView transactionValueTextView    = view.findViewById(R.id.last_transaction_value_textview);
            TextView transactionTypeTextView     = view.findViewById(R.id.last_transaction_type_textview);

            transactionCategoryTextView.setText(modelView.getCategory());
            transactionValueTextView.setText(modelView.getValue());
            transactionTypeTextView.setText(modelView.getTypeSymbol());

            lastTranscationLayout.addView(view);
        }
    }

    @Override
    public void setCategoryChart(HashMap<String, Double> values) {
        ChartHelper.setValues(this.categoryChart, values);
    }

    private void getItemsFromActivity(){
        this.monthlyIncomeValueTextView     = findViewById(R.id.main_monthly_income_value_textview);
        this.monthlyExpenseValueTextView    = findViewById(R.id.main_monthly_expense_value_textview);
        this.monthlyBalanceValueTextView    = findViewById(R.id.main_monthly_balance_value_textview);
        this.monthlyBalanceDateTextView     = findViewById(R.id.main_monthly_balance_date_textview);
        this.summaryCategoryTextView        = findViewById(R.id.main_summary_category_textview);
        this.lastMovementsListView          = findViewById(R.id.main_last_movements_listview);
        this.categoryChart                  = findViewById(R.id.main_category_chart);
        this.expenseButton                  = findViewById(R.id.main_expense_button);
        this.incomeButton                   = findViewById(R.id.main_income_button);
        this.transactionNewButton           = findViewById(R.id.main_new_movement_button);
        this.monthly_balance_layout         = findViewById(R.id.main_monthly_balance_linear_layout);
        this.lastTranscationLayout          = findViewById(R.id.main_last_movements_views);
    }

    private void initialize(){
        Locale mLocale = new Locale("pt", "BR");
        this.formatHelper = new FormatHelper(mLocale);
        this.transactionRepository  = new SQLiteTransactionRepository(this);
        this.presenter = new MainPresenter(this, this.transactionRepository, this.formatHelper);
        this.presenter.initialize();
    }

    private void initializeItemsEvent(){
        monthly_balance_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransactionYearlyReportActivity.class);
                startActivity(intent);
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

    private void startUpConfig(){
        this.getItemsFromActivity();
        this.initialize();
        this.initializeItemsEvent();
    }
}
