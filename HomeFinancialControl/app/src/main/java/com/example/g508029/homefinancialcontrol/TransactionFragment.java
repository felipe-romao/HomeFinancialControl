package com.example.g508029.homefinancialcontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.g508029.homefinancialcontrol.model.Transaction;
import com.example.g508029.homefinancialcontrol.dialog.DatePickerFragment;
import com.example.g508029.homefinancialcontrol.dialog.TimePickerFragment;
import com.example.g508029.homefinancialcontrol.helper.FormatHelper;
import com.example.g508029.homefinancialcontrol.helper.TransactionHelper;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.g508029.homefinancialcontrol.Constants.HHmm_TIME_FORMAT_PATTERN;
import static com.example.g508029.homefinancialcontrol.Constants.ddMMyy_DATE_FORMAT_PATTERN;

public class TransactionFragment extends Fragment {

    private Transaction transaction;
    private FormatHelper formatHelper;
    private TimePickerFragment timePickerFragment;
    private DatePickerFragment datePickerFragment;

    private EditText transactionValueEditText;
    private EditText transactionDateEditText;
    private EditText transactionTimeEditText;
    private EditText transactionAnnotationEditText;
    private Spinner transactionCategoriesSpinner;
    private Spinner transactionKindsSpinner;

    public TransactionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        this.getItemFromFragment(view);
        this.putValuesItemFromFragment();
        this.initializeListeners();

        return view;
    }

    public static TransactionFragment newIntance(Transaction transaction){
        TransactionFragment transactionFragment = new TransactionFragment();
        Bundle args = new Bundle();
        args.putSerializable("TRANSACTION", transaction);
        transactionFragment.setArguments(args);
        return transactionFragment;
    }

    public ArrayAdapter<String> getArrayAdpter(List<String> values){
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        return adapter;
    }

    public Transaction getTransaction() throws ParseException {

        Date date = this.formatHelper.fromStringToDate(ddMMyy_DATE_FORMAT_PATTERN, this.transactionDateEditText.getText().toString());
        date.setTime(this.formatHelper.fromStringToDate(HHmm_TIME_FORMAT_PATTERN, this.transactionTimeEditText.getText().toString()).getTime());

        String category         = this.transactionCategoriesSpinner.getSelectedItem().toString();
        double value            = this.formatHelper.fromCurrencyStringToDouble(this.transactionValueEditText.getText().toString());
        String description      = this.transactionAnnotationEditText.getText().toString();
        String paymentMode      = this.transactionKindsSpinner.getSelectedItem().toString();

        this.transaction.setCategory(category);
        this.transaction.setValue(value);
        this.transaction.setDescription(description);
        this.transaction.setPaymentMode(paymentMode);
        this.transaction.setDate(date);

        return this.transaction;
    }

    @Override
    public String toString() {
        return this.transaction.getType();
    }

    private void getItemFromFragment(View view){
        Bundle arguments = getArguments();
        if(arguments != null){
            this.transaction  = (Transaction) arguments.getSerializable("TRANSACTION");
        }

        Locale mLocale = new Locale("pt", "BR");
        this.formatHelper = new FormatHelper(mLocale);
        this.transactionCategoriesSpinner   = view.findViewById(R.id.transaction_frag_category);
        this.transactionKindsSpinner        = view.findViewById(R.id.transaction_frag_kind);
        this.transactionDateEditText        = view.findViewById(R.id.transaciton_frag_date);
        this.transactionTimeEditText        = view.findViewById(R.id.transaciton_frag_time);
        this.transactionValueEditText       = view.findViewById(R.id.transaciton_frag_value);
        this.transactionAnnotationEditText  = view.findViewById(R.id.transaciton_frag_description);
        this.datePickerFragment = new DatePickerFragment(getContext(), this.formatHelper, this.transactionDateEditText);
        this.timePickerFragment = new TimePickerFragment(getContext(), this.formatHelper, this.transactionTimeEditText);
    }

    private void putValuesItemFromFragment(){
        List<String> categories = TransactionHelper.getTransactionCategory(this.transaction.getType());
        List<String> kinds = TransactionHelper.getTransactionKind(this.transaction.getType());
        ArrayAdapter<String> categoriesAdapter = getArrayAdpter(categories);
        ArrayAdapter<String> kindsAdapter = getArrayAdpter(kinds);

        this.transactionCategoriesSpinner.setAdapter(categoriesAdapter);
        this.transactionKindsSpinner.setAdapter(kindsAdapter);

        this.transactionValueEditText.setText(formatHelper.fromDoubleToCurrencyString(this.transaction.getValue()));

        if(this.transaction.getCategory() != null && !this.transaction.getCategory().isEmpty()){
            this.transactionCategoriesSpinner.setSelection(categoriesAdapter.getPosition(this.transaction.getCategory()));
        }
        if(this.transaction.getPaymentMode() != null && !this.transaction.getPaymentMode().isEmpty()){
            this.transactionKindsSpinner.setSelection(kindsAdapter.getPosition(this.transaction.getPaymentMode()));
        }
    }

    private void initializeListeners(){
        this.transactionValueEditText.addTextChangedListener(new NumberTextWatcher(this.transactionValueEditText, this.formatHelper));

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
