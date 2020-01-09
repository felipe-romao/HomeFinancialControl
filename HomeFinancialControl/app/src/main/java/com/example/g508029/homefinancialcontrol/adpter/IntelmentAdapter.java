package com.example.g508029.homefinancialcontrol.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.R;
import com.example.g508029.homefinancialcontrol.presenter.modelView.IntelmentModeView;

import java.util.List;

public class IntelmentAdapter extends BaseAdapter {

    private Context context;
    private List<IntelmentModeView> modelViews;

    public IntelmentAdapter(Context context, List<IntelmentModeView> modelViews) {
        this.context = context;
        this.modelViews = modelViews;
    }

    @Override
    public int getCount() {
        return this.modelViews.size();
    }

    @Override
    public IntelmentModeView getItem(int position) {
        return this.modelViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public List<IntelmentModeView> getAll() { return  this.modelViews; }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        IntelmentModeView modelView = this.modelViews.get(position);

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_instalment, null, false);
        }

        TextView dateTextView        = view.findViewById(R.id.instalment_date_textview);
        TextView descriptionTextView = view.findViewById(R.id.instalment_description_textview);
        TextView valueTextView       = view.findViewById(R.id.instalment_value_textview);

        dateTextView.setText(modelView.getDate());
        descriptionTextView.setText(modelView.getDescription());
        valueTextView.setText(modelView.getValue());

        return view;
    }
}
