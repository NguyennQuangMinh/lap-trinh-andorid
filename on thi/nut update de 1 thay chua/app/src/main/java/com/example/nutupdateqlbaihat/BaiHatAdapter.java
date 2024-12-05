package com.example.nutupdateqlbaihat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BaiHatAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<BaiHat> list;
    public BaiHatAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.item_lv_baihat, null);
        // map
        TextView tvTen = convertView.findViewById(R.id.tvItemTenBH);
        TextView tvCS = convertView.findViewById(R.id.tvItemTenCS);
        TextView tvTL = convertView.findViewById(R.id.tvItemTL);
        BaiHat bh = list.get(position);
        tvTen.setText(bh.getTenBH());
        tvCS.setText((bh.getTenCS()));
        tvTL.setText(bh.getThoiLuong()+"");
        return convertView;
    }
}

