package com.example.climatehero.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Recycle.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Classification";
    public static final String COL_1 = "keyword";
    public static final String COL_2 = "category";
    public static final String COL_3 = "db_version";
    public static final String TABLE_NAME_2 = "facts";
    public static final String COL_4 = "fact";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_1 + " TEXT PRIMARY KEY,"
                + COL_2 + " TEXT,"
                + COL_3 + " INTEGER DEFAULT NULL)";

        String query2 = "CREATE TABLE " + TABLE_NAME_2 + " ("
                + COL_4 + " VARCHAR(100) PRIMARY KEY)";

        db.execSQL(query1);
        db.execSQL(query2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(db);

    }

    public boolean insertClassificationData(String keyword, String category, @Nullable Integer db_version) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, keyword);
        contentValues.put(COL_2, category);
        contentValues.put(COL_3, db_version);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }

    public boolean insertFactsData(String fact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4, fact);
        long result = db.insert(TABLE_NAME_2, null, contentValues);
        if (result == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }

    public String getSuggestedBin(String object) {
        SQLiteDatabase db = this.getWritableDatabase();
        String noMatch = "NoMatch";
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE keyword = '" + object + "'", null);
        if (res.getCount() == 0) {
            return noMatch;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(1));
        }
        return buffer.toString();
    }

    public ArrayList<String> getFact() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> facts = new ArrayList<>();
        String noResult = "NoMatch";
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME_2, null);
        if (res.getCount() <= 0) {
            facts.add(noResult);
            return facts;
        } else {
            res.moveToFirst();
            int count;
            while(!res.isAfterLast()){
                count = res.getColumnIndex(COL_4);
                facts.add(res.getString(count));
                res.moveToNext();
            }
        }
        return facts;
    }


    public void clearIfExist() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(db);
    }

    public int getVersionFromTable() {
        int version = 1;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("SELECT db_version FROM " + TABLE_NAME, null);

            if (res.getCount() == 0) {
                return version;
            } else {
                while(res.moveToNext()) {
                    version = res.getInt(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } return version;
    }
}