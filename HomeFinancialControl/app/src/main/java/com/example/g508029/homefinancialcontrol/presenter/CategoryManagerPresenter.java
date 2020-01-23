package com.example.g508029.homefinancialcontrol.presenter;

import android.util.Log;

import com.example.g508029.homefinancialcontrol.Constants;
import com.example.g508029.homefinancialcontrol.DB.ICategoryRepository;
import com.example.g508029.homefinancialcontrol.helper.CategoryHelper;
import com.example.g508029.homefinancialcontrol.helper.PaymentModeHelper;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;
import com.example.g508029.homefinancialcontrol.model.Category;

import java.util.List;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class CategoryManagerPresenter {
    public interface ICategoryManagerView{
        String getTransactionTypeSelected();
        void setTransactionTypes(List<String> transactionTypes);
        String getDescription();
        void setCategories(List<Category> categories);
        Category getCategorySelected();
        void showMessage(String message);
        void clearValues();

        String getID();
        void setID(String id);
        void setTransactionType(String transactionType);
        void setDescription(String description);
        void setOperationType(String operationType);

        void setFrequencyTypes(List<String> frequencyTypes);
        void setFrequency(String frequency);
        String getFrequency();
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
            this.view.setFrequencyTypes(PaymentModeHelper.getPaymentModeFrequencyList());
            this.view.setOperationType("Adicionar");
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
            String frequency = this.view.getFrequency();

            Category category = new Category(id, description, transactionType, frequency);
            this.repository.addCategory(category);

            this.view.clearValues();
            this.view.showMessage("Categoria adicionada com sucesso!");

        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar adicionar: " + ex.getMessage());
        }
    }

    public void onUpdatedCategory(){
        try {
            this.validateValues();

            String id = this.view.getID();
            String description = this.view.getDescription();
            String transactionType = this.view.getTransactionTypeSelected();
            String frequency = this.view.getFrequency();

            Category category = new Category(id, description, transactionType, frequency);
            this.repository.updateCategory(category);

            this.view.clearValues();
            this.view.showMessage("Categoria atualizada com sucesso!");

        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar atualizar: " + ex.getMessage());
        }
    }


    public void onSelectCategoryToUpdated(){
        Category category = this.view.getCategorySelected();
        if(category == null)
            return;

        this.view.setID(category.getId());
        this.view.setDescription(category.getDescription());
        this.view.setTransactionType(category.getTypeMovement());
        this.view.setFrequency(category.getFrequency());
        this.view.setOperationType("Atualizar");
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
        if (this.view.getTransactionTypeSelected() == null ){
            throw new RuntimeException("Informe um tipo de movimento");
        }
    }
}
