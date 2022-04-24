using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using CuaHangPhuKienDienThoai.Models;

namespace CuaHangPhuKienDienThoai.Controllers
{
    public class TaiKhoanController : ApiController
    {
        private DBContext db = new DBContext();

        [HttpGet]
        [Route("api/TaiKhoan/GetTaiKhoans")]
        public IHttpActionResult GetTaiKhoans()
        {
            return Ok(db.TaiKhoans);
        }
        [HttpGet]
        [Route("api/TaiKhoan/DangNhap")]
        public IHttpActionResult DangNhap(string TaiKhoan, string MatKhau)
        {
            var tk = db.TaiKhoans
                .FirstOrDefault(x => x.TenTaiKhoan.Equals(TaiKhoan) && x.MatKhau.Equals(MatKhau));
            if (tk != null)
            {
                if (tk.IsActive == true)
                {
                    return Ok("0");
                }
                else
                {
                    //tài khoản bị khóa
                    return Ok("1");
                }
            }
            else
            {
                // sai tài khoản hoặc mật khẩu
                return Ok("2");
            }
        }

        [HttpGet]
        [Route("api/TaiKhoan/GetTaiKhoan")]
        public IHttpActionResult GetTaiKhoan(string TaiKhoan)
        {
            TaiKhoan tk = db.TaiKhoans.Find(TaiKhoan);
            return Ok(tk);
        }


        [HttpPost]
        [Route("api/TaiKhoan/DangKy")]
        public IHttpActionResult DangKy([FromBody] TaiKhoan taiKhoan)
        {
            var item = db.TaiKhoans.Where(x => x.TenTaiKhoan == taiKhoan.TenTaiKhoan);
            if (!item.Any())
            {
                db.TaiKhoans.Add(taiKhoan);
                db.SaveChanges();
                return Ok("0");
            }
            else
            {
                return Ok("1");
            }
        }

        [HttpPut]
        [Route("api/TaiKhoan/CapNhat")]
        public IHttpActionResult CapNhat([FromBody] TaiKhoan taiKhoan)
        {
            db.TaiKhoans.Add(taiKhoan);
            db.Entry(taiKhoan).State = EntityState.Modified;
            if (db.SaveChanges() > 0)
            {
                return Ok("0");
            }

            return Ok("1");
        }
    }
}