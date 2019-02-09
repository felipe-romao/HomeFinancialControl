package com.example.g508029.homefinancialcontrol.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.R;
import com.example.g508029.homefinancialcontrol.presenter.modelView.CategoryGroupedModelView;

import java.util.List;

public class CategoryReportAdapter extends BaseAdapter{
    private Context context;
    private List<CategoryGroupedModelView> categoryGroupedModelViews;

    public CategoryReportAdapter(Context context, List<CategoryGroupedModelView> categoryGroupedModelViews) {
        this.context = context;
        this.categoryGroupedModelViews = categoryGroupedModelViews;
    }


    @Override
    public int getCount() {
        return this.categoryGroupedModelViews.size();
    }

    @Override
    public CategoryGroupedModelView getItem(int position) {
        return this.categoryGroupedModelViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        CategoryGroupedModelView modelView = this.categoryGroupedModelViews.get(position);
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.category_totalizer_list, null, false);
        }

        TextView countTextView = view.findViewById(R.id.category_totalize_count_textView);
        TextView descriptionTextView = view.findViewById(R.id.category_totalize_description_textView);
        TextView valueTextView = view.findViewById(R.id.category_totalize_value_textView);

        countTextView.setText(modelView.getCount());
        descriptionTextView.setText(modelView.getDescription());
        valueTextView.setText(modelView.getValue());

        return view;
    }
}
