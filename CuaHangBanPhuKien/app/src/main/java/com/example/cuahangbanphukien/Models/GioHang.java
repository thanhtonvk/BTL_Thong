package com.example.cuahangbanphukien.Models;

public class GioHang {
    private int ID;
    private String TaiKhoan;
    private int ChiTietSanPham;
    private String TenChiTiet;
    private int GiaBan;
    private int SoLuong;
    private ChiTietSanPham ChiTietSanPham1;

    public ChiTietSanPham getChiTietSanPham1() {
        return ChiTietSanPham1;
    }

    public GioHang() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public int getChiTietSanPham() {
        return ChiTietSanPham;
    }

    public void setChiTietSanPham(int chiTietSanPham) {
        ChiTietSanPham = chiTietSanPham;
    }

    public String getTenChiTiet() {
        return TenChiTiet;
    }

    public void setTenChiTiet(String tenChiTiet) {
        TenChiTiet = tenChiTiet;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int giaBan) {
        GiaBan = giaBan;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public GioHang(int ID, String taiKhoan, int chiTietSanPham, String tenChiTiet, int giaBan, int soLuong) {
        this.ID = ID;
        TaiKhoan = taiKhoan;
        ChiTietSanPham = chiTietSanPham;
        TenChiTiet = tenChiTiet;
        GiaBan = giaBan;
        SoLuong = soLuong;
    }
}
