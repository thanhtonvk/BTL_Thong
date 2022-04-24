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
import com.example.cuahangbanphukien.Models.LoaiSanPham;
import com.example.cuahangbanphukien.R;

import java.util.List;

public class ListLoaiSPAdapter extends ArrayAdapter<LoaiSanPham> {
    List<LoaiSanPham> loaiSanPhamList;

    public ListLoaiSPAdapter(@NonNull Context context, List<LoaiSanPham> loaiSanPhamList) {
        super(context, 0, loaiSanPhamList);
        this.loaiSanPhamList = loaiSanPhamList;
    }

    @Override
    public int getCount() {
        return loaiSanPhamList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hangsx, parent, false);
        }
        LoaiSanPham loaiSanPham = loaiSanPhamList.get(position);
        ImageView imageView = convertView.findViewById(R.id.image);
        TextView tv_tenhang = convertView.findViewById(R.id.tv_ten);
        tv_tenhang.setText(loaiSanPham.getTenLoai());
        if (getContext() != null) {
            Glide.with(getContext()).load(loaiSanPham.getHinhAnh()).into(imageView);
        }
        return convertView;
    }
}
