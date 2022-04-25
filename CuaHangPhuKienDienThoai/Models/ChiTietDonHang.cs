namespace CuaHangPhuKienDienThoai.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("ChiTietDonHang")]
    public partial class ChiTietDonHang
    {
        public int ID { get; set; }

        public int DonHang { get; set; }

        public int ChiTietSanPham { get; set; }

        [Column(TypeName = "ntext")]
        public string TenChiTiet { get; set; }

        public int? GiaBan { get; set; }

        public int SoLuong { get; set; }

   
    }
}
