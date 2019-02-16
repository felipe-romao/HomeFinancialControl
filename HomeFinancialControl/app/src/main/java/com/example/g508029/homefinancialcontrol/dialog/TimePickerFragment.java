package com.example.g508029.homefinancialcontrol.dialog;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.g508029.homefinancialcontrol.helper.FormatHelper;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static com.example.g508029.homefinancialcontrol.Constants.HHmm_TIME_FORMAT_PATTERN;

public class TimePickerFragment{
    private Context context;
    private EditText editText;
    private TimePickerDialog timePickerDialog;
    private int hour;
    private int minute;
    private FormatHelper formatHelper;
    private Locale locale;

    public TimePickerFragment(Context context, FormatHelper formatHelper, EditText editText, Locale locale){
        this.context = context;
        this.formatHelper = formatHelper;
        this.editText = editText;
        this.locale = locale;
//        initialize();
    }

    public void initialize(){
        Calendar c = Calendar.getInstance(locale);
        try {
            if (!editText.getText().toString().isEmpty())
            {
                Date dateReceived = this.formatHelper.fromStringToDate(HHmm_TIME_FORMAT_PATTERN, editText.getText().toString());
                c.setTime(dateReceived);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setHour(c.get(Calendar.HOUR_OF_DAY));
        setMinute(c.get(Calendar.MINUTE));

        if(editText.getText().toString().isEmpty()){
            editText.setText(getTimeFormatted());
        }
    }

    public void show(){
        timePickerDialog = new TimePickerDialog(context, AlertDialog.THEME_HOLO_LIGHT, timeSetListener, hour, minute, true);
        timePickerDialog.show();
    }

    private String getTimeFormatted(){
        return this.formatHelper.fromDateToString(HHmm_TIME_FORMAT_PATTERN, new Time(hour,minute, 0));
    }

    private TimePickerDialog.OnTimeSetListener timeSetListener =
            new TimePickerDialog.OnTimeSetListener() {

                //@RequiresApi(api = Build.VERSION_CODES.M)
                public void onTimeSet(TimePicker view, int hour, int minute) {
                    int currentApiVersion = Build.VERSION.SDK_INT;
                    if(currentApiVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
                        setHour(view.getHour());
                        setMinute(view.getMinute());
                    }else {
                        setHour(view.getCurrentHour());
                        setMinute(view.getCurrentMinute());
                    }
                    editText.setText(getTimeFormatted());
                }
            };

    private void setHour(int hour) {
        this.hour = hour;
    }

    private void setMinute(int minute) {
        this.minute = minute;
    }
}
