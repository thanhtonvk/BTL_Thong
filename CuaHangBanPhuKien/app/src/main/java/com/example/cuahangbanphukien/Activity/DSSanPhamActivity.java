package com.example.cuahangbanphukien.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.cuahangbanphukien.Activity.Adapter.ListSanPhamAdapter;
import com.example.cuahangbanphukien.R;
import com.example.cuahangbanphukien.Utils.Common;

public class DSSanPhamActivity extends AppCompatActivity {

    ImageView btn_trove;
    ListSanPhamAdapter sanPhamAdapter;
    GridView gv_sanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dssan_pham);
        initView();
        sanPhamAdapter = new ListSanPhamAdapter(getApplicationContext(), Common.sanPhamList);
        gv_sanpham.setAdapter(sanPhamAdapter);
        btn_trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gv_sanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.sanPham = Common.sanPhamList.get(i);
                startActivity(new Intent(getApplicationContext(), ChiTietSanPhamActivity.class));
            }
        });
    }

    private void initView() {
        btn_trove = findViewById(R.id.btn_trove);
        gv_sanpham = findViewById(R.id.gv_sanpham);
    }
}