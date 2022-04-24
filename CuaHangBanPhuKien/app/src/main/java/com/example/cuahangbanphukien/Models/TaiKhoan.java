package com.example.cuahangbanphukien.Models;

public class TaiKhoan {
    private String TenTaiKhoan,MatKhau,HoTen,NgaySinh;
    private  boolean IsActive;

    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        TenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }
    public TaiKhoan(){

    }
    public TaiKhoan(String tenTaiKhoan, String matKhau, String hoTen, String ngaySinh, boolean isActive) {
        TenTaiKhoan = tenTaiKhoan;
        MatKhau = matKhau;
        HoTen = hoTen;
        NgaySinh = ngaySinh;
        IsActive = isActive;
    }
    public TaiKhoan(String tenTaiKhoan, String matKhau, String hoTen, String ngaySinh) {
        TenTaiKhoan = tenTaiKhoan;
        MatKhau = matKhau;
        HoTen = hoTen;
        NgaySinh = ngaySinh;
    }
}
