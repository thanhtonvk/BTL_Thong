package com.example.cuahangbanphukien.Activity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cuahangbanphukien.API.APIService;
import com.example.cuahangbanphukien.Activity.Adapter.ListHangSXAdapter;
import com.example.cuahangbanphukien.Activity.Adapter.ListLoaiSPAdapter;
import com.example.cuahangbanphukien.Activity.Adapter.ListSanPhamAdapter;
import com.example.cuahangbanphukien.Activity.ChiTietSanPhamActivity;
import com.example.cuahangbanphukien.Activity.DSSanPhamActivity;
import com.example.cuahangbanphukien.Models.HangSanXuat;
import com.example.cuahangbanphukien.Models.LoaiSanPham;
import com.example.cuahangbanphukien.Models.SanPham;
import com.example.cuahangbanphukien.R;
import com.example.cuahangbanphukien.Utils.Common;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChuFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }

    AutoCompleteTextView edt_timkiem;
    TwoWayView lv_giamgia, lv_loaiphukien, lv_hangsanxuat;
    GridView gv_sanpham;
    Button btn_timkiem;
    ListSanPhamAdapter sanPhamMoiAdapter;
    ListSanPhamAdapter sanPhamGiamGiaAdapter;
    ListLoaiSPAdapter loaiSPAdapter;
    ListHangSXAdapter hangSXAdapter;
    List<HangSanXuat> hangSanXuatList;
    List<LoaiSanPham> loaiSanPhamList;
    List<String> listTenSanPham = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadAdapter();
        onClick();
    }

    private void initView(View view) {
        edt_timkiem = view.findViewById(R.id.edt_timkiem);
        lv_giamgia = view.findViewById(R.id.lv_giamgia);
        lv_loaiphukien = view.findViewById(R.id.lv_loai);
        lv_hangsanxuat = view.findViewById(R.id.lv_hangsx);
        gv_sanpham = view.findViewById(R.id.gv_sanpham);
        btn_timkiem = view.findViewById(R.id.btn_timkiem);
    }

    private void onClick() {
        btn_timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", edt_timkiem.getText().toString());
                String tuKhoa = edt_timkiem.getText().toString();
                APIService.sanPhamAPI.timKiem(tuKhoa).enqueue(new Callback<List<SanPham>>() {
                    @Override
                    public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                        if (response.body() != null) {
                            Common.sanPhamList = response.body();
                            startActivity(new Intent(getContext(), DSSanPhamActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SanPham>> call, Throwable t) {
                        Log.d("click", t.getMessage());
                    }
                });
            }
        });
        lv_loaiphukien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LoaiSanPham loaiSanPham = loaiSanPhamList.get(i);
                APIService.sanPhamAPI.getSanPhamByLoai(loaiSanPham.getID()).enqueue(new Callback<List<SanPham>>() {
                    @Override
                    public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                        if (response.body() != null) {
                            Common.sanPhamList = response.body();
                            startActivity(new Intent(getContext(), DSSanPhamActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SanPham>> call, Throwable t) {

                    }
                });
            }
        });
        lv_hangsanxuat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HangSanXuat hangSanXuat = hangSanXuatList.get(i);
                APIService.sanPhamAPI.getSanPhamByHSX(hangSanXuat.getID()).enqueue(new Callback<List<SanPham>>() {
                    @Override
                    public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                        if (response.body() != null) {
                            Common.sanPhamList = response.body();
                            startActivity(new Intent(getContext(), DSSanPhamActivity.class));
                        }

                    }

                    @Override
                    public void onFailure(Call<List<SanPham>> call, Throwable t) {

                    }
                });
            }
        });
        gv_sanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.sanPham = sanPhamMoiList.get(i);
                startActivity(new Intent(getContext(), ChiTietSanPhamActivity.class));
            }
        });
        lv_giamgia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.sanPham = sanPhamGiamGiaList.get(i);
                startActivity(new Intent(getContext(), ChiTietSanPhamActivity.class));
            }
        });
    }

    List<SanPham> sanPhamMoiList;
    List<SanPham> sanPhamGiamGiaList;

    private void loadAdapter() {
        APIService.sanPhamAPI.getListSanPhamMoi().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhamMoiList = response.body();
                if (sanPhamMoiList != null) {
                    sanPhamMoiAdapter = new ListSanPhamAdapter(getContext(), sanPhamMoiList);
                    gv_sanpham.setAdapter(sanPhamMoiAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
        APIService.sanPhamAPI.getListGiamGia().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhamGiamGiaList = response.body();
                if (sanPhamGiamGiaList != null) {
                    sanPhamGiamGiaAdapter = new ListSanPhamAdapter(getContext(), sanPhamGiamGiaList);
                    lv_giamgia.setAdapter(sanPhamGiamGiaAdapter);
                } else {
                    Toast.makeText(getContext(), "Loi1", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(getContext(), "Loi2", Toast.LENGTH_LONG).show();
            }
        });
        APIService.sanPhamAPI.getHangSanXuats().enqueue(new Callback<List<HangSanXuat>>() {
            @Override
            public void onResponse(Call<List<HangSanXuat>> call, Response<List<HangSanXuat>> response) {
                hangSanXuatList = response.body();
                if (hangSanXuatList != null) {
                    for (HangSanXuat hangSanXuat : hangSanXuatList
                    ) {
                        listTenSanPham.add(hangSanXuat.getTenHang());
                    }
                    hangSXAdapter = new ListHangSXAdapter(getContext(), hangSanXuatList);
                    lv_hangsanxuat.setAdapter(hangSXAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<HangSanXuat>> call, Throwable t) {

            }
        });
        APIService.sanPhamAPI.getLoaiSanPhams().enqueue(new Callback<List<LoaiSanPham>>() {
            @Override
            public void onResponse(Call<List<LoaiSanPham>> call, Response<List<LoaiSanPham>> response) {
                loaiSanPhamList = response.body();
                if (loaiSanPhamList != null) {
                    for (LoaiSanPham loaiSanPham : loaiSanPhamList) {
                        listTenSanPham.add(loaiSanPham.getTenLoai());
                    }
                    loaiSPAdapter = new ListLoaiSPAdapter(getContext(), loaiSanPhamList);
                    lv_loaiphukien.setAdapter(loaiSPAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<LoaiSanPham>> call, Throwable t) {

            }
        });
        APIService.sanPhamAPI.getListSanPham().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                List<SanPham> sanPhamList = response.body();
                if (sanPhamList != null) {
                    for (SanPham sanPham : sanPhamList
                    ) {
                        listTenSanPham.add(sanPham.getTenSP());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listTenSanPham);
                    edt_timkiem.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });


    }

}