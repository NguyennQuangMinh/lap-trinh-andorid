package com.example.quanlinhahang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBNhaHang extends SQLiteOpenHelper {

    private String tableName = "tbl_nhahang";
    private String col_maNH = "maNH";
    private String col_tenNH = "tenNH";
    private String col_diaChi = "diaChi";
    private String col_danhGia = "danhGia";

    DBNhaHang(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = String.format("Create table if not exists %s (%s Text Primary key, %s Text,%s Text,%s Text);", tableName, col_maNH, col_tenNH, col_diaChi, col_danhGia);

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        //Tạo lại
        onCreate(db);
    }

    public boolean themNhaHang(NhaHang nhaHang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_maNH, nhaHang.getMaNhaHang());
        contentValues.put(col_tenNH, nhaHang.getTenNhaHang());
        contentValues.put(col_diaChi, nhaHang.getDiaChi());
        contentValues.put(col_danhGia, nhaHang.getDanhGia());

        long check = db.insert(tableName, null, contentValues);

        if (check == -1) {

            return false;
        }

        db.close();
        return true;
    }

    public boolean xoaNhaHang(String maNH) {
        String sql =  "delete from " + tableName + " where " + col_maNH + " = '" + maNH + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        try {

            db.execSQL(sql);
        } catch (SQLException e) {
            return false;
        }

        db.close();
        return true;
    }

    public List<NhaHang> layDanhSach() {

        List<NhaHang> dsNhaHang = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + tableName + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                dsNhaHang.add(new NhaHang(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            }
        }

        return dsNhaHang;
    }
}