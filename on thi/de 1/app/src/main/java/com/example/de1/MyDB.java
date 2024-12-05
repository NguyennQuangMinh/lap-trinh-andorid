package com.example.de1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {
    private static final String DBNAME="test.db";
    private static final int VERSION=1;
    private static final String TABLE_NAME="DSBAIHAT";

    private static String ID="_id"; // bắt buộc có
    // tương ứng với đề bài
    private static String BAIHAT="baihat";
    private static String THOIGIAN="thoigian";
    private static String TACGIA="tacgia";

    private SQLiteDatabase mydb;

    public MyDB(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // nhớ để ý dấu cách
        // string: text, int: integer, double: real
        // id: giữ nguyên
        String createTable ="CREATE TABLE " +TABLE_NAME+"( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+BAIHAT+ " TEXT, "+THOIGIAN+" TEXT, "+TACGIA+" TEXT)";
        db.execSQL(createTable);
    }

    public static String getBAIHAT() {
        return BAIHAT;
    }

    public static String getID() {
        return ID;
    }

    public static String getTHOIGIAN() {
        return THOIGIAN;
    }

    public static String getTACGIA() {
        return TACGIA;
    }

    public void openDb()
    {
        mydb=getWritableDatabase();
    }
    public void closeDb()
    {
        if(mydb!=null&&mydb.isOpen())
            mydb.close();
    }
    public Cursor get()
    {
        String query="SELECT * FROM "+TABLE_NAME;
        return mydb.rawQuery(query, null);
    }
    public long insert(String baihat, String thoigian, String tacgia)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(BAIHAT, baihat);
        value.put(THOIGIAN, thoigian);
        value.put(TACGIA, tacgia);
        return db.insert(TABLE_NAME, null, value);
    }
    public long delete(Integer id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues value=new ContentValues();
        String where= ID+" = " + id;
        return db.delete(TABLE_NAME, where, null);
    }
}
