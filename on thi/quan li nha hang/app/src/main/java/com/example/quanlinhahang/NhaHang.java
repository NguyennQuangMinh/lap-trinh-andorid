package com.example.quanlinhahang;

public class NhaHang {
    private String maNhaHang;
    private String tenNhaHang;
    private String diaChi;
    private String danhGia;

    public String getMaNhaHang() {
        return maNhaHang;
    }

    public void setMaNhaHang(String maNhaHang) {
        this.maNhaHang = maNhaHang;
    }

    public String getTenNhaHang() {
        return tenNhaHang;
    }

    public void setTenNhaHang(String tenNhaHang) {
        this.tenNhaHang = tenNhaHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public NhaHang(String maNhaHang, String tenNhaHang, String diaChi, String danhGia) {
        this.maNhaHang = maNhaHang;
        this.tenNhaHang = tenNhaHang;
        this.diaChi = diaChi;
        this.danhGia = danhGia;
    }
}
