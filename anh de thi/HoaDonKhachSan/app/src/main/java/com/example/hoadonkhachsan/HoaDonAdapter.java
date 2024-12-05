package com.example.hoadonkhachsan;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter {

    List<HoaDon> dsHoaDon;

    Activity activity;

    public HoaDonAdapter(List<HoaDon> dsHoaDon, Activity activity) {
        this.dsHoaDon = dsHoaDon;
        this.activity = activity;

    }


    @Override
    public int getCount() {
        return dsHoaDon.size();
    }

    @Override
    public Object getItem(int position) {
        return dsHoaDon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.item_hoadon, null);
            viewHolder = new ViewHolder();
            viewHolder.txtHoTen = convertView.findViewById(R.id.txtHoTen);
            viewHolder.txtSoPhong =  convertView.findViewById(R.id.txtSoPhong);
            viewHolder.txtTongGia = convertView.findViewById(R.id.txtTongGia);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HoaDon hoaDon = (HoaDon) getItem(position);

        viewHolder.txtHoTen.setText(hoaDon.getHoTen());
        viewHolder.txtSoPhong.setText(hoaDon.getSoPhong());

        Double tong = (hoaDon.getDonGia() * hoaDon.getSoNgayLuuTru());
        viewHolder.txtTongGia.setText(String.valueOf(tong));

        return convertView;
    }

    private static class ViewHolder {

        TextView txtHoTen, txtSoPhong, txtTongGia;
    }
}
