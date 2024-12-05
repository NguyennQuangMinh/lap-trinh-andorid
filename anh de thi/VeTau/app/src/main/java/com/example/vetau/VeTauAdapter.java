package com.example.vetau;


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

public class VeTauAdapter extends BaseAdapter {

    List<VeTau> dsVeTau;

    Activity activity;

    public VeTauAdapter(List<VeTau> dsVeTau, Activity activity) {
        this.dsVeTau = dsVeTau;
        this.activity = activity;

    }


    @Override
    public int getCount() {
        return dsVeTau.size();
    }

    @Override
    public Object getItem(int position) {
        return dsVeTau.get(position);
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
            convertView = activity.getLayoutInflater().inflate(R.layout.item_vetau, null);
            viewHolder = new ViewHolder();
            viewHolder.txtGaDi = convertView.findViewById(R.id.txtGaDi);
            viewHolder.txtGaDen = convertView.findViewById(R.id.txtGaDen);
            viewHolder.txtChieuDi = convertView.findViewById(R.id.txtChieuDi);
            viewHolder.txtTongGia = convertView.findViewById(R.id.txtTongGia);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        VeTau veTau = (VeTau) getItem(position);

        viewHolder.txtGaDi.setText(veTau.getGaDi());
        viewHolder.txtGaDen.setText(veTau.getGaDen());
        viewHolder.txtChieuDi.setText(veTau.getChieuDi());

        double tong;
        if ("Một Chiều".equals(viewHolder.txtChieuDi.getText())) {
            tong = veTau.getDonGia();
        } else if ("Khứ Hồi".equals(viewHolder.txtChieuDi.getText())) {
            tong = veTau.getDonGia() * 2 * 0.95;
        } else {
            tong = 0; // Default value if neither "Một Chiều" nor "Khứ Hồi"
        }

        viewHolder.txtTongGia.setText(String.valueOf(tong));

        return convertView;
    }


    public void filterList(List<VeTau> filteredList) {
        dsVeTau = new ArrayList<>(filteredList);
        notifyDataSetChanged();
    }
    //Sử dụng sự kiện xóa thì mở lại hàm này
    public void removeItem(int position) {
        dsVeTau.remove(position);
        notifyDataSetChanged();
    }
    private static class ViewHolder {

        TextView txtGaDi;
        TextView txtGaDen;
        TextView txtChieuDi;
        TextView txtTongGia;
    }
}