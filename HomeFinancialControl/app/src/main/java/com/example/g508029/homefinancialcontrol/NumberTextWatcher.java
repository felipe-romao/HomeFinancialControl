package com.example.g508029.homefinancialcontrol;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.g508029.homefinancialcontrol.helper.FormatHelper;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class NumberTextWatcher implements TextWatcher {
    private final WeakReference<EditText> editTextWeakReference;
    private FormatHelper formatHelper;

    public NumberTextWatcher(EditText editText, FormatHelper formatHelper) {
        this.editTextWeakReference = new WeakReference<EditText>(editText);
        this.formatHelper = formatHelper;
        editText.setSelection(editText.length());
    }

    public NumberTextWatcher(EditText editText) {
        this.editTextWeakReference = new WeakReference<EditText>(editText);
        this.formatHelper = new FormatHelper(Locale.getDefault());
        editText.setSelection(editText.length());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        EditText editText = editTextWeakReference.get();
        if (editText == null) return;
        editText.removeTextChangedListener(this);

        BigDecimal parsed = parseToBigDecimal(editable.toString(), this.formatHelper.getLocate());
        String formatted = this.formatHelper.fromDoubleToCurrencyString(parsed.doubleValue());

        editText.setText(formatted);
        editText.setSelection(formatted.length());
        editText.addTextChangedListener(this);
    }

    private BigDecimal parseToBigDecimal(String value, Locale locale) {
        String replaceable = String.format("[%s,.\\s]", NumberFormat.getCurrencyInstance(locale).getCurrency().getSymbol());

        String cleanString = value.replaceAll(replaceable, "");

        return new BigDecimal(cleanString).setScale(
                2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR
        );
    }
}