package com.example.cuahangbanphukien.Models;

public class SanPham {
    private int ID;
    private String TenSP,HinhAnh,MoTa;
    private int GiaBan,LoaiSanPham,HangSanXuat;
    private boolean SPMoi,GiamGia;
    private boolean IsActive;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int giaBan) {
        GiaBan = giaBan;
    }

    public int getLoaiSanPham() {
        return LoaiSanPham;
    }

    public void setLoaiSanPham(int loaiSanPham) {
        LoaiSanPham = loaiSanPham;
    }

    public int getHangSanXuat() {
        return HangSanXuat;
    }

    public void setHangSanXuat(int hangSanXuat) {
        HangSanXuat = hangSanXuat;
    }

    public boolean isSPMoi() {
        return SPMoi;
    }

    public void setSPMoi(boolean SPMoi) {
        this.SPMoi = SPMoi;
    }

    public boolean isGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(boolean giamGia) {
        GiamGia = giamGia;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public SanPham(int ID, String tenSP, String hinhAnh, String moTa, int giaBan, int loaiSanPham, int hangSanXuat, boolean SPMoi, boolean giamGia, boolean isActive) {
        this.ID = ID;
        TenSP = tenSP;
        HinhAnh = hinhAnh;
        MoTa = moTa;
        GiaBan = giaBan;
        LoaiSanPham = loaiSanPham;
        HangSanXuat = hangSanXuat;
        this.SPMoi = SPMoi;
        GiamGia = giamGia;
        IsActive = isActive;
    }
}
