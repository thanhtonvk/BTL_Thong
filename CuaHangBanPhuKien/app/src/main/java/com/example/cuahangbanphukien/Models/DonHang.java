package com.example.cuahangbanphukien.Models;

public class DonHang {
    private int ID;
    private String TaiKhoan;
    private String DiaChi;
    private String SDT;
    private String TinhTrang;

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

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public DonHang(int ID, String taiKhoan, String diaChi, String SDT, String tinhTrang) {
        this.ID = ID;
        TaiKhoan = taiKhoan;
        DiaChi = diaChi;
        this.SDT = SDT;
        TinhTrang = tinhTrang;
    }

    public DonHang(String taiKhoan, String diaChi, String SDT, String tinhTrang) {
        TaiKhoan = taiKhoan;
        DiaChi = diaChi;
        this.SDT = SDT;
        TinhTrang = tinhTrang;
    }
}
