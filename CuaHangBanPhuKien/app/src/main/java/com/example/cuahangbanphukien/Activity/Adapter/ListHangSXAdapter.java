package com.example.cuahangbanphukien.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.cuahangbanphukien.Models.HangSanXuat;
import com.example.cuahangbanphukien.R;

import java.util.List;

public class ListHangSXAdapter extends ArrayAdapter<HangSanXuat> {
    List<HangSanXuat> hangSanXuatList;

    public ListHangSXAdapter(@NonNull Context context, List<HangSanXuat> hangSanXuatList) {
        super(context, 0, hangSanXuatList);
        this.hangSanXuatList = hangSanXuatList;
    }

    @Override
    public int getCount() {
        return hangSanXuatList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hangsx, parent, false);
        }
        HangSanXuat hangSanXuat = hangSanXuatList.get(position);
        ImageView imageView = convertView.findViewById(R.id.image);
        TextView tv_tenhang = convertView.findViewById(R.id.tv_ten);
        tv_tenhang.setText(hangSanXuat.getTenHang());
        if (getContext() != null) {
            Glide.with(getContext()).load(hangSanXuat.getHinhAnh()).into(imageView);
        }
        return convertView;
    }
}
