package com.example.cuahangbanphukien.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahangbanphukien.API.APIService;
import com.example.cuahangbanphukien.Models.TaiKhoan;
import com.example.cuahangbanphukien.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity {

    EditText edt_taikhoan, edt_matkhau, edt_hoten;
    TextView edt_ngaysinh, btn_dangnhap;
    Button btn_dangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        initView();
        onClick();
    }

    private void onClick() {
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        edt_ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDatePicker();
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taiKhoan = edt_taikhoan.getText().toString();
                String matKhau = edt_matkhau.getText().toString();
                String hoten = edt_hoten.getText().toString();
                String ngaysinh = edt_ngaysinh.getText().toString();
                if(taiKhoan.equals("")||matKhau.equals("")||hoten.equals("")||ngaysinh.equals("")){
                    Toast.makeText(getApplicationContext(),"Các thông tin không được để trống",Toast.LENGTH_LONG).show();
                }else{
                    ProgressDialog dialog = new ProgressDialog(DangKyActivity.this);
                    dialog.setTitle("Đang đăng ký");
                    dialog.show();
                    TaiKhoan tk = new TaiKhoan(taiKhoan,matKhau,hoten,ngaysinh,true);
                    APIService.taiKhoanAPI.DangKy(tk).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            dialog.dismiss();
                            String res = response.body();
                            if(res.equals("0")){
                                Toast.makeText(getApplicationContext(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"Tài khoản đã tồn tại",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Lỗi",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        edt_taikhoan = findViewById(R.id.edt_tendangnhap);
        edt_matkhau = findViewById(R.id.edt_matkhau);
        edt_hoten = findViewById(R.id.edt_hoten);
        edt_ngaysinh = findViewById(R.id.edt_ngaysinh);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        btn_dangky = findViewById(R.id.btn_dangky);
    }
    private void setDatePicker() {
        int selectedYear = 2000;
        int selectedMonth = 5;
        int selectedDayOfMonth = 10;


        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                edt_ngaysinh.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        datePickerDialog.show();
    }
}