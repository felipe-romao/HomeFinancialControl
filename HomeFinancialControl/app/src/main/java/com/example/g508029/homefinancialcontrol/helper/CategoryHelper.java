package com.example.g508029.homefinancialcontrol.helper;

import com.example.g508029.homefinancialcontrol.model.Category;
import com.example.g508029.homefinancialcontrol.model.CategoryGrouped;
import com.example.g508029.homefinancialcontrol.presenter.modelView.CategoryGroupedModelView;

import java.util.ArrayList;
import java.util.List;

public class CategoryHelper {
    public static CategoryGroupedModelView toCategoryModelView(CategoryGrouped categoryGrouped, FormatHelper formatHelper){
        String count = String.valueOf(categoryGrouped.getCount());
        String value = formatHelper.fromDoubleToCurrencyString(categoryGrouped.getValue());
        return new CategoryGroupedModelView(count, categoryGrouped.getDescription(), value);
    }

    public static List<CategoryGroupedModelView> toCategoryGroupedModelViewList(List<CategoryGrouped> categoryGroupeds, FormatHelper formatHelper){
        List<CategoryGroupedModelView> categoryGroupedModelViewList = new ArrayList<>();
        for (CategoryGrouped categoryGrouped : categoryGroupeds){
            categoryGroupedModelViewList.add(CategoryHelper.toCategoryModelView(categoryGrouped, formatHelper));
        }
        return categoryGroupedModelViewList;
    }
}
