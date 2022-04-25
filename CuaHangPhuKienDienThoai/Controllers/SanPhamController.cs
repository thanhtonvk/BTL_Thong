using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using CuaHangPhuKienDienThoai.Models;

namespace CuaHangPhuKienDienThoai.Controllers
{
    public class SanPhamController : ApiController
    {
        private DBContext db = new DBContext();

        [HttpGet]
        [Route("api/SanPham/GetSanPhams")]
        //GET sản phẩm
        public IQueryable<SanPham> GetSanPhams()
        {
            return db.SanPhams.Where(x => x.IsActive == true);
        }

        [HttpGet]
        [Route("api/SanPham/GetSanPhamGiamGia")]
        public List<SanPham> GetSanPhamGiamGia()
        {
            return db.SanPhams.Where(x => x.IsActive == true && x.GiamGia == true).ToList();
        }

        [HttpGet]
        [Route("api/SanPham/GetSanPhamMoi")]
        public List<SanPham> GetSanPhamMoi()
        {
            return db.SanPhams.Where(x => x.IsActive == true && x.SPMoi == true).ToList();
        }

        [HttpGet]
        [Route("api/SanPham/GetSanPhamByID")]
        //GET sản phẩm theo ID
        public IHttpActionResult GetSanPhamByID(int ID)
        {
            SanPham sanPham = db.SanPhams.Find(ID);
            return Ok(sanPham);
        }

        [HttpGet]
        [Route("api/SanPham/TimKiem")]
        //Tìm kiếm
        public IEnumerable<SanPham> TimKiem(string TuKhoa)
        {
            if (string.IsNullOrEmpty(TuKhoa))
            {
                return db.SanPhams.Where(x => x.IsActive == true).ToList();
            }

            return db.SanPhams.Where(x =>
                x.TenSP.ToLower().Contains(TuKhoa.ToLower()));
        }


        //get list sản phẩm theo hãng sản xuất
        [HttpGet]
        [Route("api/SanPham/GetSanPhamByHSX")]
        public IEnumerable<SanPham> GetSanPhamByHSX(int IDHangSanXuat)
        {
            return db.SanPhams.Where(x => x.IsActive == true && x.HangSanXuat == IDHangSanXuat);
        }

        [HttpGet]
        [Route("api/SanPham/GetSanPhamByLoaiSanPham")]
        //get list sản phẩm theo loại sản phẩm
        public IEnumerable<SanPham> GetSanPhamByLoaiSanPham(int IDLoaiSanPham)
        {
            return db.SanPhams.Where(x => x.IsActive == true && x.LoaiSanPham == IDLoaiSanPham);
        }

        [HttpGet]
        [Route("api/SanPham/GetChiTietSanPham")]
        //Get ds chi tiết sản phẩm theo id sản phẩm
        public IQueryable<ChiTietSanPham> GetChiTietSanPham(int IDSanPham)
        {
            return db.ChiTietSanPhams.Where(x => x.IsActive == true && x.SanPham == IDSanPham);
        }

        [HttpGet]
        [Route("api/SanPham/GetHangSanXuat")]
        public IQueryable<HangSanXuat> GetHangSanXuat()
        {
            return db.HangSanXuats.Where(x => x.IsActive == true);
        }

        [HttpGet]
        [Route("api/SanPham/GetLoaiSanPham")]
        public IQueryable<LoaiSanPham> GetLoaiSanPham()
        {
            return db.LoaiSanPhams.Where(x => x.IsActive == true);
        }
    }
}