package com.example.de1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BaiHatAdapter extends ArrayAdapter<DSBaiHat> {
    public BaiHatAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public BaiHatAdapter(@NonNull Context context, int resource, @NonNull List<DSBaiHat> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null)
        {
            LayoutInflater vi=LayoutInflater.from(getContext());
            v=vi.inflate(R.layout.item_bai_hat, null);
        }
        DSBaiHat bh=getItem(position);
        if(bh!=null)
        {
            TextView textViewBaiHat =(TextView) v.findViewById(R.id.textViewBaiHat);
            TextView textViewThoiGian=(TextView) v.findViewById(R.id.textViewThoiGian);
            TextView textViewTacGia=(TextView) v.findViewById(R.id.textViewTacGia);

            textViewBaiHat.setText(bh.BaiHat);
            textViewThoiGian.setText(bh.ThoiGian);
            textViewTacGia.setText(bh.TacGia);
        }
        return v;
    }
}
