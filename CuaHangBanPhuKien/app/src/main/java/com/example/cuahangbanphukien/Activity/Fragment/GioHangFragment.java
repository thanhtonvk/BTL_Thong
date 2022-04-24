package com.example.cuahangbanphukien.Activity.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.cuahangbanphukien.API.APIService;
import com.example.cuahangbanphukien.Activity.Adapter.ListGioHangAdapter;
import com.example.cuahangbanphukien.Models.GioHang;
import com.example.cuahangbanphukien.R;
import com.example.cuahangbanphukien.Utils.Common;

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
    }

    ListView lv_giohang;
    Button btn_refresh;

    private void initView(View view) {
        lv_giohang = view.findViewById(R.id.lv_giohang);
        btn_refresh = view.findViewById(R.id.btn_refresh);
    }

    List<GioHang> gioHangList;

    private void loadData() {
        APIService.gioHangAPI.getGioHangs(Common.taiKhoan.getTenTaiKhoan()).enqueue(new Callback<List<GioHang>>() {
            @Override
            public void onResponse(Call<List<GioHang>> call, Response<List<GioHang>> response) {
                if (response.body() != null) {
                    gioHangList = response.body();
                    ListGioHangAdapter adapter = new ListGioHangAdapter(getContext(), gioHangList);
                    lv_giohang.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<GioHang>> call, Throwable t) {

            }
        });
    }


}