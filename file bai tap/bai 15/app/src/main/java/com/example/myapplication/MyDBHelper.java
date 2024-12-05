package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DBName = "mydb.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "Sinhvien";
    private static  String ID = "_id";
    private static  String NAME = "name";
    private static  String YEARDB = "yeardb";
    private SQLiteDatabase myDB;

    public MyDBHelper(@Nullable Context context) {
        super(context, DBName, null, VERSION);
    }

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getYEARDB() {
        return YEARDB;
    }

    public SQLiteDatabase getMyDB() {
        return myDB;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // tao database
        String queryTable = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + YEARDB + " INTEGER NOT NULL)";

        sqLiteDatabase.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void openDB(){
        myDB = getWritableDatabase();
    }
    public void closeDB(){
        if (myDB != null && myDB.isOpen()){
            myDB.close();
        }
    }
    // insert

    public long Insert(String name, int yeardb){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(YEARDB, yeardb);
        return sqLiteDatabase.insert(TABLE_NAME, null, values);
    }

    public long Update(int id, String name, int yeardb){
        ContentValues values = new ContentValues();
        values.put(ID, ID);
        values.put(NAME, name);
        values.put(YEARDB, yeardb);
        String where = ID + " = " +id;
        return myDB.update(TABLE_NAME,  values, where, null);
    }

    public long Delete(int id){
        String where = ID + " = " +id;
        return myDB.delete(TABLE_NAME, where, null);
    }
    public Cursor DisplayAll(){
        String query = "SELECT * FROM " + TABLE_NAME;
        return  myDB.rawQuery(query, null);
    }
}
