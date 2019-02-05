package com.example.g508029.homefinancialcontrol.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.R;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionsMonthlyModelView;

import java.util.List;

public class TransactionYearlyReportAdpter extends BaseAdapter {
    private Context context;
    private List<TransactionsMonthlyModelView> modelViews;

    public TransactionYearlyReportAdpter(Context context, List<TransactionsMonthlyModelView> modelViews) {
        this.context = context;
        this.modelViews = modelViews;
    }

    @Override
    public int getCount() {
        return this.modelViews.size();
    }

    @Override
    public Object getItem(int position) {
        return this.modelViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        TransactionsMonthlyModelView modelView = this.modelViews.get(position);
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.transactions_monthly_list, null, false);
        }
        TextView incomeMonthlyTextView = view.findViewById(R.id.transactions_monthly_list_income_value_textview);
        TextView expenseMonthlyTextView = view.findViewById(R.id.transactions_monthly_list_expense_value_textview);
        TextView balanceMonthlyTextView = view.findViewById(R.id.transactions_monthly_list_balance_value_textview);
        TextView dateMonthlyTextView = view.findViewById(R.id.transactions_monthly_list_date_textview);

        incomeMonthlyTextView.setText(modelView.getIncomeValue());
        expenseMonthlyTextView.setText(modelView.getExpenseValue());
        balanceMonthlyTextView.setText(modelView.getBalanceValue());
        dateMonthlyTextView.setText(modelView.getDate());

        return view;
    }
}
