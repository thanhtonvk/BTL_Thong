package com.example.cuahangbanphukien.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuahangbanphukien.API.APIService;
import com.example.cuahangbanphukien.Activity.Adapter.ListGioHangAdapter;
import com.example.cuahangbanphukien.Activity.DatHangActivity;
import com.example.cuahangbanphukien.Models.GioHang;
import com.example.cuahangbanphukien.R;
import com.example.cuahangbanphukien.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gio_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        btn_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DatHangActivity.class));
            }
        });
    }

    ListView lv_giohang;
    Button btn_refresh, btn_dathang;
    ListGioHangAdapter adapter;

    private void initView(View view) {
        lv_giohang = view.findViewById(R.id.lv_giohang);
        btn_refresh = view.findViewById(R.id.btn_refresh);
        adapter = new ListGioHangAdapter(getContext(), gioHangList);
        lv_giohang.setAdapter(adapter);
        btn_dathang = view.findViewById(R.id.btn_dathang);
    }

    List<GioHang> gioHangList = new ArrayList<>();

    private void loadData() {
        gioHangList.clear();
        APIService.gioHangAPI.getGioHangs(Common.taiKhoan.getTenTaiKhoan()).enqueue(new Callback<List<GioHang>>() {
            @Override
            public void onResponse(Call<List<GioHang>> call, Response<List<GioHang>> response) {
                if (response.body() != null) {
                    for (GioHang gioHang : response.body()) {
                        gioHangList.add(gioHang);
                        adapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<GioHang>> call, Throwable t) {

            }
        });
    }


}