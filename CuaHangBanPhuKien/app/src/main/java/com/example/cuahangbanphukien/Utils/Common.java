package com.example.cuahangbanphukien.Utils;

import com.example.cuahangbanphukien.Models.ChiTietSanPham;
import com.example.cuahangbanphukien.Models.SanPham;
import com.example.cuahangbanphukien.Models.TaiKhoan;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Common {
    public static String serverLink = "https://cuahangphukiendienthoai.conveyor.cloud/";
    public static TaiKhoan taiKhoan;
    public static List<SanPham> sanPhamList;
    public static SanPham sanPham;
    public static List<ChiTietSanPham> chiTietSanPhamList;


    public static String formatMoney(int number) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(number);
        return str1;
    }
}
