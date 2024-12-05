package com.example.vetau;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class VeTauDB extends SQLiteOpenHelper {

    private String tableName = "tbl_vetau";
    private String col_gaDi = "gaDi";
    private String col_gaDen = "gaDen";
    private String col_donGia = "donGia";
    private String col_chieuDi = "chieuDi";


    public VeTauDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = String.format("Create table if not exists %s (%s Text Primary key, %s Text,%s Double,%s Text);", tableName,col_gaDi, col_gaDen, col_donGia, col_chieuDi);

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        //Tạo lại
        onCreate(db);
    }

    public void themVeTau(VeTau veTau) {
        SQLiteDatabase db = this.getWritableDatabase();
        String existingGaDi = null;
        Cursor cursor = db.query(
                "tbl_vetau",
                null,
                "gaDi = ?",
                new String[]{veTau.getGaDi()},
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            existingGaDi = cursor.getString(cursor.getColumnIndexOrThrow("gaDi"));
        }
        cursor.close();

        if (existingGaDi == null) {
            // Insert the new VeTau
            ContentValues values = new ContentValues();
            values.put("chieuDi", veTau.getChieuDi());
            values.put("gaDen", veTau.getGaDen());
            values.put("gaDi", veTau.getGaDi());
            values.put("donGia", veTau.getDonGia());
            db.insert("tbl_vetau", null, values);
        } else {
            // Handle the duplicate (e.g., show an error message)
            Log.e("VeTauDB", "Duplicate gaDi: " + veTau.getGaDi());
        }
        db.close();
    }

    public boolean xoaVeTau(String gaDi) {
        String sql =  "delete from " + tableName + " where " + col_gaDi + " = '" + gaDi + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        try {

            db.execSQL(sql);
        } catch (SQLException e) {
            return false;
        }
        db.close();
        return true;
    }

    public boolean suaVeTau(VeTau veTau) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_gaDi, veTau.getGaDi());
        contentValues.put(col_gaDen, veTau.getGaDen());
        contentValues.put(col_donGia, veTau.getDonGia());
        contentValues.put(col_chieuDi, veTau.getChieuDi());
        try {
            // Thực hiện cập nhật thông tin thí sinh, trả về số bản ghi đã được cập nhật
            int rowsAffected = db.update(tableName, contentValues, col_gaDi + " = ?", new String[]{veTau.getGaDi()});
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            db.close();
        }
    }
    public List<VeTau> danhSachVeTau() {

        List<VeTau> dsVeTau = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + tableName + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                dsVeTau.add(new VeTau(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getString(3)
                ));
            }
        }

        return dsVeTau;
    }
}


