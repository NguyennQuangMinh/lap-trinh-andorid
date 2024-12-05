package com.example.bai10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    public SinhVienAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List<SinhVien> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View y = convertView;
        if( y == null){
            LayoutInflater yi= LayoutInflater.from(getContext());
            y= yi.inflate(R.layout.item, null);
        }
        SinhVien p = getItem(position);
        if( p!= null){
            TextView tv1 = (TextView) y.findViewById(R.id.textViewHoten);
            TextView tv2 = (TextView) y.findViewById(R.id.textViewNamSinh);
            tv1.setText(p.Hoten);
            tv2.setText(String.valueOf(p.Namsinh));
        }
        return y;
    }

}
