package com.example.g508029.homefinancialcontrol.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.R;
import com.example.g508029.homefinancialcontrol.model.Category;
import com.example.g508029.homefinancialcontrol.presenter.modelView.CategoryGroupedModelView;

import java.util.List;

public class CategoryManagerAdapter extends BaseAdapter{
    private Context context;
    private List<Category> categories;

    public CategoryManagerAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }


    @Override
    public int getCount() {
        return this.categories.size();
    }

    @Override
    public Category getItem(int position) {
        return this.categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Category category = this.categories.get(position);
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.category_list, null, false);
        }
        TextView movementTextView = view.findViewById(R.id.category_movement_type_textView);
        TextView frequencyTextView = view.findViewById(R.id.category_frequency_textView);
        TextView categoryTextView = view.findViewById(R.id.category_textView);

        movementTextView.setText(category.getTypeMovement());
        frequencyTextView.setText(category.getFrequency());
        categoryTextView.setText(category.getDescription());

        return view;
    }
}
