package com.example.finaltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Gallery.db";
    public static final String TABLE_NAME = "gallery";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String MESSAGE = "message";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table gallery " +
                        "(id integer primary key,name text, message text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS gallery");
        onCreate(db);
    }

    public boolean insertGallery(String name, String message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("message", message);

        db.insert("gallery", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gallery where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateGallery(Integer id, String name, String message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("message", message);
        db.update("gallery", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteGallery(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("gallery",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList getAllGallery() {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gallery", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(ID)) + " " +
                    res.getString(res.getColumnIndex(NAME)) + " " + res.getString(res.getColumnIndex(MESSAGE)));
            res.moveToNext();
        }
        return array_list;
    }
}
