package com.example.de1thaychua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="song";
    private static final String TABLE_NAME ="Baihat";
    private static final String ID ="ID";
    private static final String NAME ="NAME";
    private static final String SINGER ="SINGER";
    private static final String TIME ="TIME";


    private Context context;

    public Database(Context context) {
        super(context, DATABASE_NAME,null, 1);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                ID +" integer primary key  , "+
                NAME + " TEXT, "+
                SINGER + " TEXT, "+
                TIME +" float)";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public  void xoaToanBo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from Baihat");
    }


    //Add new a student
    public void addSong(Baihat baihat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, baihat.getName());
        values.put(SINGER, baihat.getSinger());
        values.put(TIME, baihat.getTime());

        db.insert(TABLE_NAME,null,values);

        db.close();
    }


    public void QueryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }


    public List<Baihat> getAll() {
        List<Baihat> listSong = new ArrayList<Baihat>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME  ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Baihat baihat = new Baihat();
                baihat.setID(cursor.getInt(0));
                baihat.setName(cursor.getString(1));
                baihat.setSinger(cursor.getString(2));
                baihat.setTime(cursor.getFloat(3));
                listSong.add(baihat);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listSong;
    }

}
