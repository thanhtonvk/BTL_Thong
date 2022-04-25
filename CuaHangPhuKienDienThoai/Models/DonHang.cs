namespace CuaHangPhuKienDienThoai.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("DonHang")]
    public partial class DonHang
    {
        
        public int ID { get; set; }

        [Required]
        [StringLength(50)]
        public string TaiKhoan { get; set; }

        [Column(TypeName = "ntext")]
        public string DiaChi { get; set; }

        [StringLength(10)]
        public string SDT { get; set; }

        [Column(TypeName = "ntext")]
        public string TinhTrang { get; set; }

      
    }
}
