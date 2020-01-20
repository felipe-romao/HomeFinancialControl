package com.example.g508029.homefinancialcontrol.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.g508029.homefinancialcontrol.model.Category;

import java.util.ArrayList;
import java.util.List;

public class SQLiteCategoryRepository implements ICategoryRepository {
    private static String TABLE_NAME = "Category";
    private Context context;
    private DBHelper dbHelper;

    public SQLiteCategoryRepository(Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    public SQLiteCategoryRepository(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public void addCategory(Category category) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getContentValues(category);
        db.insert(TABLE_NAME, null, values);
    }

    public void updateCategory(Category category) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = getContentValues(category);
        db.update(TABLE_NAME, values, "id=?", new String[]{category.getId()});
    }

    @Override
    public List<Category> getAll() {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME+ ";";
        Cursor cursor = db.rawQuery(sql, null);
        return populatePaymentMode(cursor);
    }

    @Override
    public void deleteCategoryById(String categoryId) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{categoryId});
    }

    private ContentValues getContentValues(Category category) {
        ContentValues values = new ContentValues();
        values.put("id", category.getId());
        values.put("description", category.getDescription());
        values.put("movement_type", category.getTypeMovement());
        values.put("frequency", category.getFrequency());

        return values;
    }

    private List<Category> populatePaymentMode(Cursor cursor) {
        List<Category> categories = new ArrayList<>();
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String description  = cursor.getString(cursor.getColumnIndex("description"));
            String movementType = cursor.getString(cursor.getColumnIndex("movement_type"));
            String frequency    = cursor.getString(cursor.getColumnIndex("frequency"));
            Category category   = new Category(id, description, movementType, frequency);
            categories.add(category);
        }
        return categories;
    }
}
