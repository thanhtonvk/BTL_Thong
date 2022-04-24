package com.example.cuahangbanphukien.Models;

public class ChiTietDonHang {
    private int ID, DonHang, ChiTietSanPham;
    private String TenChiTiet;
    private int GiaBan;
    private int SoLuong;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDonHang() {
        return DonHang;
    }

    public void setDonHang(int donHang) {
        DonHang = donHang;
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

    public ChiTietDonHang(int ID, int donHang, int chiTietSanPham, String tenChiTiet, int giaBan, int soLuong) {
        this.ID = ID;
        DonHang = donHang;
        ChiTietSanPham = chiTietSanPham;
        TenChiTiet = tenChiTiet;
        GiaBan = giaBan;
        SoLuong = soLuong;
    }
}
