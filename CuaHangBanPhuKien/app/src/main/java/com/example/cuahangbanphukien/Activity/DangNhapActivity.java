package com.example.cuahangbanphukien.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahangbanphukien.API.APIService;
import com.example.cuahangbanphukien.Models.TaiKhoan;
import com.example.cuahangbanphukien.R;
import com.example.cuahangbanphukien.Utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {

    EditText edt_taikhoan, edt_matkhau;
    Button btn_dangnhap;
    TextView btn_dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        initView();
        onClick();
    }

    private void initView() {
        edt_taikhoan = findViewById(R.id.edt_tendangnhap);
        edt_matkhau = findViewById(R.id.edt_matkhau);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        btn_dangky = findViewById(R.id.btn_dangky);
    }

    private void onClick() {
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DangKyActivity.class));
            }
        });
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taiKhoan = edt_taikhoan.getText().toString();
                String matKhau = edt_matkhau.getText().toString();
                if (taiKhoan.equals("") || matKhau.equals("")) {
                    Toast.makeText(getApplicationContext(), "Tài khoản và mật khẩu không được để rỗng", Toast.LENGTH_LONG).show();
                } else {
                    ProgressDialog dialog = new ProgressDialog(DangNhapActivity.this);
                    dialog.setTitle("Đang đăng nhập");
                    dialog.show();
                    APIService.taiKhoanAPI.DangNhap(taiKhoan, matKhau).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            dialog.dismiss();
                            String res = response.body();
                            if (res.equals("0")) {
                                APIService.taiKhoanAPI.GetTaiKhoan(taiKhoan).enqueue(new Callback<TaiKhoan>() {
                                    @Override
                                    public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                                        if (response.body() != null) {
                                            Common.taiKhoan = response.body();
                                            startActivity(new Intent(getApplicationContext(), ManHinhChinhActivity.class));
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<TaiKhoan> call, Throwable t) {

                                    }
                                });
                            } else if (res.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Tài khoản bị khóa", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });
                }
            }
        });
    }
}