package com.example.vetau;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class VeTau {

    private String gaDi;
    private String gaDen;
    private double donGia;
    private String chieuDi;


    private boolean selected;


    public String getGaDi() {
        return gaDi;
    }

    public void setGaDi(String  gaDi) {
        this.gaDi = gaDi;
    }

    public String getGaDen() {
        return gaDen;
    }

    public void setGaDen(String gaDen) {
        this.gaDen = gaDen;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getChieuDi() {
        return chieuDi;
    }

    public void setChieuDi(String chieuDi) {
        this.chieuDi = chieuDi;
    }





    public VeTau(String  gaDi, String gaDen, double donGia, String chieuDi ) {
        this.gaDi = gaDi;
        this.gaDen = gaDen;
        this.donGia = donGia;
        this.chieuDi = chieuDi;
    }



    // Các getter và setter khác...






    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
