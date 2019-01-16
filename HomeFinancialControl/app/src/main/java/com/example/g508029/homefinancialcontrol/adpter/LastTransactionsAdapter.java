package com.example.g508029.homefinancialcontrol.adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.Constants;
import com.example.g508029.homefinancialcontrol.R;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;

import java.util.List;

import static android.content.ContentValues.TAG;

public class LastTransactionsAdapter extends BaseAdapter {
    private final Context context;
    private FormatHelper formatHelper;
    private final List<Transaction> transactions;

    public LastTransactionsAdapter(Context context, FormatHelper formatHelper, List<Transaction> transactions){
        this.context = context;
        this.formatHelper = formatHelper;
        this.transactions = transactions;
        Log.d(TAG, "LastTransactionsAdapter: count " + transactions.size());
    }

    @Override
    public int getCount() {
        return this.transactions.size();
    }

    @Override
    public Object getItem(int position) {
        return this.transactions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Transaction transaction = this.transactions.get(position);

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_last_transactions, parent, false);
        }

        TextView transactionCategoryTextView = view.findViewById(R.id.last_transaction_category_textview);
        TextView transactionValueTextView = view.findViewById(R.id.last_transaction_value_textview);
        TextView transactionTypeTextView = view.findViewById(R.id.last_transaction_type_textview);

        transactionCategoryTextView.setText(transaction.toString());
        transactionValueTextView.setText(this.formatHelper.fromDoubleToCurrencyString(transaction.getValue()));
        transactionTypeTextView.setText(Constants.getTransactionSymbol(transaction.getType()));

        return view;
    }
}
