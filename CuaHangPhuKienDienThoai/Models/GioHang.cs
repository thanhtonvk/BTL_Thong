namespace CuaHangPhuKienDienThoai.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("GioHang")]
    public partial class GioHang
    {
        public int ID { get; set; }

        [Required]
        [StringLength(50)]
        public string TaiKhoan { get; set; }

        public int ChiTietSanPham { get; set; }

        [Column(TypeName = "ntext")]
        public string TenChiTiet { get; set; }

        public int? GiaBan { get; set; }

        public int SoLuong { get; set; }

    
    }
}
