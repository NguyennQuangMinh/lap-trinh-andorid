package com.example.hoadonkhachsan;

public class HoaDon {
    private String soPhong;
    private String hoTen;
    private double donGia;
    private double soNgayLuuTru;
    private boolean selected;


    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String  soPhong) {
        this.soPhong = soPhong;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getSoNgayLuuTru() {
        return soNgayLuuTru;
    }

    public void setSoNgayLuuTru(double soNgayLuuTru) {this.soNgayLuuTru = soNgayLuuTru; }



    public HoaDon(String  soPhong, String hoTen, double donGia, double soNgayLuuTru ) {

        this.soPhong = soPhong;
        this.hoTen = hoTen;
        this.donGia = donGia;
        this.soNgayLuuTru = soNgayLuuTru;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
