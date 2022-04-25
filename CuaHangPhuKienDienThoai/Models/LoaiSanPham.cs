namespace CuaHangPhuKienDienThoai.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("LoaiSanPham")]
    public partial class LoaiSanPham
    {
      

        public int ID { get; set; }

        [Required]
        [StringLength(100)]
        public string TenLoai { get; set; }

        [Column(TypeName = "ntext")]
        public string HinhAnh { get; set; }

        public bool? IsActive { get; set; }

 
    }
}
