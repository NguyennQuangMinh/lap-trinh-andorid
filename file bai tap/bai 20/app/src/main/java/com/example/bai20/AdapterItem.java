package com.example.bai20;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;

public class AdapterItem extends BaseAdapter {
    private ArrayList<MainActivity2> data;
    private Activity context;
    private LayoutInflater inflater;

    public AdapterItem(ArrayList<MainActivity2> data, Activity activity) {
        this.data = data;
        this.context = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.activity_main2, null);
        }

        ImageView imgProfile = v.findViewById(R.id.imageView);
        TextView tvName = v.findViewById(R.id.tvName);
        tvName.setText(data.get(position).getName());

        TextView description = v.findViewById(R.id.tvDescription);
        description.setText(data.get(position).getDescription());

        Switch sw = v.findViewById(R.id.sw);
        sw.setChecked(data.get(position).isStatus());

        return v;
    }
}
