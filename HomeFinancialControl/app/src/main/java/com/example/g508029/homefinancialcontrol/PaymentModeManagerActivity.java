package com.example.g508029.homefinancialcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.g508029.homefinancialcontrol.DB.IPaymentModeRepository;
import com.example.g508029.homefinancialcontrol.DB.SQLitePaymentModeRepository;
import com.example.g508029.homefinancialcontrol.model.PaymentMode;
import com.example.g508029.homefinancialcontrol.presenter.PaymentModeManagerPresenter;

import java.util.List;

public class PaymentModeManagerActivity extends AppCompatActivity implements PaymentModeManagerPresenter.IPaymentModeManagerView{

    private static final String TAG = "TESTE";
    private EditText descriptionEditText;
    private Button addButton;
    private ListView paymentModesListView;
    private IPaymentModeRepository paymentModeRepository;
    private PaymentModeManagerPresenter presenter;
    private ArrayAdapter<PaymentMode> paymentModeArrayAdapter;
    private PaymentMode paymentModeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mode_manager);
        this.getItemsFromActivity();
        this.initialize();
        this.initializeListenersEvent();
        registerForContextMenu(this.paymentModesListView);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public String getMode() {
        return this.descriptionEditText.getText().toString();
    }

    @Override
    public void setMode(String mode) {
        this.descriptionEditText.setText(mode);
    }

    @Override
    public void setPaymentModes(List<PaymentMode> paymentModes) {
        this.paymentModeArrayAdapter = new ArrayAdapter<PaymentMode>(this, android.R.layout.simple_list_item_1, paymentModes);
        this.paymentModesListView.setAdapter(this.paymentModeArrayAdapter);
    }

    @Override
    public PaymentMode getPaymentModeSelected() {
        return this.paymentModeSelected;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        this.paymentModeSelected = this.paymentModeArrayAdapter.getItem(info.position);
        MenuItem delete = menu.add("Deletar");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.onDeletedPaymentMode();
                presenter.onGetAllPaymentModes();
                return false;
            }
        });
    }

    private void initializeListenersEvent() {
        this.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddedPaymentMode();
                presenter.onGetAllPaymentModes();
            }
        });
    }

    private void getItemsFromActivity() {
        this.descriptionEditText = findViewById(R.id.payment_mode_description_editText);
        this.addButton = findViewById(R.id.payment_mode_add_button);
        this.paymentModesListView = findViewById(R.id.payment_mode_listview);
    }

    private void initialize() {
        this.paymentModeRepository = new SQLitePaymentModeRepository(this);
        this.presenter = new PaymentModeManagerPresenter(this, this.paymentModeRepository);
        this.presenter.initialize();
    }
}
