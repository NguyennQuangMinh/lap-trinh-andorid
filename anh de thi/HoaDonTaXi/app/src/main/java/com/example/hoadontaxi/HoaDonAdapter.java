package com.example.hoadontaxi;

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
            viewHolder.txtQuangDuong = convertView.findViewById(R.id.txtQuangDuong);
            viewHolder.txtSoXe =  convertView.findViewById(R.id.txtSoXe);
            viewHolder.txtTongGia = convertView.findViewById(R.id.txtTongGia);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HoaDon hoaDon = (HoaDon) getItem(position);

        viewHolder.txtQuangDuong.setText(String.valueOf(hoaDon.getQuangDuong()));
        viewHolder.txtSoXe.setText(hoaDon.getSoXe());

        Double tong = (hoaDon.getDonGia() * hoaDon.getQuangDuong()*((100- hoaDon.getKhuyenMai())/100));
        viewHolder.txtTongGia.setText(String.valueOf(tong));





        return convertView;
    }

    public void filterList(List<HoaDon> filteredList) {
        dsHoaDon = new ArrayList<>(filteredList);
        notifyDataSetChanged();
    }
    //Sử dụng sự kiện xóa thì mở lại hàm này
    public void removeItem(int position) {
        dsHoaDon.remove(position);
        notifyDataSetChanged();
    }


    private static class ViewHolder {

        TextView txtQuangDuong, txtSoXe, txtTongGia;
    }
}
