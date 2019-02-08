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

import com.example.g508029.homefinancialcontrol.DB.SQLiteTransactionRepository;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.adpter.TransactionsReportAdapter;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.presenter.TransactionReportPresenter;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.util.List;
import java.util.Locale;

public class TransactionsReportActivity extends AppCompatActivity implements TransactionReportPresenter.ITransactionReportView {

    private EditText yearEdtitText;
    private Spinner monthSpinner;
    private ListView monthsListView;
    private Button searchButton;
    private FormatHelper formatHelper;
    private TransactionRepository repository;
    private TransactionReportPresenter presenter;
    private ArrayAdapter<String> monthAdpter;
    private TransactionsReportAdapter adapter;
    private TransactionModelView transactionSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions_report);
        this.getItemsFromActivity();
        this.initialize();
        this.initializeListernsEvent();
        registerForContextMenu(this.monthsListView);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initializeListernsEvent() {
        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGetAllTransactionsByPeriod();
            }
        });
    }

    private void initialize() {
        Locale mLocale = new Locale("pt", "BR");
        this.formatHelper = new FormatHelper(mLocale);
        this.repository  = new SQLiteTransactionRepository(this);
        this.presenter = new TransactionReportPresenter(this, this.repository, this.formatHelper);
        this.presenter.initialize();
    }

    private void getItemsFromActivity() {
        this.yearEdtitText = findViewById(R.id.transaction_report_year_editText);
        this.monthSpinner = findViewById(R.id.transaction_report_month_spinner);
        this.monthsListView = findViewById(R.id.transaction_report_listview);
        this.searchButton = findViewById(R.id.transaction_report_search_button);
    }

    @Override
    public void setMonths(List<String> months) {
        this.monthAdpter = getArrayAdpter(months);
        this.monthSpinner.setAdapter(this.monthAdpter);
    }

    @Override
    public void setMonth(String month) {
        this.monthSpinner.setSelection(this.monthAdpter.getPosition(month));
    }

    @Override
    public String getMonthSelected() {
        return this.monthAdpter.getItem(this.monthSpinner.getSelectedItemPosition());
    }

    @Override
    public void setYear(String year) {
        this.yearEdtitText.setText(year);
    }

    @Override
    public String getYear() {
        return this.yearEdtitText.getText().toString();
    }

    @Override
    public void setTransactions(List<TransactionModelView> modelViews) {
        this.adapter = new TransactionsReportAdapter(this, modelViews);
        this.monthsListView.setAdapter(this.adapter);
    }

    @Override
    public TransactionModelView getTransactionSelected() {
        return this.transactionSelected;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        this.transactionSelected = this.adapter.getItem(info.position);

        MenuItem delete = menu.add("Deletar");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onDeleteTransactionSelected();
                presenter.onGetAllTransactionsByPeriod();
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
}
