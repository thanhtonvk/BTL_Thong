use master
go
create database CuaHangPhuKien
go
use CuaHangPhuKien
go
create table TaiKhoan(
	TenTaiKhoan varchar(50) primary key,
	MatKhau varchar(20) not null,
	HoTen nvarchar(50) not null,
	NgaySinh date,
	IsActive bit default 1
)
go
create table LoaiSanPham(
	ID int identity(1,1) primary key,
	TenLoai nvarchar(100) not null,
	HinhAnh ntext,
	IsActive bit default 1
)
go
create table HangSanXuat(
	ID int identity(1,1) primary key,
	TenHang nvarchar(100) not null,
	HinhAnh ntext,
	IsActive bit default 1
)
go
create table SanPham(
	ID int identity(1,1) primary key,
	TenSP nvarchar(100) not null,
	HinhAnh ntext,
	MoTa ntext,
	GiaBan int,
	LoaiSanPham int not null constraint fk_1 foreign key(LoaiSanPham) references LoaiSanPham(ID),
	HangSanXuat int not null constraint fk_2 foreign key(HangSanXuat) references HangSanXuat(ID),
	SPMoi bit default 0,
	GiamGia bit default 0,
	IsActive bit default 1
)
go
create table ChiTietSanPham(
	ID int identity(1,1) primary key,
	TenChiTiet nvarchar(100) not null,
	HinhAnh ntext,
	GiaBan int,
	SanPham int not null constraint fk_3 foreign key(SanPham) references SanPham(ID),
	IsActive bit default 1
)
go
create table GioHang(
	ID int identity(1,1) primary key,
	TaiKhoan varchar(50) not null constraint fk_4 foreign key(TaiKhoan) references TaiKhoan(TenTaiKhoan),
	ChiTietSanPham int not null constraint fk_5 foreign key(ChiTietSanPham) references ChiTietSanPham(ID),
	TenChiTiet ntext,
	GiaBan int,
	SoLuong int not null
)
go
create table DonHang(
	ID int identity(1,1) primary key,
	TaiKhoan varchar(50) not null constraint fk_6 foreign key(TaiKhoan) references TaiKhoan(TenTaiKhoan),
	DiaChi ntext,
	SDT char(10),
	TinhTrang ntext,
)
go
create table ChiTietDonHang(
	ID int identity(1,1) primary key,
	DonHang int not null constraint fk_7 foreign key(DonHang) references DonHang(ID),
	ChiTietSanPham int not null constraint fk_8 foreign key(ChiTietSanPham) references ChiTietSanPham(ID),
	TenChiTiet ntext,
	GiaBan int,
	SoLuong int not null
)