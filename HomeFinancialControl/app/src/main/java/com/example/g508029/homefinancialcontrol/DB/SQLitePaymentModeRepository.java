package com.example.g508029.homefinancialcontrol.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.g508029.homefinancialcontrol.model.PaymentMode;

import java.util.ArrayList;
import java.util.List;

public class SQLitePaymentModeRepository implements IPaymentModeRepository {
    private static String TABLE_NAME = "PaymentMode";
    private Context context;
    private DBHelper dbHelper;

    public SQLitePaymentModeRepository(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    public SQLitePaymentModeRepository(Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @Override
    public void addPaymentMode(PaymentMode paymentMode) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getContentValues(paymentMode);
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public List<PaymentMode> getAll() {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME+ ";";
        Cursor cursor = db.rawQuery(sql, null);
        return populatePaymentMode(cursor);
    }

    @Override
    public void deletePaymentModeById(String id) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{id});
    }

    private ContentValues getContentValues(PaymentMode paymentMode) {
        ContentValues values = new ContentValues();
        values.put("id", paymentMode.getId());
        values.put("description", paymentMode.getMode());
        return values;
    }

    private List<PaymentMode> populatePaymentMode(Cursor cursor) {
        List<PaymentMode> paymentModes = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String mode = cursor.getString(cursor.getColumnIndex("description"));
            PaymentMode paymentMode = new PaymentMode(id, mode);
            paymentModes.add(paymentMode);
        }
        return paymentModes;
    }
}
