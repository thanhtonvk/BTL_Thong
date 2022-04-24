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
import com.example.cuahangbanphukien.Models.SanPham;
import com.example.cuahangbanphukien.R;
import com.example.cuahangbanphukien.Utils.Common;

import java.util.List;

public class ListSanPhamAdapter extends ArrayAdapter<SanPham> {
    List<SanPham> sanPhamList;

    public ListSanPhamAdapter(@NonNull Context context, List<SanPham> sanPhamList) {
        super(context, 0, sanPhamList);
        this.sanPhamList = sanPhamList;
    }

    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sanpham, parent, false);
        }
        SanPham sanPham = sanPhamList.get(position);
        ImageView img = convertView.findViewById(R.id.image);
        TextView tv_tensp = convertView.findViewById(R.id.tv_tensanpham);
        TextView tv_giaban = convertView.findViewById(R.id.tv_giaban);
        tv_giaban.setText(Common.formatMoney(sanPham.getGiaBan()));
        tv_tensp.setText(sanPham.getTenSP());
        if (getContext() != null) {
            Glide.with(getContext()).load(sanPham.getHinhAnh()).into(img);
        }
        return convertView;

    }
}
