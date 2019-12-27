package com.example.g508029.homefinancialcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.g508029.homefinancialcontrol.DB.SQLiteTransactionRepository;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.adpter.CategoryReportAdapter;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.presenter.CategoryReportPresenter;
import com.example.g508029.homefinancialcontrol.presenter.modelView.CategoryGroupedModelView;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class CategoryReportActivity extends HomeActivity implements CategoryReportPresenter.ICategoryReportView {

    private Spinner monthsSpinner;
    private Spinner movementTypesSpinner;
    private Button searchButton;
    private EditText yearEditText;
    private ListView categoriesListView;
    private FormatHelper formatHelper;
    private TransactionRepository repository;
    private CategoryReportPresenter presenter;
    private ArrayAdapter<String> monthAdpter;
    private ArrayAdapter<String> transactionTypesAdpter;
    private CategoryReportAdapter categoryReportAdapter;
    private boolean hasHeaderCategoriesListView = false;
    private CategoryGroupedModelView selectedCategoryModelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_report);
        this.getItemsFromActivity();
        this.initialize();
        this.initializeListernsEvent();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void setTransactionTypes(List<String> transactionTypes) {
        this.transactionTypesAdpter = getArrayAdpter(transactionTypes);
        this.movementTypesSpinner.setAdapter(this.transactionTypesAdpter);
    }

    @Override
    public void setTransactionType(String transactionType) {
        this.movementTypesSpinner.setSelection(this.transactionTypesAdpter.getPosition(transactionType));
    }

    @Override
    public String getTransactionTypeSelected() {
        return this.transactionTypesAdpter.getItem(this.movementTypesSpinner.getSelectedItemPosition());
    }

    @Override
    public void setMonths(List<String> months) {
        this.monthAdpter = getArrayAdpter(months);
        this.monthsSpinner.setAdapter(this.monthAdpter);
    }

    @Override
    public void setMonth(String month) {
        this.monthsSpinner.setSelection(this.monthAdpter.getPosition(month));
    }

    @Override
    public String getMonthSelected() {
        return this.monthAdpter.getItem(this.monthsSpinner.getSelectedItemPosition());
    }

    @Override
    public void setYear(String year) {
        this.yearEditText.setText(year);
    }

    @Override
    public String getYear() {
        return this.yearEditText.getText().toString();
    }

    @Override
    public void setCategoryModelViews(List<CategoryGroupedModelView> categoryGroupedModelViews) {
        this.categoryReportAdapter = new CategoryReportAdapter(this, categoryGroupedModelViews);
        this.categoriesListView.setAdapter(this.categoryReportAdapter);

        if(hasHeaderCategoriesListView){
            this.categoriesListView.addHeaderView(this.createHeaderView());
            hasHeaderCategoriesListView = true;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private View createHeaderView(){
        View headerView = getLayoutInflater().inflate(R.layout.category_totalizer_list, null);
        this.categoryReportAdapter.populateFields(headerView, this.categoryReportAdapter.createHeaderData());
        return headerView;
    }

    private ArrayAdapter<String> getArrayAdpter(List<String> values){
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        return adapter;
    }

    public CategoryGroupedModelView getSelectedCategoryGroupedModelView(){
        return this.selectedCategoryModelView;
    }

    public void setTransactionsFromSelectedCategory(List<TransactionModelView> modelViews){
        Intent intent = new Intent(CategoryReportActivity.this, FiltredTransactionsActivity.class);
        intent.putExtra("transactions", (Serializable) modelViews);
        intent.putExtra("title", "Categoria: " + this.selectedCategoryModelView.getDescription());
        startActivity(intent);
    }

    private void initializeListernsEvent() {
        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGetAllCategoriesTotalizers();
            }
        });

        this.categoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCategoryModelView = (CategoryGroupedModelView) categoriesListView.getItemAtPosition(position);
                presenter.onShowTransactionsDetail();
            }
        });
    }

    private void initialize() {
        Locale mLocale = new Locale("pt", "BR");
        this.formatHelper = new FormatHelper(mLocale);
        this.repository  = new SQLiteTransactionRepository(this);
        this.presenter = new CategoryReportPresenter(this, this.repository, this.formatHelper);
        this.presenter.initialize();
    }

    private void getItemsFromActivity() {
        this.monthsSpinner = findViewById(R.id.category_report_month_spinner);
        this.movementTypesSpinner = findViewById(R.id.category_report_movement_type_spinner);
        this.searchButton = findViewById(R.id.category_report_search_button);
        this.yearEditText = findViewById(R.id.category_report_year_editText);
        this.categoriesListView = findViewById(R.id.category_report_listview);
    }
}
