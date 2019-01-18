package com.example.g508029.homefinancialcontrol.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.g508029.homefinancialcontrol.model.Transaction;

import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "HomeFinancialControl";
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_creation_category_table = String.format("CREATE TABLE Category (id TEXT PRIMARY KEY, " +
                "description TEXT NOT NULL, " +
                "movement_type TEXT NOT NULL);");

        String sql_creation_paymentmode_table = String.format("CREATE TABLE PaymentMode (id TEXT PRIMARY KEY, " +
                        "description TEXT NOT NULL);");

        String sql_creation_transaction_table = String.format("CREATE TABLE \"Transaction\" (id TEXT PRIMARY KEY, " +
                "type TEXT NOT NULL, " +
                "description TEXT NULL, " +
                "value REAL NOT NULL, " +
                "date INTEGER NOT NULL, " +
                "image TEXT NULL, " +
                "category TEXT NOT NULL, " +
                "payment_mode TEXT NOT NULL);");

        db.execSQL(sql_creation_category_table);
        db.execSQL(sql_creation_paymentmode_table);
        db.execSQL(sql_creation_transaction_table);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS Category;");
        db.execSQL("DROP TABLE IF EXISTS PaymentMode;");
        db.execSQL("DROP TABLE IF EXISTS \"Transaction\";");

        onCreate(db);
    }

    public void clearDbAndRecreate() {
        clearDb();
        onCreate(this.getWritableDatabase());
    }

    public void clearDb() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Category;");
        db.execSQL("DROP TABLE IF EXISTS PaymentMode;");
        db.execSQL("DROP TABLE IF EXISTS \"Transaction\";");
    }
}
