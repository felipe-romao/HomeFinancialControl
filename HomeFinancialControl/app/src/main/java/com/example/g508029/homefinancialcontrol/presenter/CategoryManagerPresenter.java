package com.example.g508029.homefinancialcontrol.presenter;

import com.example.g508029.homefinancialcontrol.model.Category;

import java.util.List;

public class CategoryManagerPresenter {
    public interface ICategoryManagerView{
        String getTransactionType();
        String getDescription();
        void setCategories(List<Category> categories);
        Category getCategorySelected();
        void showMessage(String message);
        void clearValues();
    }
}
