using System;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity;
using System.Linq;

namespace CuaHangPhuKienDienThoai.Models
{
    public partial class DBContext : DbContext
    {
        public DBContext()
            : base("name=DBContext")
        {
        }

        public virtual DbSet<ChiTietDonHang> ChiTietDonHangs { get; set; }
        public virtual DbSet<ChiTietSanPham> ChiTietSanPhams { get; set; }
        public virtual DbSet<DonHang> DonHangs { get; set; }
        public virtual DbSet<GioHang> GioHangs { get; set; }
        public virtual DbSet<HangSanXuat> HangSanXuats { get; set; }
        public virtual DbSet<LoaiSanPham> LoaiSanPhams { get; set; }
        public virtual DbSet<SanPham> SanPhams { get; set; }
        public virtual DbSet<TaiKhoan> TaiKhoans { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<ChiTietSanPham>()
                .HasMany(e => e.ChiTietDonHangs)
                .WithRequired(e => e.ChiTietSanPham1)
                .HasForeignKey(e => e.ChiTietSanPham)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<ChiTietSanPham>()
                .HasMany(e => e.GioHangs)
                .WithRequired(e => e.ChiTietSanPham1)
                .HasForeignKey(e => e.ChiTietSanPham)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<DonHang>()
                .Property(e => e.TaiKhoan)
                .IsUnicode(false);

            modelBuilder.Entity<DonHang>()
                .Property(e => e.SDT)
                .IsFixedLength()
                .IsUnicode(false);

            modelBuilder.Entity<DonHang>()
                .HasMany(e => e.ChiTietDonHangs)
                .WithRequired(e => e.DonHang1)
                .HasForeignKey(e => e.DonHang)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<GioHang>()
                .Property(e => e.TaiKhoan)
                .IsUnicode(false);

            modelBuilder.Entity<HangSanXuat>()
                .HasMany(e => e.SanPhams)
                .WithRequired(e => e.HangSanXuat1)
                .HasForeignKey(e => e.HangSanXuat)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<LoaiSanPham>()
                .HasMany(e => e.SanPhams)
                .WithRequired(e => e.LoaiSanPham1)
                .HasForeignKey(e => e.LoaiSanPham)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<SanPham>()
                .HasMany(e => e.ChiTietSanPhams)
                .WithRequired(e => e.SanPham1)
                .HasForeignKey(e => e.SanPham)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<TaiKhoan>()
                .Property(e => e.TenTaiKhoan)
                .IsUnicode(false);

            modelBuilder.Entity<TaiKhoan>()
                .Property(e => e.MatKhau)
                .IsUnicode(false);

            modelBuilder.Entity<TaiKhoan>()
                .HasMany(e => e.DonHangs)
                .WithRequired(e => e.TaiKhoan1)
                .HasForeignKey(e => e.TaiKhoan)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<TaiKhoan>()
                .HasMany(e => e.GioHangs)
                .WithRequired(e => e.TaiKhoan1)
                .HasForeignKey(e => e.TaiKhoan)
                .WillCascadeOnDelete(false);
        }
    }
}
