package com.example.hoadontaxi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDB extends SQLiteOpenHelper {

    private static String tableName = "tbl_hoadon";
    private static String col_soXe = "soXe";
    private String col_quangDuong = "quangDuong";
    private String col_donGia = "donGia";
    private String col_khuyenMai = "khuyenMai";


    public HoaDonDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = String.format("Create table if not exists %s (%s Text Primary key, %s Double,%s Double,%s Double);",
                tableName, col_soXe, col_quangDuong, col_donGia, col_khuyenMai );

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
        if (!kiemTraSoXeTonTai(db, hoaDon.getSoXe())) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(col_soXe, hoaDon.getSoXe());
            contentValues.put(col_quangDuong, hoaDon.getQuangDuong());
            contentValues.put(col_donGia, hoaDon.getDonGia());
            contentValues.put(col_khuyenMai, hoaDon.getKhuyenMai());


            db.insert(tableName, null, contentValues);
        } else {
            // Xử lý trường hợp soBD đã tồn tại
        }

        db.close();
    }

    private boolean kiemTraSoXeTonTai(SQLiteDatabase db, String soXe) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + col_soXe + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{soXe});
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

    public boolean xoaKhachHang(String soXe) {
        String sql =  "delete from " + tableName + " where " + col_soXe + " = '" + soXe + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        try {

            db.execSQL(sql);
        } catch (SQLException e) {
            return false;
        }

        db.close();
        return true;
    }

    public boolean suaKhachHang(HoaDon hoaDon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_soXe, hoaDon.getSoXe());
        contentValues.put(col_quangDuong, hoaDon.getQuangDuong());
        contentValues.put(col_donGia, hoaDon.getDonGia());
        contentValues.put(col_khuyenMai, hoaDon.getKhuyenMai());


        try {
            // Thực hiện cập nhật thông tin thí sinh, trả về số bản ghi đã được cập nhật
            int rowsAffected = db.update(tableName, contentValues, col_soXe + " = ?", new String[]{hoaDon.getSoXe()});
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            db.close();
        }
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
                        cursor.getDouble(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3)
                ));
            }
        }

        return dsKhachHang;
    }
}
