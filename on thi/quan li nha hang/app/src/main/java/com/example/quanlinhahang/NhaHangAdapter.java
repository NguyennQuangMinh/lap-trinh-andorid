package com.example.quanlinhahang;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NhaHangAdapter extends BaseAdapter {

    private List<NhaHang> dsNhaHang;
    private Activity activity;

    public NhaHangAdapter(List<NhaHang> dsNhaHang, Activity activity) {
        this.dsNhaHang = dsNhaHang;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return dsNhaHang.size();
    }

    @Override
    public Object getItem(int position) {
        return dsNhaHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.item_nhahang, null);
            viewHolder = new ViewHolder();
            viewHolder.txtTenNhaHang = convertView.findViewById(R.id.txtTenNhaHang);
            viewHolder.txtDiaChi = convertView.findViewById(R.id.txtDiaChi);
            viewHolder.txtDanhGia = convertView.findViewById(R.id.txtDanhGia);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NhaHang nh = (NhaHang) getItem(position);

        viewHolder.txtTenNhaHang.setText(nh.getTenNhaHang());
        viewHolder.txtDiaChi.setText(nh.getDiaChi());
        viewHolder.txtDanhGia.setText(nh.getDanhGia());

        return convertView;
    }

    private static class ViewHolder {
        TextView txtTenNhaHang, txtDiaChi, txtDanhGia;
    }
}
