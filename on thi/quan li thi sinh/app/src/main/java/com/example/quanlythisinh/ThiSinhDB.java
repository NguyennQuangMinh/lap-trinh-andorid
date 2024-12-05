package com.example.quanlythisinh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ThiSinhDB extends SQLiteOpenHelper {

    private String tableName = "tbl_thisinh";
    private String col_soBD = "soBD";
    private String col_tenTS = "tenTS";
    private String col_diemToan = "diemToan";
    private String col_diemLy = "diemLy";
    private String col_diemHoa = "diemHoa";

    public ThiSinhDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = String.format("Create table if not exists %s (%s Text Primary key, %s Text,%s Double,%s Double, %s Double);", tableName, col_soBD, col_tenTS, col_diemToan, col_diemLy, col_diemHoa);

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        //Tạo lại
        onCreate(db);
    }

    public void themThiSinh(ThiSinh thiSinh) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Kiểm tra xem giá trị soBD đã tồn tại hay chưa
        if (!kiemTraSoBDTonTai(db, thiSinh.getSoBaoDanh())) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(col_soBD, thiSinh.getSoBaoDanh());
            contentValues.put(col_tenTS, thiSinh.getHoTen());
            contentValues.put(col_diemToan, thiSinh.getDiemToan());
            contentValues.put(col_diemLy, thiSinh.getDiemLy());
            contentValues.put(col_diemHoa, thiSinh.getDiemHoa());

            db.insert(tableName, null, contentValues);
        } else {
            // Xử lý trường hợp soBD đã tồn tại
        }

        db.close();
    }

    private boolean kiemTraSoBDTonTai(SQLiteDatabase db, String soBD) {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + col_soBD + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{soBD});
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


    public boolean xoaThiSinh(String soBD) {
        String sql =  "delete from " + tableName + " where " + col_soBD + " = '" + soBD + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        try {

            db.execSQL(sql);
        } catch (SQLException e) {
            return false;
        }

        db.close();
        return true;
    }

    public boolean suaThiSinh(ThiSinh thiSinh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_soBD, thiSinh.getSoBaoDanh());
        contentValues.put(col_tenTS, thiSinh.getHoTen());
        contentValues.put(col_diemToan, thiSinh.getDiemToan());
        contentValues.put(col_diemLy, thiSinh.getDiemLy());
        contentValues.put(col_diemHoa, thiSinh.getDiemHoa());

        try {
            // Thực hiện cập nhật thông tin thí sinh, trả về số bản ghi đã được cập nhật
            int rowsAffected = db.update(tableName, contentValues, col_soBD + " = ?", new String[]{thiSinh.getSoBaoDanh()});
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            db.close();
        }
    }






    public List<ThiSinh> danhSachThiSinh() {

        List<ThiSinh> dsThiSinh = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + tableName + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                dsThiSinh.add(new ThiSinh(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4)
                ));
            }
        }

        return dsThiSinh;
    }
}

