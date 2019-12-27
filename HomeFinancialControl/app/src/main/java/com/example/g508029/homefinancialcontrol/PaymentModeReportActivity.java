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
import com.example.g508029.homefinancialcontrol.adpter.PamentModeReportAdapter;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.model.InfoTransactionGrouped;
import com.example.g508029.homefinancialcontrol.presenter.PaymentModeReportPresenter;
import com.example.g508029.homefinancialcontrol.presenter.modelView.InfoTransactionGroupedModelView;
import com.example.g508029.homefinancialcontrol.presenter.modelView.TransactionModelView;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class PaymentModeReportActivity extends HomeActivity implements PaymentModeReportPresenter.IPaymentModeReportView{

    private Spinner monthSpinner;
    private ListView infoListView;
    private Button searchButton;
    private EditText yearEditText;
    private FormatHelper formatHelper;
    private TransactionRepository repository;
    private PaymentModeReportPresenter presenter;
    private ArrayAdapter<String> monthAdpter;
    private PamentModeReportAdapter paymentModeReportAdapter;
    private boolean hasHeaderListView = false;
    private InfoTransactionGroupedModelView selectedInfoModelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mode_report);

        this.getItemsFromActivity();
        this.initialize();
        this.initializeListernsEvent();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initializeListernsEvent() {
        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGetAllInfoPaymentModeGrouped();
            }
        });

        this.infoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedInfoModelView = (InfoTransactionGroupedModelView) infoListView.getItemAtPosition(position);
                presenter.onShowTransactionsDetail();
            }
        });
    }

    private void initialize() {
        Locale mLocale = new Locale("pt", "BR");
        this.formatHelper = new FormatHelper(mLocale);
        this.repository  = new SQLiteTransactionRepository(this);
        this.presenter = new PaymentModeReportPresenter(this, this.repository, this.formatHelper);
        this.presenter.initialize();
    }

    private void getItemsFromActivity() {
        this.monthSpinner = findViewById(R.id.payment_mode_report_month_spinner);
        this.infoListView = findViewById(R.id.payment_mode_report_listview);
        this.searchButton = findViewById(R.id.payment_mode_report_search_button);
        this.yearEditText = findViewById(R.id.payment_mode_report_year_editText);
    }

    private ArrayAdapter<String> getArrayAdpter(List<String> values){
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        return adapter;
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
        this.yearEditText.setText(year);
    }

    @Override
    public String getYear() {
        return this.yearEditText.getText().toString();
    }

    @Override
    public void setInfoTransactionGroupedModelViews(List<InfoTransactionGroupedModelView> infoGroupedModelViews) {
        this.paymentModeReportAdapter = new PamentModeReportAdapter(this, infoGroupedModelViews);
        this.infoListView.setAdapter(this.paymentModeReportAdapter);

        if(hasHeaderListView){
            this.infoListView.addHeaderView(this.createHeaderView());
            hasHeaderListView = true;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public InfoTransactionGroupedModelView getSelectedInfoTransactionModelView(){
        return this.selectedInfoModelView;
    }

    @Override
    public void setTransactionsFromSelectedPaymentMode(List<TransactionModelView> modelViews)
    {
        Intent intent = new Intent(PaymentModeReportActivity.this, FiltredTransactionsActivity.class);
        intent.putExtra("transactions", (Serializable) modelViews);
        intent.putExtra("title", "Modo de pagamento: " + this.selectedInfoModelView.getDescription());
        startActivity(intent);
    }

    private View createHeaderView(){
        View headerView = getLayoutInflater().inflate(R.layout.info_transaction_grouped_list, null);
        this.paymentModeReportAdapter.populateFields(headerView, this.paymentModeReportAdapter.createHeaderData());
        return headerView;
    }
}
