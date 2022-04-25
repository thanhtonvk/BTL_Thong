package com.example.cuahangbanphukien.API;

import com.example.cuahangbanphukien.Models.ChiTietDonHang;
import com.example.cuahangbanphukien.Models.ChiTietSanPham;
import com.example.cuahangbanphukien.Models.DonHang;
import com.example.cuahangbanphukien.Models.GioHang;
import com.example.cuahangbanphukien.Models.HangSanXuat;
import com.example.cuahangbanphukien.Models.LoaiSanPham;
import com.example.cuahangbanphukien.Models.SanPham;
import com.example.cuahangbanphukien.Models.TaiKhoan;
import com.example.cuahangbanphukien.Utils.Common;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    APIService taiKhoanAPI = new Retrofit.Builder()
            .baseUrl(Common.serverLink + "/api/TaiKhoan/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("GetTaiKhoans")
    Call<List<TaiKhoan>> getAllTaiKhoans();

    @GET("DangNhap")
    Call<String> DangNhap(@Query("TaiKhoan") String taiKhoan, @Query("MatKhau") String matKhau);

    @GET("GetTaiKhoan")
    Call<TaiKhoan> GetTaiKhoan(@Query("TaiKhoan") String taiKhoan);

    @POST("DangKy")
    Call<String> DangKy(@Body TaiKhoan taiKhoan);

    @PUT("CapNhat")
    Call<String> capNhatTaiKhoan(@Body TaiKhoan taiKhoan);

    APIService sanPhamAPI = new Retrofit.Builder()
            .baseUrl(Common.serverLink + "/api/SanPham/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("GetSanPhams")
    Call<List<SanPham>> getListSanPham();

    @GET("GetSanPhamGiamGia")
    Call<List<SanPham>> getListGiamGia();

    @GET("GetSanPhamMoi")
    Call<List<SanPham>> getListSanPhamMoi();

    @GET("GetSanPhamByID")
    Call<SanPham> getSanPhamByID(@Query("ID") int ID);

    @GET("TimKiem")
    Call<List<SanPham>> timKiem(@Query("TuKhoa") String tuKhoa);

    @GET("GetSanPhamByHSX")
    Call<List<SanPham>> getSanPhamByHSX(@Query("IDHangSanXuat") int IDHangSX);

    @GET("GetSanPhamByLoaiSanPham")
    Call<List<SanPham>> getSanPhamByLoai(@Query("IDLoaiSanPham") int IDLoai);

    @GET("GetChiTietSanPham")
    Call<List<ChiTietSanPham>> getChiTietSanPhams(@Query("IDSanPham") int IDSP);

    @GET("GetHangSanXuat")
    Call<List<HangSanXuat>> getHangSanXuats();

    @GET("GetLoaiSanPham")
    Call<List<LoaiSanPham>> getLoaiSanPhams();

    APIService gioHangAPI = new Retrofit.Builder()
            .baseUrl(Common.serverLink + "/api/GioHang/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @POST("ThemGioHang")
    Call<String> themGioHang(@Body GioHang gioHang);

    @GET("GetGioHang")
    Call<List<GioHang>> getGioHangs(@Query("TenTaiKhoan") String tenTaiKhoan);

    @PUT("UpdategioHang")
    Call<String> updateGioHang(@Query("IDGioHang") int IDGioHang, @Query("SoLuong") int soLuong);

    @DELETE("XoaGioHang")
    Call<String> xoaGioHang(@Query("IDGioHang") int IDGioHang);

    @DELETE("XoaAllGioHang")
    Call<String> xoaAllGioHang(@Query("TaiKhoan") String taiKhoan);

    APIService donHangAPI = new Retrofit.Builder()
            .baseUrl(Common.serverLink + "/api/DonHang/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @POST("ThemDonHang")
    Call<String> themDonHang(@Body DonHang donHang);

    @GET("GetDonHang")
    Call<List<DonHang>> getDonHang(@Query("TaiKhoan") String taiKhoan);

    @GET("GetDonHangByID")
    Call<DonHang> getDonHangByID(@Query("IDDonHang") int IDDonHang);

    @GET("GetChiTietDonHangs")
    Call<List<ChiTietDonHang>> getChiTietDonHangs(@Query("IDDonHang") int IDDonHang);

}
