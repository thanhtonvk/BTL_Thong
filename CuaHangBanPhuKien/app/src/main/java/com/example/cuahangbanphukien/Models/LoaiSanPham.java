package com.example.cuahangbanphukien.Models;

public class LoaiSanPham {
    private int ID;
    private String TenLoai, HinhAnh;
    private boolean IsActive;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenHang) {
        TenLoai = tenHang;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public LoaiSanPham(int ID, String tenLoai, String hinhAnh, boolean isActive) {
        this.ID = ID;
        TenLoai = tenLoai;
        HinhAnh = hinhAnh;
        IsActive = isActive;
    }
}
