package com.example.cuahangbanphukien.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.cuahangbanphukien.API.APIService;
import com.example.cuahangbanphukien.Activity.Adapter.ListSanPhamAdapter;
import com.example.cuahangbanphukien.MainActivity;
import com.example.cuahangbanphukien.Models.ChiTietSanPham;
import com.example.cuahangbanphukien.Models.GioHang;
import com.example.cuahangbanphukien.Models.SanPham;
import com.example.cuahangbanphukien.R;
import com.example.cuahangbanphukien.Utils.Common;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    ImageSlider slider;
    TextView tv_tensp, tv_giaban, tv_mota;
    GridView gv_sanpham;
    Button btn_themgiohang, btn_muangay;
    List<ChiTietSanPham> chiTietSanPhamList;
    List<SlideModel> slideModelList;
    List<SanPham> sanPhamList;
    int flag = 0;
    List<String> tenLoaiSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        initView();
        loadData();
    }

    private void initView() {
        slider = findViewById(R.id.slider);
        tv_tensp = findViewById(R.id.tv_tensanpham);
        tv_giaban = findViewById(R.id.tv_giaban);
        tv_mota = findViewById(R.id.tv_mota);
        gv_sanpham = findViewById(R.id.gv_sanpham);
        btn_themgiohang = findViewById(R.id.btn_themgiohang);
        btn_muangay = findViewById(R.id.btn_muangay);
        slideModelList = new ArrayList<>();
        tenLoaiSanPham = new ArrayList<>();
    }

    private void loadData() {

        tv_tensp.setText(Common.sanPham.getTenSP());
        tv_giaban.setText(Common.formatMoney(Common.sanPham.getGiaBan()));
        tv_mota.setText(Common.sanPham.getMoTa());
        APIService.sanPhamAPI.getChiTietSanPhams(Common.sanPham.getID()).enqueue(new Callback<List<ChiTietSanPham>>() {
            @Override
            public void onResponse(Call<List<ChiTietSanPham>> call, Response<List<ChiTietSanPham>> response) {
                chiTietSanPhamList = response.body();
                slideModelList.add(new SlideModel(Common.sanPham.getHinhAnh(), ScaleTypes.CENTER_CROP));
                for (ChiTietSanPham chiTietSanPham : chiTietSanPhamList) {
                    slideModelList.add(new SlideModel(chiTietSanPham.getHinhAnh(), ScaleTypes.CENTER_CROP));
                    tenLoaiSanPham.add(chiTietSanPham.getTenChiTiet());
                }
                slider.setImageList(slideModelList);
            }

            @Override
            public void onFailure(Call<List<ChiTietSanPham>> call, Throwable t) {

            }
        });
        APIService.sanPhamAPI.getSanPhamByLoai(Common.sanPham.getLoaiSanPham()).enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                sanPhamList = response.body();
                ListSanPhamAdapter adapter = new ListSanPhamAdapter(getApplicationContext(), sanPhamList);
                gv_sanpham.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
        gv_sanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.sanPham = sanPhamList.get(i);
                startActivity(new Intent(getApplicationContext(), ChiTietSanPhamActivity.class));
            }
        });
        btn_themgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                dialog();
            }
        });
        btn_muangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 2;
                dialog();
            }
        });

    }

    private void dialog() {
        Dialog dialog = new Dialog(ChiTietSanPhamActivity.this);
        dialog.setContentView(R.layout.dialog_chi_tiet_san_pham);
        dialog.show();
        ImageView img = dialog.findViewById(R.id.image);
        TextView tv_giaban = dialog.findViewById(R.id.tv_giaban);
        Spinner sp_loaisp = dialog.findViewById(R.id.sp_loaisp);
        Button btn_giam = dialog.findViewById(R.id.btn_giam);
        Button btn_tang = dialog.findViewById(R.id.btn_tang);
        EditText edt_soluong = dialog.findViewById(R.id.edt_soluong);
        Button btn_xacnhan = dialog.findViewById(R.id.btn_xacnhan);
        if (tenLoaiSanPham.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ChiTietSanPhamActivity.this, android.R.layout.simple_spinner_dropdown_item, tenLoaiSanPham);
            sp_loaisp.setAdapter(adapter);
            int index = sp_loaisp.getSelectedItemPosition();
            ChiTietSanPham chiTietSanPham = chiTietSanPhamList.get(index);
            tv_giaban.setText(Common.formatMoney(chiTietSanPham.getGiaBan()));
            Glide.with(ChiTietSanPhamActivity.this).load(chiTietSanPham.getHinhAnh()).into(img);
            sp_loaisp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ChiTietSanPham chiTietSanPham = chiTietSanPhamList.get(i);
                    tv_giaban.setText(Common.formatMoney(chiTietSanPham.getGiaBan()));
                    Glide.with(ChiTietSanPhamActivity.this).load(chiTietSanPham.getHinhAnh()).into(img);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    ChiTietSanPham chiTietSanPham = chiTietSanPhamList.get(0);
                    tv_giaban.setText(Common.formatMoney(chiTietSanPham.getGiaBan()));
                    Glide.with(ChiTietSanPhamActivity.this).load(chiTietSanPham.getHinhAnh()).into(img);
                }
            });
            btn_giam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(edt_soluong.getText().toString()) == 1) {
                        edt_soluong.setText("1");
                    } else {
                        int soluong = Integer.parseInt(edt_soluong.getText().toString());
                        soluong--;
                        edt_soluong.setText(soluong + "");
                    }
                }
            });
            btn_tang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int soluong = Integer.parseInt(edt_soluong.getText().toString());
                    soluong++;
                    edt_soluong.setText(soluong + "");
                }
            });
            if (flag == 1) {
                //thêm vào giỏ hàng
                btn_xacnhan.setText("Thêm vào giỏ hàng");
                btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GioHang gioHang = new GioHang();
                        gioHang.setTaiKhoan(Common.taiKhoan.getTenTaiKhoan());
                        gioHang.setChiTietSanPham(chiTietSanPham.getID());
                        gioHang.setTenChiTiet(chiTietSanPham.getTenChiTiet());
                        gioHang.setGiaBan(chiTietSanPham.getGiaBan());
                        gioHang.setSoLuong(Integer.parseInt(edt_soluong.getText().toString()));
                        APIService.gioHangAPI.themGioHang(gioHang).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String res = response.body();
                                if (res != null) {
                                    if (res.equals("0")) {
                                        Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_LONG).show();
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Lỗi, kiểm tra internet", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            } else {
                //mua ngay
                btn_xacnhan.setText("Mua ngay");
                btn_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GioHang gioHang = new GioHang();
                        gioHang.setTaiKhoan(Common.taiKhoan.getTenTaiKhoan());
                        gioHang.setChiTietSanPham(chiTietSanPham.getID());
                        gioHang.setTenChiTiet(chiTietSanPham.getTenChiTiet());
                        gioHang.setGiaBan(chiTietSanPham.getGiaBan());
                        gioHang.setSoLuong(Integer.parseInt(edt_soluong.getText().toString()));
                        APIService.gioHangAPI.themGioHang(gioHang).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String res = response.body();
                                if (res != null) {
                                    if (res.equals("0")) {
                                        Toast.makeText(getApplicationContext(), "Thành công", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_LONG).show();
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Lỗi, kiểm tra internet", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        }


    }

}