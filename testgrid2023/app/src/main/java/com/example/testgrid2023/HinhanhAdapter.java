package com.example.testgrid2023;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class HinhanhAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Hinhanh> hinhanhList;

    public HinhanhAdapter(Context context, int layout, List<Hinhanh> hinhanhList) {
        this.context = context;
        this.layout = layout;
        this.hinhanhList = hinhanhList;
    }

    @Override
    public int getCount() {
        return hinhanhList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    class ViewHolder{
        ImageView imageViewHinh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // gan layout cho no
            view = inflater.inflate(layout, null);
            // anh xa
            holder.imageViewHinh = (ImageView) view.findViewById(R.id.imageView);
            // lay anh xa
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        // lay vi tri
        Hinhanh hinhanh = hinhanhList.get(i);
        holder.imageViewHinh.setImageResource(hinhanh.getHinh());
        return view;
    }
}
