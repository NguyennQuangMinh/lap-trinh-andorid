package com.example.sanpham;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class SanPham {

    private String tenSP;
    private double giaTien;
    private String sW;




    private boolean selected;


    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String  tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setDonGia(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getSW() {
        return sW;
    }

    public void setSW(String  sW) {
        this.sW = sW;
    }






    public SanPham(String  tenSP, double giaTien, String sW) {
        this.tenSP = tenSP;
        this.giaTien = giaTien;
        this.sW= sW;

    }



    // Các getter và setter khác...






    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}