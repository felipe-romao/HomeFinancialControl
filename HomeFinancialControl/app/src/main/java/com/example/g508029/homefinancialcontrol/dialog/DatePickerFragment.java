package com.example.g508029.homefinancialcontrol.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.g508029.homefinancialcontrol.helper.FormatHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static com.example.g508029.homefinancialcontrol.Constants.ddMMyy_DATE_FORMAT_PATTERN;

public class DatePickerFragment{
    private int day;
    private int month;
    private int year;
    private Context context;
    private FormatHelper formatHelper;
    private EditText editText;
    private DatePickerDialog datePickerDialog;
    private Locale locale;

    public DatePickerFragment(Context context, FormatHelper formatHelper, EditText editText, Locale locale) {
        this.context  = context;
        this.formatHelper = formatHelper;
        this.editText = editText;
        this.locale = locale;
        //this.initialize();
    }

    public void initialize(){
        Calendar c = Calendar.getInstance(this.locale);
        try {
            if (!editText.getText().toString().isEmpty())
            {
                Date dateReceived = this.formatHelper.fromStringToDate(ddMMyy_DATE_FORMAT_PATTERN, editText.getText().toString());
                c.setTime(dateReceived);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setDay(c.get(Calendar.DAY_OF_MONTH));
        setMonth(c.get(Calendar.MONTH));
        setYear(c.get(Calendar.YEAR));

        if(editText.getText().toString().isEmpty()){
            editText.setText(getDateFormatted());
        }
    }

    public void show(){
        this.datePickerDialog = new DatePickerDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private String getDateFormatted(){
        Date date = new Date(year-1900,month,day);
        return this.formatHelper.fromDateToString(ddMMyy_DATE_FORMAT_PATTERN, date);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    Log.d(TAG, "onDateSet: year" + view.getYear());
                    Log.d(TAG, "onDateSet: month" + view.getMonth());
                    Log.d(TAG, "onDateSet: day" + view.getDayOfMonth());
                    setYear(view.getYear());
                    setMonth(view.getMonth());
                    setDay(view.getDayOfMonth());
                    editText.setText(getDateFormatted());
                }
            };

    private void setDay(int day) {
        this.day = day;
    }

    private void setMonth(int month) {
        this.month = month;
    }

    private void setYear(int year) {
        this.year = year;
    }
}
