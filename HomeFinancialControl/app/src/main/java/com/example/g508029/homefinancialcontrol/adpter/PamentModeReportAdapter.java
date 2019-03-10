package com.example.g508029.homefinancialcontrol.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.R;
import com.example.g508029.homefinancialcontrol.presenter.modelView.CategoryGroupedModelView;
import com.example.g508029.homefinancialcontrol.presenter.modelView.InfoTransactionGroupedModelView;

import java.util.List;

public class PamentModeReportAdapter extends BaseAdapter{
    private Context context;
    private List<InfoTransactionGroupedModelView> infoTransactionGroupedModelViews;

    public PamentModeReportAdapter(Context context, List<InfoTransactionGroupedModelView> infoTransactionGroupedModelViews){
        this.context = context;
        this.infoTransactionGroupedModelViews = infoTransactionGroupedModelViews;
    }


    @Override
    public int getCount() {
        return this.infoTransactionGroupedModelViews.size();
    }

    @Override
    public Object getItem(int position) {
        return this.infoTransactionGroupedModelViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        InfoTransactionGroupedModelView modelView = this.infoTransactionGroupedModelViews.get(position);
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.info_transaction_grouped_list, null, false);
        }
        populateFields(view, modelView);
        return view;
    }

    public InfoTransactionGroupedModelView createHeaderData(){
        InfoTransactionGroupedModelView modelView = new InfoTransactionGroupedModelView();
        modelView.setTotal("Total");
        modelView.setQuantity("Qtde");
        modelView.setDescription("Descrição");
        return modelView;
    }

    public void populateFields(View view, InfoTransactionGroupedModelView modelView) {
        TextView countTextView = view.findViewById(R.id.info_total_textView);
        TextView descriptionTextView = view.findViewById(R.id.info_description_textView);
        TextView valueTextView = view.findViewById(R.id.info_sum_textView);

        countTextView.setText(modelView.getQuantity());
        descriptionTextView.setText(modelView.getDescription());
        valueTextView.setText(modelView.getTotal());
    }
}
