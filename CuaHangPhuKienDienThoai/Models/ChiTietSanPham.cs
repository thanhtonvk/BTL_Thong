namespace CuaHangPhuKienDienThoai.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("ChiTietSanPham")]
    public partial class ChiTietSanPham
    {
        

        public int ID { get; set; }

        [Required]
        [StringLength(100)]
        public string TenChiTiet { get; set; }

        [Column(TypeName = "ntext")]
        public string HinhAnh { get; set; }

        public int? GiaBan { get; set; }

        public int SanPham { get; set; }

        public bool? IsActive { get; set; }

      
    }
}
