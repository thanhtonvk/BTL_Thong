package com.example.cuahangbanphukien.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuahangbanphukien.API.APIService;
import com.example.cuahangbanphukien.Activity.Adapter.ListGioHangAdapter;
import com.example.cuahangbanphukien.Models.DonHang;
import com.example.cuahangbanphukien.Models.GioHang;
import com.example.cuahangbanphukien.R;
import com.example.cuahangbanphukien.Utils.Common;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatHangActivity extends AppCompatActivity {
    TwoWayView lv_giohang;
    Button btn_dathang;
    ListGioHangAdapter adapter;
    EditText edt_diachi, edt_sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang);
        initView();
        loadData();
        btn_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(DatHangActivity.this);
                dialog.setTitle("Đang đặt hàng");
                dialog.show();
                DonHang donHang = new DonHang(Common.taiKhoan.getTenTaiKhoan(), edt_diachi.getText().toString(), edt_sdt.getText().toString(), "0");
                APIService.donHangAPI.themDonHang(donHang).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() != null) {
                            APIService.gioHangAPI.xoaAllGioHang(Common.taiKhoan.getTenTaiKhoan()).enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if (response.body() != null) {
                                        dialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Đặt hàng thành công", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    dialog.dismiss();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void initView() {
        lv_giohang = findViewById(R.id.lv_giohang);
        adapter = new ListGioHangAdapter(getApplicationContext(), gioHangList);
        lv_giohang.setAdapter(adapter);
        btn_dathang = findViewById(R.id.btn_dathang);
        edt_diachi = findViewById(R.id.edt_diachinhan);
        edt_sdt = findViewById(R.id.edt_sdt);
    }

    List<GioHang> gioHangList = new ArrayList<>();

    private void loadData() {
        APIService.gioHangAPI.getGioHangs(Common.taiKhoan.getTenTaiKhoan()).enqueue(new Callback<List<GioHang>>() {
            @Override
            public void onResponse(Call<List<GioHang>> call, Response<List<GioHang>> response) {
                gioHangList.clear();
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