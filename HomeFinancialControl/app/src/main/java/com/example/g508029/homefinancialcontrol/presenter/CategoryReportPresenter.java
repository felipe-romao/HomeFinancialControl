package com.example.g508029.homefinancialcontrol.presenter;

import com.example.g508029.homefinancialcontrol.CategoryGroupedListGenerator;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.TransactionsBuilder;
import com.example.g508029.homefinancialcontrol.helper.CategoryHelper;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.model.TransactionsMonthly;
import com.example.g508029.homefinancialcontrol.presenter.modelView.CategoryGroupedModelView;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static com.example.g508029.homefinancialcontrol.Constants.EXPENSE_DESCRIPTION;

public class CategoryReportPresenter {
    public interface ICategoryReportView{
        void setTransactionTypes(List<String> transactionTypes);
        void setTransactionType(String transactionType);
        String getTransactionTypeSelected();
        void setMonths(List<String> months);
        void setMonth(String month);
        String getMonthSelected();
        void setYear(String year);
        String getYear();
        void setCategoryModelViews(List<CategoryGroupedModelView> categoryGroupedModelViews);
        void showMessage(String message);
        CategoryGroupedModelView getSelectedCategoryGroupedModelView();
        void setTransactionsFromSelectedCategory(List<TransactionModelView> modelViews);
    }

    private ICategoryReportView view;
    private TransactionRepository repository;
    private FormatHelper formatHelper;

    public CategoryReportPresenter(ICategoryReportView view, TransactionRepository repository, FormatHelper formatHelper) {
        this.view = view;
        this.repository = repository;
        this.formatHelper = formatHelper;
    }

    public void initialize(){
        try {
            List<String> monthNames = this.formatHelper.getMonthNames();
            String monthNameCurrent = this.formatHelper.getMonthNameCurrent();
            int yearCurrent = Calendar.getInstance(this.formatHelper.getLocate()).get(Calendar.YEAR);

            this.view.setMonths(monthNames);
            this.view.setMonth(monthNameCurrent);
            this.view.setYear(String.valueOf(yearCurrent));
            this.view.setTransactionTypes(TransactionHelper.getTransactionTypes());
            this.view.setTransactionType(EXPENSE_DESCRIPTION);

            this.onGetAllCategoriesTotalizers();

        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar inicializar: " + ex.getMessage());
        }
    }

    public void onGetAllCategoriesTotalizers(){
        try {
            int month = this.formatHelper.getMonthNumberByName(this.view.getMonthSelected());
            int year = Integer.valueOf(this.view.getYear());
            String type = this.view.getTransactionTypeSelected();

            List<Transaction> transactionsByMonthAndType = this.repository.getAllTransactionsByMonthAndType(month, year, type);
            CategoryGroupedListGenerator generator = new CategoryGroupedListGenerator(transactionsByMonthAndType);
            this.view.setCategoryModelViews(CategoryHelper.toCategoryGroupedModelViewList(generator.generate(), formatHelper));

        } catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar buscar dados: " + ex.getMessage());
        }
    }

    public void onShowTransactionsDetail() {
        try{
            CategoryGroupedModelView modelView = this.view.getSelectedCategoryGroupedModelView();
            Integer monthSelected = this.formatHelper.getMonthNumberByName(this.view.getMonthSelected());
            Integer year = Integer.valueOf(this.view.getYear());

            List<Transaction> transactions = this.repository.getTransactionsByCategoryAndMonthAndYear(modelView.getDescription(), monthSelected, year);
            this.view.setTransactionsFromSelectedCategory(TransactionHelper.toTransactionsModelViewList(transactions, this.formatHelper));

        }catch (Exception ex){
            this.view.showMessage("Ocorreu um erro ao tentar listas as transações: " + ex.getMessage());
        }

    }
}
