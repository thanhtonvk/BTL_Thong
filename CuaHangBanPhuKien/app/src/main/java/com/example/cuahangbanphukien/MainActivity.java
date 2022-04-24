package com.example.cuahangbanphukien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cuahangbanphukien.API.APIService;
import com.example.cuahangbanphukien.Activity.DangNhapActivity;
import com.example.cuahangbanphukien.Models.TaiKhoan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(getApplicationContext(), DangNhapActivity.class));
    }
}