package com.example.g508029.homefinancialcontrol.helper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.Constants;
import com.example.g508029.homefinancialcontrol.MainActivity;
import com.example.g508029.homefinancialcontrol.R;
import com.example.g508029.homefinancialcontrol.adpter.LastTransactionsAdapter;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.github.mikephil.charting.charts.PieChart;

import static android.content.ContentValues.TAG;
import static com.example.g508029.homefinancialcontrol.Constants.LAST_TRANSACTIONS_COUNTER;
import static com.example.g508029.homefinancialcontrol.Constants.MMMMyyyy_DATE_FORMAT_PATTERN;


public class MainActivityHelper {
    private MainActivity activity;
    private FormatHelper formatHelper;
    private TextView monthlyIncomeValueTextView;
    private TextView monthlyExpenseValueTextView;
    private TextView monthlyBalanceValueTextView;
    private TextView monthlyBalanceDateTextView;
    private TextView summaryCategoryTextView;
    private ListView lastMovementsListView;
    private PieChart categoryChart;
    private TransactionsMonthly transactionsMonthly;
    private LinearLayout lastTranscationLayout;

    public MainActivityHelper(MainActivity activity, FormatHelper formatHelper){
        this.activity = activity;
        this.formatHelper = formatHelper;
        this.getItemsFromActivity();
    }

    private void getItemsFromActivity(){
        this.monthlyIncomeValueTextView     = activity.findViewById(R.id.main_monthly_income_value_textview);
        this.monthlyExpenseValueTextView    = activity.findViewById(R.id.main_monthly_expense_value_textview);
        this.monthlyBalanceValueTextView    = activity.findViewById(R.id.main_monthly_balance_value_textview);
        this.monthlyBalanceDateTextView     = activity.findViewById(R.id.main_monthly_balance_date_textview);
        this.summaryCategoryTextView        = activity.findViewById(R.id.main_summary_category_textview);
        this.lastMovementsListView          = activity.findViewById(R.id.main_last_movements_listview);
        this.categoryChart                  = activity.findViewById(R.id.main_category_chart);
        this.lastTranscationLayout          = this.activity.findViewById(R.id.main_last_movements_views);
    }

    public void putValuesInActivity(TransactionsMonthly transactionsMonthly){
        this.transactionsMonthly = transactionsMonthly;

        Log.d(TAG, "putValuesInActivity: MonthlyIncome -> " + this.transactionsMonthly.getMonthlyIncome());
        Log.d(TAG, "putValuesInActivity: MonthlyExpense -> " + this.transactionsMonthly.getMonthlyExpense());
        Log.d(TAG, "putValuesInActivity: MonthlyBalanceValue -> " + this.transactionsMonthly.getMonthlyBalanceValue());

        String periodText = this.formatHelper.fromDateToString(MMMMyyyy_DATE_FORMAT_PATTERN, this.transactionsMonthly.getPeriod());
        this.monthlyIncomeValueTextView.setText(this.formatHelper.fromDoubleToCurrencyString(this.transactionsMonthly.getMonthlyIncome()));
        this.monthlyExpenseValueTextView.setText(this.formatHelper.fromDoubleToCurrencyString(this.transactionsMonthly.getMonthlyExpense()));
        this.monthlyBalanceValueTextView.setText(this.formatHelper.fromDoubleToCurrencyString(this.transactionsMonthly.getMonthlyBalanceValue()));
        this.monthlyBalanceDateTextView.setText(periodText);
        this.summaryCategoryTextView.setText(periodText);
        ChartHelper.setValues(this.categoryChart, transactionsMonthly.getCategoriesTotalizer());
        this.loadLastTransactions();
    }

    private void loadLastTransactions(){
        this.lastTranscationLayout.removeAllViewsInLayout();
        LayoutInflater inflater = LayoutInflater.from(this.activity);

/*
        for(Transaction transaction:this.transactionsMonthly.getlastTransactions(10)){
            View view = inflater.inflate(R.layout.list_last_transactions, null, false);

            TextView transactionCategoryTextView = view.findViewById(R.id.last_transaction_category_textview);
            TextView transactionValueTextView    = view.findViewById(R.id.last_transaction_value_textview);
            TextView transactionTypeTextView     = view.findViewById(R.id.last_transaction_type_textview);

            transactionCategoryTextView.setText(transaction.toString());
            transactionValueTextView.setText(this.formatHelper.fromDoubleToCurrencyString(transaction.getValue()));
            transactionTypeTextView.setText(Constants.getTransactionSymbol(transaction.getType()));

            lastTranscationLayout.addView(view);
        }
*/
    }
}
