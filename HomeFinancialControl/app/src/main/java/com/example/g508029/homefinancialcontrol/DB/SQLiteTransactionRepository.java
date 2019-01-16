package com.example.g508029.homefinancialcontrol.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.g508029.homefinancialcontrol.model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SQLiteTransactionRepository implements TransactionRepository{
    private static String TABLE_NAME = "\"Transaction\"";
    private Context context;
    private DBHelper dbHelper;

    public SQLiteTransactionRepository(Context context){
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public void addTransaction(Transaction transaction){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getContentValues(transaction);
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void updateTransaction(Transaction transaction) {

    }

    @Override
    public Transaction getTransactionById(String id) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByMonth(int month) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);

        return populateTransactions(cursor);
    }

    @Override
    public void deleteTransaction(String id) {

    }

    @NonNull
    private ContentValues getContentValues(Transaction transaction) {
        ContentValues values = new ContentValues();
        //new Date(cursor.getLong(yourColumnIndex));

        values.put("id", transaction.getId());
        values.put("type", transaction.getType());
        values.put("value", transaction.getValue());
        values.put("date", transaction.getDate().getTime());
        values.put("category", transaction.getCategory());
        values.put("payment_mode", transaction.getPaymentMode());
        values.put("description", transaction.getDescription());
        return values;
    }

    @NonNull
    private List<Transaction> populateTransactions(Cursor cursor) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        while (cursor.moveToNext()){
            String type = cursor.getString(cursor.getColumnIndex("type"));
            Transaction transaction = new Transaction(type);
            transaction.setId(cursor.getString(cursor.getColumnIndex("id")));
            transaction.setValue(cursor.getDouble(cursor.getColumnIndex("value")));
            transaction.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            transaction.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            transaction.setPaymentMode(cursor.getString(cursor.getColumnIndex("payment_mode")));
            transaction.setDate(new Date(cursor.getLong(cursor.getColumnIndex("date"))));

            transactions.add(transaction);
        }

        return transactions;
    }
}
