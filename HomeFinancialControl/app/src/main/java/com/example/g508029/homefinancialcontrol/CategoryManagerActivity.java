package com.example.g508029.homefinancialcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.g508029.homefinancialcontrol.DB.ICategoryRepository;
import com.example.g508029.homefinancialcontrol.DB.SQLiteCategoryRepository;
import com.example.g508029.homefinancialcontrol.model.Category;
import com.example.g508029.homefinancialcontrol.presenter.CategoryManagerPresenter;
import com.example.g508029.homefinancialcontrol.presenter.CategoryManagerPresenter.ICategoryManagerView;

import java.util.List;

public class CategoryManagerActivity extends HomeActivity implements ICategoryManagerView{

    private EditText descriptionEditText;
    private ListView categoryListView;
    private Button categoryAddButton;
    private Spinner transactionTypeSpinner;
    private ICategoryRepository repository;
    private CategoryManagerPresenter presenter;
    private ArrayAdapter<String> transactionTypeAdpter;
    private ArrayAdapter<Category> categoryAdapter;
    private Category categorySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_manager);
        this.getItemsFromActivity();
        this.initialize();
        this.initializeListernsEvent();
        registerForContextMenu(this.categoryListView);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public String getTransactionTypeSelected() {
        return this.transactionTypeAdpter.getItem(this.transactionTypeSpinner.getSelectedItemPosition());
    }

    @Override
    public void setTransactionTypes(List<String> transactionTypes) {
        this.transactionTypeAdpter = getArrayAdpter(transactionTypes);
        this.transactionTypeSpinner.setAdapter(this.transactionTypeAdpter);
    }

    @Override
    public String getDescription() {
        return this.descriptionEditText.getText().toString();
    }

    @Override
    public void setCategories(List<Category> categories) {
        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        this.categoryListView.setAdapter(categoryAdapter);
    }

    @Override
    public Category getCategorySelected() {
        return this.categorySelected;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearValues() {
        this.descriptionEditText.setText("");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        this.categorySelected = categoryAdapter.getItem(info.position);

        MenuItem delete = menu.add("Deletar");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onDeleteCategorySelected();
                presenter.onGetAllCategories();
                return false;
            }
        });
    }

    public ArrayAdapter<String> getArrayAdpter(List<String> values){
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        return adapter;
    }

    private void initializeListernsEvent() {
        this.categoryAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddedCategory();
                presenter.onGetAllCategories();
            }
        });
    }

    private void initialize() {
        this.repository = new SQLiteCategoryRepository(this);
        this.presenter = new CategoryManagerPresenter(this, this.repository);
        this.presenter.initialize();
    }

    private void getItemsFromActivity() {
        this.descriptionEditText = findViewById(R.id.category_manager_description_editText);
        this.categoryListView = findViewById(R.id.category_manager_listview);
        this.categoryAddButton = findViewById(R.id.category_manager_add_button);
        this.transactionTypeSpinner = findViewById(R.id.category_manager_transaction_type_spinner);
    }
}
