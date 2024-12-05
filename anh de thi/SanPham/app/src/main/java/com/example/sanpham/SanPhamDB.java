package com.example.sanpham;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDB extends SQLiteOpenHelper {

    private String tableName = "tbl_sanpham";

    private String col_tenSP = "tenSP";
    private String col_giaTien = "giaTien";
    private String col_sW= "sW";



    public SanPhamDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = String.format("Create table if not exists %s (%s Text Primary key,%s Double, %s Text );", tableName,col_tenSP, col_giaTien, col_sW );

        db.execSQL(sqlCreate);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        //Tạo lại
        onCreate(db);
    }

    public void themSanPham(SanPham sanPham) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_tenSP, sanPham.getTenSP());
        contentValues.put(col_giaTien, sanPham.getGiaTien());
        contentValues.put(col_sW, sanPham.getSW());

        db.insert(tableName, null, contentValues);
        db.close();
    }


    public boolean xoaSanPham(String tenSP) {
        String sql =  "delete from " + tableName + " where " + col_tenSP + " = '" + tenSP + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        try {

            db.execSQL(sql);
        } catch (SQLException e) {
            return false;
        }
        db.close();
        return true;
    }

    public boolean suaSanPham(SanPham sanPham) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_tenSP, sanPham.getTenSP());
        contentValues.put(col_giaTien, sanPham.getGiaTien());
        contentValues.put(col_sW, sanPham.getSW());

        try {
            // Thực hiện cập nhật thông tin thí sinh, trả về số bản ghi đã được cập nhật
            int rowsAffected = db.update(tableName, contentValues, col_tenSP + " = ?", new String[]{sanPham.getTenSP()});
            return rowsAffected > 0;
        }  catch (SQLException e) {
            return false;
        } finally {
            db.close();
        }
    }


    public List<SanPham> danhSachSanPham() {

        List<SanPham> dsSanPham = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + tableName + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                dsSanPham.add(new SanPham(
                        cursor.getString(0),
                        cursor.getDouble(1),
                        cursor.getString(2)
                ));
            }
        }

        return dsSanPham;
    }
}
