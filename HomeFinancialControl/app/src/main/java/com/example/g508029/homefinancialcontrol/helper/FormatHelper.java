package com.example.g508029.homefinancialcontrol.helper;

import android.util.Log;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FormatHelper {
    private SimpleDateFormat dateFormat;
    private NumberFormat numberFormat;
    private Locale locale;

    public FormatHelper(Locale locale){
        this.locale       = locale;
        this.dateFormat   = new SimpleDateFormat();
        this.numberFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public Locale getLocate(){
        return this.locale;
    }

    public String fromDateToString(String pattern, Date date){
        this.dateFormat.applyPattern(pattern);
        return this.dateFormat.format(date);
    }

    public Date fromStringToDate(String pattern, String value) throws ParseException {
        this.dateFormat.applyPattern(pattern);
        return this.dateFormat.parse(value);
    }

    public String fromDoubleToCurrencyString(double value){
        return numberFormat.format(value);
    }

    public double fromCurrencyStringToDouble(String currency) throws ParseException {
        return numberFormat.parse(currency).doubleValue();
    }

    public List<String> getMonthNames(){
        return Arrays.asList(new DateFormatSymbols(this.locale).getMonths());
    }

    public int getMonthNumberByName(String monthName){
        return Arrays.asList(new DateFormatSymbols(this.locale).getMonths()).indexOf(monthName) + 1;
    }

    public String getMonthNameCurrent(){
        return this.getMonthNames().get(Calendar.getInstance(this.locale).MONTH - 1);
    }
}
