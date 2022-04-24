package com.example.cuahangbanphukien.Models;

public class ChiTietSanPham {
    private int ID;
    private String TenChiTiet,HinhAnh;
    private int GiaBan;
    private int SanPham;
    private boolean IsActive;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenChiTiet() {
        return TenChiTiet;
    }

    public void setTenChiTiet(String tenChiTiet) {
        TenChiTiet = tenChiTiet;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int giaBan) {
        GiaBan = giaBan;
    }

    public int getSanPham() {
        return SanPham;
    }

    public void setSanPham(int sanPham) {
        SanPham = sanPham;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public ChiTietSanPham(int ID, String tenChiTiet, String hinhAnh, int giaBan, int sanPham, boolean isActive) {
        this.ID = ID;
        TenChiTiet = tenChiTiet;
        HinhAnh = hinhAnh;
        GiaBan = giaBan;
        SanPham = sanPham;
        IsActive = isActive;
    }
}
