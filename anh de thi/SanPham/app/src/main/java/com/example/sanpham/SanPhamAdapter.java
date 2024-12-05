package com.example.sanpham;

import static java.lang.String.valueOf;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends BaseAdapter {

    List<SanPham> dsSanPham;

    Activity activity;

    public SanPhamAdapter(List<SanPham> dsSanPham, Activity activity) {
        this.dsSanPham = dsSanPham;
        this.activity = activity;

    }


    @Override
    public int getCount() {
        return dsSanPham.size();
    }

    @Override
    public Object getItem(int position) {
        return dsSanPham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Inside getView method
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.item_sanpham, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTenSP = convertView.findViewById(R.id.txtTenSP);
            viewHolder.txtGiaTien = convertView.findViewById(R.id.txtGiaTien);
            viewHolder.txtGiaKhuyenMai = convertView.findViewById(R.id.txtGiaKhuyenMai);
            viewHolder.txtSW=convertView.findViewById(R.id.txtSW);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SanPham sanPham = (SanPham) getItem(position);

        viewHolder.txtTenSP.setText(sanPham.getTenSP());
        viewHolder.txtGiaTien.setText(String.valueOf(sanPham.getGiaTien()));
        viewHolder.txtSW.setText(sanPham.getSW());

        double tong;
        if (" ".equals(viewHolder.txtSW.getText())) {
            tong = 0;
        } else if ("Giảm Giá Còn:".equals(viewHolder.txtSW.getText())) {
            tong = sanPham.getGiaTien() * 0.9;
        } else {
            tong = 0; // Default value if neither "Một Chiều" nor "Khứ Hồi"
        }
        viewHolder.txtGiaKhuyenMai.setText(String.valueOf(tong));

        return convertView;
    }


    public void filterList(List<SanPham> filteredList) {
        dsSanPham = new ArrayList<>(filteredList);
        notifyDataSetChanged();
    }

    private static class ViewHolder {

        TextView txtTenSP;
        TextView txtGiaTien;
        TextView txtGiaKhuyenMai;
        TextView txtSW;

    }
}
