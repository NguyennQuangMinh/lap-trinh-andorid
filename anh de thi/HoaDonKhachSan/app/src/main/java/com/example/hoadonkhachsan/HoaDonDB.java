package com.example.hoadonkhachsan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDB extends SQLiteOpenHelper {

    private String tableName = "tbl_hoadon";
    private String col_soPhong = "soPhong";
    private String col_tenKH = "tenKH";
    private String col_donGia = "donGia";
    private String col_soNgayLuuTru = "soNgayLuuTru";


    public HoaDonDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = String.format("Create table if not exists %s (%s Text Primary key, %s Text,%s Double,%s Double);",
                tableName, col_soPhong, col_tenKH, col_donGia, col_soNgayLuuTru );

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        //Tạo lại
        onCreate(db);
    }

    public void themKhachHang(HoaDon hoaDon) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Kiểm tra xem giá trị soBD đã tồn tại hay chưa
        if (!kiemTraSoPhongTonTai(db, hoaDon.getSoPhong())) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(col_soPhong, hoaDon.getSoPhong());
            contentValues.put(col_tenKH, hoaDon.getHoTen());
            contentValues.put(col_donGia, hoaDon.getDonGia());
            contentValues.put(col_soNgayLuuTru, hoaDon.getSoNgayLuuTru());


            db.insert(tableName, null, contentValues);
        } else {
            // Xử lý trường hợp soBD đã tồn tại
        }

        db.close();
    }

    private boolean kiemTraSoPhongTonTai(SQLiteDatabase db, String soPhong) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + col_soPhong + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{soPhong});
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                int count = cursor.getInt(0);
                return count > 0;
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    public boolean xoaKhachHang(String soPhong) {
        String sql =  "delete from " + tableName + " where " + col_soPhong + " = '" + soPhong + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        try {

            db.execSQL(sql);
        } catch (SQLException e) {
            return false;
        }

        db.close();
        return true;
    }


    public List<HoaDon> danhSachKhachHang() {

        List<HoaDon> dsKhachHang = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + tableName + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                dsKhachHang.add(new HoaDon(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3)
                ));
            }
        }

        return dsKhachHang;
    }
}

