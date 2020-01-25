package com.example.g508029.homefinancialcontrol.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.R;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.util.List;

public class TransactionsReportAdapter extends BaseAdapter{
    private Context context;
    private List<TransactionModelView> modelViews;

    public TransactionsReportAdapter(Context context, List<TransactionModelView> modelViews) {
        this.context = context;
        this.modelViews = modelViews;
    }

    @Override
    public int getCount() {
        return this.modelViews.size();
    }

    @Override
    public TransactionModelView getItem(int position) {
        return this.modelViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        TransactionModelView modelView = this.modelViews.get(position);
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.transactions_list, null, false);
        }

        TextView categoryTextView = view.findViewById(R.id.transctions_list_category_textView);
        TextView dateTextView = view.findViewById(R.id.transctions_list_date_textView);
        TextView descriptionTextView = view.findViewById(R.id.transctions_list_description_textView);
        TextView paymentModeTextView = view.findViewById(R.id.transctions_list_payment_mode_textView);
        TextView typeSymbolTextView = view.findViewById(R.id.transctions_list_type_symbol_textView);
        TextView valueTextView = view.findViewById(R.id.transctions_list_value_textView);
        TextView frequencyTextView = view.findViewById(R.id.transctions_list_frequency_textView);

        categoryTextView.setText(modelView.getCategory());
        dateTextView.setText(modelView.getDate());
        descriptionTextView.setText(modelView.getDescription());
        paymentModeTextView.setText(modelView.getPaymentMode());
        typeSymbolTextView.setText(modelView.getTypeSymbol());
        valueTextView.setText(modelView.getValue());
        frequencyTextView.setText(modelView.getFrequency());

        return view;
    }
}
