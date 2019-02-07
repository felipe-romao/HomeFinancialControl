package com.example.g508029.homefinancialcontrol.presenter;

import com.example.g508029.homefinancialcontrol.DB.ICategoryRepository;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;
import com.example.g508029.homefinancialcontrol.model.Category;

import java.util.List;
import java.util.UUID;

public class CategoryManagerPresenter {
    public interface ICategoryManagerView{
        String getTransactionTypeSelected();
        void setTransactionTypes(List<String> transactionTypes);
        String getDescription();
        void setCategories(List<Category> categories);
        Category getCategorySelected();
        void showMessage(String message);
        void clearValues();
    }

    private ICategoryManagerView view;
    private ICategoryRepository repository;

    public CategoryManagerPresenter(ICategoryManagerView view, ICategoryRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void initialize(){
        try {
            this.view.setTransactionTypes(TransactionHelper.getTransactionTypes());
            this.onGetAllCategories();
        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar inicializar: " + ex.getMessage());
        }
    }

    public void onAddedCategory(){
        try {
            this.validateValues();

            String id = UUID.randomUUID().toString();
            String description = this.view.getDescription();
            String transactionType = this.view.getTransactionTypeSelected();

            Category category = new Category(id, description, transactionType);
            this.repository.addCategory(category);

            this.view.clearValues();

        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar adicionar: " + ex.getMessage());
        }
    }

    public void onGetAllCategories(){
        try {
            List<Category> categories = this.repository.getAll();
            this.view.setCategories(categories);
        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar buscar as categorias: " + ex.getMessage());
        }
    }

    public void onDeleteCategorySelected(){
        try {
            Category category = this.view.getCategorySelected();
            if(category == null)
                return;

            this.repository.deleteCategoryById(category.getId());
        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar deletar: " + ex.getMessage());
        }
    }

    private void validateValues(){
        if (this.view.getDescription() == null || this.view.getDescription().isEmpty()){
            throw new RuntimeException("Informe uma descrição");
        }
        if (this.view.getCategorySelected() == null ){
            throw new RuntimeException("Informe um tipo de movimento");
        }
    }
}
