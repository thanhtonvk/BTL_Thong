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
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public ChiTietSanPham()
        {
            ChiTietDonHangs = new HashSet<ChiTietDonHang>();
            GioHangs = new HashSet<GioHang>();
        }

        public int ID { get; set; }

        [Required]
        [StringLength(100)]
        public string TenChiTiet { get; set; }

        [Column(TypeName = "ntext")]
        public string HinhAnh { get; set; }

        public int? GiaBan { get; set; }

        public int SanPham { get; set; }

        public bool? IsActive { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ChiTietDonHang> ChiTietDonHangs { get; set; }

        public virtual SanPham SanPham1 { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<GioHang> GioHangs { get; set; }
    }
}
