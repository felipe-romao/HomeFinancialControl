package com.example.g508029.homefinancialcontrol.helper;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.g508029.homefinancialcontrol.R;
import com.example.g508029.homefinancialcontrol.model.Transaction;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import static android.content.ContentValues.TAG;
import static com.example.g508029.homefinancialcontrol.Constants.HHmm_TIME_FORMAT_PATTERN;
import static com.example.g508029.homefinancialcontrol.Constants.ddMMyy_DATE_FORMAT_PATTERN;

public class TransactionFragmentHelper {
    private Transaction transaction;
    private View view;
    public TransactionFragmentHelper (View view, FormatHelper formatHelper){
        this.view = view;
        this.formatHelper = formatHelper;
        this.getItemsFromFragment();
    }

    private FormatHelper formatHelper;
    private TextView transactionTypeTextView;
    private EditText transactionValueEditText;
    private EditText transactionDateEditText;
    private EditText transactionTimeEditText;
    private EditText transactionAnnotationEditText;
    private Spinner transactionCategoriesSpinner;
    private Spinner transactionKindsSpinner;

    private void getItemsFromFragment(){
        this.transactionTypeTextView        = this.view.findViewById(R.id.transaciton_frag_type);
        this.transactionCategoriesSpinner   = this.view.findViewById(R.id.transaction_frag_category);
        this.transactionKindsSpinner        = this.view.findViewById(R.id.transaction_frag_kind);
        this.transactionDateEditText        = this.view.findViewById(R.id.transaciton_frag_date);
        this.transactionTimeEditText        = this.view.findViewById(R.id.transaciton_frag_time);
        this.transactionValueEditText       = this.view.findViewById(R.id.transaciton_frag_value);
        this.transactionAnnotationEditText  = this.view.findViewById(R.id.transaciton_frag_description);
    }

    public Transaction createTransaction() throws ParseException {

        Log.d(TAG, "createTransaction: date: " + this.transactionDateEditText.getText().toString());

        Date date = this.formatHelper.fromStringToDate(ddMMyy_DATE_FORMAT_PATTERN, this.transactionDateEditText.getText().toString());
        Log.d(TAG, "createTransaction: date2: " + date);

        date.setTime(this.formatHelper.fromStringToDate(HHmm_TIME_FORMAT_PATTERN, this.transactionTimeEditText.getText().toString()).getTime());

        String type             = this.transactionTypeTextView.getText().toString();
        String category         = this.transactionCategoriesSpinner.getSelectedItem().toString();
        double value            = this.formatHelper.fromCurrencyStringToDouble(this.transactionValueEditText.getText().toString());
        String description      = this.transactionAnnotationEditText.getText().toString();
        String paymentMode      = this.transactionKindsSpinner.getSelectedItem().toString();

        this.transaction = new Transaction(UUID.randomUUID().toString(),type,description,value,date,category,paymentMode);
        return this.transaction;
    }

    public EditText getTransactionValueEditText(){
        return this.transactionValueEditText;
    }

    public EditText getTransactionDateEditText(){
        return this.transactionDateEditText;
    }

    public EditText getTransactionTimeEditText(){
        return this.transactionTimeEditText;
    }

    public Spinner getTransactionCategoriesSpinner(){
        return transactionCategoriesSpinner;
    }
    public Spinner getTransactionKindsSpinner(){
        return transactionKindsSpinner;
    }
}
