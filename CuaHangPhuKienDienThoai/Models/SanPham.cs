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
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public SanPham()
        {
            ChiTietSanPhams = new HashSet<ChiTietSanPham>();
        }

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

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ChiTietSanPham> ChiTietSanPhams { get; set; }

        public virtual HangSanXuat HangSanXuat1 { get; set; }

        public virtual LoaiSanPham LoaiSanPham1 { get; set; }
    }
}
