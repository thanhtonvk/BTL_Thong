namespace CuaHangPhuKienDienThoai.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("SanPham")]
    public partial class SanPham
    {
      
        public int ID { get; set; }

        [Required]
        [StringLength(100)]
        public string TenSP { get; set; }

        [Column(TypeName = "ntext")]
        public string HinhAnh { get; set; }

        [Column(TypeName = "ntext")]
        public string MoTa { get; set; }

        public int? GiaBan { get; set; }

        public int LoaiSanPham { get; set; }

        public int HangSanXuat { get; set; }

        public bool? SPMoi { get; set; }

        public bool? GiamGia { get; set; }

        public bool? IsActive { get; set; }
    }
}
