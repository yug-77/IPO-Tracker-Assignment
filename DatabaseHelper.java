package com.example.ipotracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TradeTracker.db";
    private static final int DATABASE_VERSION = 1;

    // Table and Column names
    private static final String TABLE_WATCHLIST = "watchlist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_STOCK_NAME = "stock_name";
    private static final String COLUMN_ENTRY_PRICE = "entry_price";
    private static final String COLUMN_TARGET_PRICE = "target_price";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the Watchlist table
        String createTable = "CREATE TABLE " + TABLE_WATCHLIST + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STOCK_NAME + " TEXT, " +
                COLUMN_ENTRY_PRICE + " REAL, " +
                COLUMN_TARGET_PRICE + " REAL)";
        db.execSQL(createTable);

        // Insert some initial tracking data for testing
        insertSeedData(db);
    }

    private void insertSeedData(SQLiteDatabase db) {
        ContentValues coalIndia = new ContentValues();
        coalIndia.put(COLUMN_STOCK_NAME, "Coal India");
        coalIndia.put(COLUMN_ENTRY_PRICE, 435.50);
        coalIndia.put(COLUMN_TARGET_PRICE, 460.00);
        db.insert(TABLE_WATCHLIST, null, coalIndia);

        ContentValues tataCap = new ContentValues();
        tataCap.put(COLUMN_STOCK_NAME, "Tata Capital");
        tataCap.put(COLUMN_ENTRY_PRICE, 1200.00);
        tataCap.put(COLUMN_TARGET_PRICE, 1350.50);
        db.insert(TABLE_WATCHLIST, null, tataCap);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATCHLIST);
        onCreate(db);
    }
    public String getWatchlistData() {
        StringBuilder builder = new StringBuilder();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_WATCHLIST, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                double entry = cursor.getDouble(2);
                double target = cursor.getDouble(3);
                builder.append("Stock: ").append(name).append("\n")
                        .append("Entry: ₹").append(entry).append("  |  Target: ₹").append(target)
                        .append("\n\n");
            } while (cursor.moveToNext());
        } else {
            builder.append("Watchlist is empty.");
        }
        cursor.close();
        return builder.toString();
    }
}
