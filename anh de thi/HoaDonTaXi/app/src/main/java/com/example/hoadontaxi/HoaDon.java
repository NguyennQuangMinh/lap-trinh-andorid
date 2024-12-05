package com.example.hoadontaxi;

public class HoaDon {
    private String soXe;
    private double quangDuong;
    private double donGia;
    private double khuyenMai;
    private boolean selected;


    public String getSoXe() {
        return soXe;
    }

    public void setSoXe(String  soXe) {
        this.soXe = soXe;
    }

    public double getQuangDuong() {
        return quangDuong;
    }

    public void setQuangDuong(double quangDuong) {
        this.quangDuong = quangDuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(double khuyenMai) {this.khuyenMai = khuyenMai; }



    public HoaDon(String  soXe, double quangDuong, double donGia, double khuyenMai ) {

        this.soXe = soXe;
        this.quangDuong = quangDuong;
        this.donGia = donGia;
        this.khuyenMai = khuyenMai;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
