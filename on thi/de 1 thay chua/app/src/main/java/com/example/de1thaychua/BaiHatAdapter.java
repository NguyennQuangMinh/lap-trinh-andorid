package com.example.de1thaychua;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BaiHatAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Baihat> arrayList;

    public BaiHatAdapter(Context context, int layout, ArrayList<Baihat> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);


        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvSinger = (TextView) view.findViewById(R.id.tvSinger);
        TextView tvTime = (TextView) view.findViewById(R.id.tvTime);


        Baihat nhac = arrayList.get(i);
        tvName.setText(nhac.getName());
        tvSinger.setText(nhac.getSinger() );
        tvTime.setText(nhac.getTime()+"");
        return view;
    }
}
