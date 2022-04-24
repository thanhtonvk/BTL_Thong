package com.example.cuahangbanphukien.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.cuahangbanphukien.API.APIService;
import com.example.cuahangbanphukien.Models.GioHang;
import com.example.cuahangbanphukien.R;
import com.example.cuahangbanphukien.Utils.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListGioHangAdapter extends ArrayAdapter<GioHang> {

    List<GioHang> gioHangList;

    public ListGioHangAdapter(@NonNull Context context, List<GioHang> gioHangList) {
        super(context, 0, gioHangList);
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_giohang, parent, false);
        }
        ImageView img = convertView.findViewById(R.id.image);
        TextView tv_tensp = convertView.findViewById(R.id.tv_tensanpham);
        TextView tv_giaban = convertView.findViewById(R.id.tv_giaban);
        Button btn_giam = convertView.findViewById(R.id.btn_giam);
        Button btn_tang = convertView.findViewById(R.id.btn_tang);
        EditText edt_soluong = convertView.findViewById(R.id.edt_soluong);
        GioHang gioHang = gioHangList.get(position);
        tv_tensp.setText(gioHang.getTenChiTiet());
        tv_giaban.setText(Common.formatMoney(gioHang.getGiaBan()));
        edt_soluong.setText(gioHang.getSoLuong() + "");
        Glide.with(getContext()).load(gioHang.getChiTietSanPham1().getHinhAnh()).into(img);
        btn_giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(edt_soluong.getText().toString()) == 1) {
                    APIService.gioHangAPI.xoaGioHang(gioHang.getID()).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.body() != null) {
                                gioHangList.remove(position);
                                notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                } else {
                    int soluong = Integer.parseInt(edt_soluong.getText().toString());
                    soluong--;
                    edt_soluong.setText(soluong + "");
                    APIService.gioHangAPI.updateGioHang(gioHang.getID(), soluong).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            }
        });
        btn_tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong = Integer.parseInt(edt_soluong.getText().toString());
                soluong++;
                edt_soluong.setText(soluong + "");
                APIService.gioHangAPI.updateGioHang(gioHang.getID(), soluong).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
        return convertView;
    }
}
