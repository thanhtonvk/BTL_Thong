package com.example.cuahangbanphukien.Models;

public class HangSanXuat {
    private int ID;
    private String TenHang,HinhAnh;
    private boolean IsActive;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String tenHang) {
        TenHang = tenHang;
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

    public HangSanXuat(int ID, String tenHang, String hinhAnh, boolean isActive) {
        this.ID = ID;
        TenHang = tenHang;
        HinhAnh = hinhAnh;
        IsActive = isActive;
    }
}
