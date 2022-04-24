using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using CuaHangPhuKienDienThoai.Models;

namespace CuaHangPhuKienDienThoai.Controllers
{
    public class DonHangController : ApiController
    {
        private DBContext db = new DBContext();

        [HttpPost]
        [Route("api/DonHang/ThemDonHang")]
        public IHttpActionResult ThemDonHang([FromBody] DonHang donHang, [FromBody] List<GioHang> gioHangs)
        {
            db.DonHangs.Add(donHang);
            foreach (var gioHang in gioHangs)
            {
                ChiTietDonHang chiTietDonHang = new ChiTietDonHang()
                {
                    DonHang = donHang.ID, ChiTietSanPham = gioHang.ChiTietSanPham, TenChiTiet = gioHang.TenChiTiet,
                    GiaBan = gioHang.GiaBan, SoLuong = gioHang.SoLuong
                };
                db.ChiTietDonHangs.Add(chiTietDonHang);
            }

            db.GioHangs.RemoveRange(gioHangs);
            if (db.SaveChanges() > 0)
            {
                return Ok("0");
            }

            return Ok("1");
        }

        [HttpGet]
        [Route("api/DonHang/GetDonHang")]
        public IQueryable<DonHang> GetDonHang(string TaiKhoan)
        {
            return db.DonHangs.Where(x => x.TaiKhoan == TaiKhoan);
        }

        [HttpGet]
        [Route("api/DonHang/GetDonHangByID")]
        public IHttpActionResult GetDonHangByID(int IDDonHang)
        {
            return Ok(db.DonHangs.Find(IDDonHang));
        }

        [HttpGet]
        [Route("api/DonHang/GetChiTietDonHangs")]
        public IQueryable<ChiTietDonHang> GetChiTietDonHangs(int IDDonHang)
        {
            return db.ChiTietDonHangs.Where(x => x.DonHang == IDDonHang);
        }
        
    }
}