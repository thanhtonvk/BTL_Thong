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
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public DonHang()
        {
            ChiTietDonHangs = new HashSet<ChiTietDonHang>();
        }

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

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ChiTietDonHang> ChiTietDonHangs { get; set; }

        public virtual TaiKhoan TaiKhoan1 { get; set; }
    }
}
