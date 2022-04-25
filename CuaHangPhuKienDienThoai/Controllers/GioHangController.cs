using System.Data.Entity;
using System.Linq;
using System.Web.Http;
using CuaHangPhuKienDienThoai.Models;

namespace CuaHangPhuKienDienThoai.Controllers
{
    public class GioHangController : ApiController
    {
        private DBContext db = new DBContext();

        [HttpPost]
        [Route("api/GioHang/ThemGioHang")]
        public IHttpActionResult ThemGioHang([FromBody] GioHang gioHang)
        {
            db.GioHangs.Add(gioHang);

            if (db.SaveChanges() > 0)
            {
                return Ok("0");
            }
            else
            {
                return Ok("1");
            }
        }

        private int KTTonTai(int ChiTietSanPham)
        {
            var item = db.GioHangs.FirstOrDefault(x => x.ChiTietSanPham == ChiTietSanPham);
            if (item != null)
            {
                return db.GioHangs.Local.IndexOf(item);
            }

            return -1;
        }

        [HttpGet]
        [Route("api/GioHang/GetGioHang")]
        public IQueryable<GioHang> GetGioHang(string TenTaiKhoan)
        {
            return db.GioHangs.Where(x => x.TaiKhoan == TenTaiKhoan);
        }

        [HttpPut]
        [Route("api/GioHang/UpdateGioHang")]
        public IHttpActionResult UpdateGioHang(int IDGioHang, int SoLuong)
        {
            var gioHang = db.GioHangs.Find(IDGioHang);
            gioHang.SoLuong = SoLuong;
            db.GioHangs.Add(gioHang);
            db.Entry(gioHang).State = EntityState.Modified;
            if (db.SaveChanges() > 0)
            {
                return Ok("0");
            }
            else
            {
                return Ok("1");
            }
        }

        [HttpDelete]
        [Route("api/GioHang/XoaGioHang")]
        public IHttpActionResult DeleteGioHang(int IDGioHang)
        {
            var giohang = db.GioHangs.Find(IDGioHang);
            db.GioHangs.Remove(giohang);
            if (db.SaveChanges() > 0)
            {
                return Ok("0");
            }
            else
            {
                return Ok("1");
            }
        }

        [HttpDelete]
        [Route("api/GioHang/XoaAllGioHang")]
        public IHttpActionResult XoaAllGioHang(string TaiKhoan)
        {
            db.GioHangs.RemoveRange(db.GioHangs.Where(x => x.TaiKhoan == TaiKhoan));
            if (db.SaveChanges() > 0)
            {
                return Ok("0");
            }
            else
            {
                return Ok("1");
            }
        }
    }
}