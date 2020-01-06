package com.example.g508029.homefinancialcontrol;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.g508029.homefinancialcontrol.DB.DBHelper;
import com.example.g508029.homefinancialcontrol.DB.ICategoryRepository;
import com.example.g508029.homefinancialcontrol.DB.IPaymentModeRepository;
import com.example.g508029.homefinancialcontrol.DB.SQLiteCategoryRepository;
import com.example.g508029.homefinancialcontrol.DB.SQLitePaymentModeRepository;
import com.example.g508029.homefinancialcontrol.DB.SQLiteTransactionRepository;
import com.example.g508029.homefinancialcontrol.DB.TransactionRepository;
import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.dialog.DatePickerFragment;
import com.example.g508029.homefinancialcontrol.dialog.TimePickerFragment;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;
import com.example.g508029.homefinancialcontrol.presenter.TransactionFragmentPresenter;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.g508029.homefinancialcontrol.Constants.HHmm_TIME_FORMAT_PATTERN;
import static com.example.g508029.homefinancialcontrol.Constants.ddMMyy_DATE_FORMAT_PATTERN;

public class TransactionFragment extends Fragment implements TransactionFragmentPresenter.ITransactionFragmentView{

    private String transactionType;
    private FormatHelper formatHelper;
    private TimePickerFragment timePickerFragment;
    private DatePickerFragment datePickerFragment;

    private EditText transactionValueEditText;
    private EditText transactionDateEditText;
    private EditText transactionTimeEditText;
    private EditText transactionAnnotationEditText;
    private Spinner transactionCategoriesSpinner;
    private Spinner transactionKindsSpinner;
    private Spinner optionsCashesSpinner;
    private TransactionRepository repository;
    private TransactionFragmentPresenter presenter;
    private ArrayAdapter<String> categoriesAdapter;
    private ArrayAdapter<String> kindsAdapter;
    private ArrayAdapter<String> optionsCashesAdapter;
    private ICategoryRepository categoryRepository;
    private IPaymentModeRepository paymentModeRepository;
    private Locale mLocale;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        this.initialize();
        this.getItemFromFragment(view);
        this.presenter.initialize();
        this.initializeListeners();

        return view;
    }

    public static TransactionFragment newIntance(String transactionType){
        TransactionFragment transactionFragment = new TransactionFragment();
        Bundle args = new Bundle();
        args.putSerializable("TRANSACTION_TYPE", transactionType);
        transactionFragment.setArguments(args);
        return transactionFragment;
    }

    public ArrayAdapter<String> getArrayAdpter(List<String> values){
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        return adapter;
    }

    @Override
    public String toString() {
        return this.transactionType;
    }

    @Override
    public String getCategory() {
        return this.categoriesAdapter.getItem(this.transactionCategoriesSpinner.getSelectedItemPosition());
    }

    @Override
    public String getTransactionValue() {
        return this.transactionValueEditText.getText().toString();
    }

    @Override
    public String getDescription() {
        return this.transactionAnnotationEditText.getText().toString();
    }

    @Override
    public String getPaymentMode() {
        return this.kindsAdapter.getItem(this.transactionKindsSpinner.getSelectedItemPosition());
    }

    @Override
    public String getTransactionDate() {
        return this.transactionDateEditText.getText().toString();
    }

    @Override
    public String getTransactionTime() {
        return this.transactionTimeEditText.getText().toString();
    }

    @Override
    public String getTransactionType() {
        return this.transactionType;
    }

    @Override
    public int getOptionCashSelected() {
        return  this.optionsCashesSpinner.getSelectedItemPosition() + 1;
    }

    @Override
    public void setInitialDateTime() {
        this.transactionDateEditText.setText("");
        this.transactionTimeEditText.setText("");
        this.datePickerFragment.initialize();
        this.timePickerFragment.initialize();
    }

    @Override
    public void setTransactionValue(String value) {

        this.transactionValueEditText.setText(value);
    }

    @Override
    public void setCategories(List<String> categories) {
        this.categoriesAdapter = getArrayAdpter(categories);
        this.transactionCategoriesSpinner.setAdapter(categoriesAdapter);
    }

    @Override
    public void setPaymentModes(List<String> paymentModes) {
        this.kindsAdapter = getArrayAdpter(paymentModes);
        this.transactionKindsSpinner.setAdapter(kindsAdapter);
    }

    @Override
    public void setOptionsCashes(List<String> optionsCashes) {
        this.optionsCashesAdapter = getArrayAdpter(optionsCashes);
        this.optionsCashesSpinner.setAdapter(optionsCashesAdapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void addTransactionInDatabase(){
        this.presenter.onAddNewTransaction();
    }

    private void getItemFromFragment(View view){
        Bundle arguments = getArguments();
        if(arguments != null){
            this.transactionType  = (String)arguments.getSerializable("TRANSACTION_TYPE");
        }
        this.transactionCategoriesSpinner   = view.findViewById(R.id.transaction_frag_category);
        this.transactionKindsSpinner        = view.findViewById(R.id.transaction_frag_kind);
        this.optionsCashesSpinner           = view.findViewById(R.id.transaction_frag_option_cash);
        this.transactionDateEditText        = view.findViewById(R.id.transaciton_frag_date);
        this.transactionTimeEditText        = view.findViewById(R.id.transaciton_frag_time);
        this.transactionValueEditText       = view.findViewById(R.id.transaciton_frag_value);
        this.transactionAnnotationEditText  = view.findViewById(R.id.transaciton_frag_description);
        this.datePickerFragment             = new DatePickerFragment(getContext(), this.formatHelper, this.transactionDateEditText, this.mLocale);
        this.timePickerFragment             = new TimePickerFragment(getContext(), this.formatHelper, this.transactionTimeEditText, this.mLocale);
    }

    private void initialize(){
        this.mLocale        = new Locale("pt", "BR");
        DBHelper dbHelper   = new DBHelper(getContext());
        this.formatHelper   = new FormatHelper(mLocale);
        this.repository     = new SQLiteTransactionRepository(getContext(), dbHelper);
        this.categoryRepository    = new SQLiteCategoryRepository(getContext(), dbHelper);
        this.paymentModeRepository = new SQLitePaymentModeRepository(getContext(), dbHelper);
        this.presenter      = new TransactionFragmentPresenter(this, this.repository, this.categoryRepository, this.paymentModeRepository, this.formatHelper);
    }

    private void initializeListeners(){
        this.transactionValueEditText.addTextChangedListener(
                new NumberTextWatcher(this.transactionValueEditText, this.formatHelper));

        this.transactionTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerFragment.show();
            }
        });

        this.transactionDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show();
            }
        });
    }
}
