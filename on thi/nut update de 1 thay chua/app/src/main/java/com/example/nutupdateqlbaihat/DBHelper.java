package com.example.nutupdateqlbaihat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static String DBName = "songdb1.db";
    private int Version = 1;
    private String Table_Name = "Song";
    private String ID = "_id";
    private String TenBH = "_tenbh";
    private String TenCS = "_tencs";
    private String TL = "_tl";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBName, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "Create table "+Table_Name+" ("+ID+" integer  primary key autoincrement not null," +
                ""+TenBH+" TEXT not null," +
                " "+TenCS+" TEXT not null," +
                " "+TL+" float)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(BaiHat bh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(ID, bh.getMaBH());
        values.put(TenBH, bh.getTenBH());
        values.put(TenCS, bh.getTenCS());
        values.put(TL, bh.getThoiLuong());
        db.insert(Table_Name, null, values);
    }
    public List<BaiHat> getAll(){
        List<BaiHat> lst = new ArrayList<>();
        String qr = "Select *from "+Table_Name;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(qr, null);
        while (cursor.moveToNext()){
            BaiHat bh = new BaiHat();
            bh.setMaBH(cursor.getInt(0));
            bh.setTenCS(cursor.getString(1));
            bh.setTenBH(cursor.getString(2));
            bh.setThoiLuong(cursor.getFloat(3));
            lst.add(bh);
        }
        return lst;
    }
    public void update(BaiHat bh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, bh.getMaBH());
        values.put(TenBH, bh.getTenBH());
        values.put(TenCS, bh.getTenCS());
        values.put(TL, bh.getThoiLuong());
        String where = ID+" = "+bh.getMaBH();
        db.update(Table_Name, values, where, null);
    }
    public void deleteById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = ID+" = "+id;
        db.delete(Table_Name, where, null);
        //db.execSQL("delete from "+Table_Name+" where "+ID+" = "+id);
    }
}

