using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using CuaHangPhuKienDienThoai.Models;

namespace CuaHangPhuKienDienThoai.Areas.Admin.Controllers
{
    public class GioHangsController : Controller
    {
        private DBContext db = new DBContext();

        // GET: Admin/GioHangs
        public ActionResult Index()
        {
            var gioHangs = db.GioHangs;
            return View(gioHangs.ToList());
        }

        // GET: Admin/GioHangs/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            GioHang gioHang = db.GioHangs.Find(id);
            if (gioHang == null)
            {
                return HttpNotFound();
            }
            return View(gioHang);
        }

        // GET: Admin/GioHangs/Create
        public ActionResult Create()
        {
            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet");
            ViewBag.TaiKhoan = new SelectList(db.TaiKhoans, "TenTaiKhoan", "MatKhau");
            return View();
        }

        // POST: Admin/GioHangs/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,TaiKhoan,ChiTietSanPham,TenChiTiet,GiaBan,SoLuong")] GioHang gioHang)
        {
            if (ModelState.IsValid)
            {
                db.GioHangs.Add(gioHang);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet", gioHang.ChiTietSanPham);
            ViewBag.TaiKhoan = new SelectList(db.TaiKhoans, "TenTaiKhoan", "MatKhau", gioHang.TaiKhoan);
            return View(gioHang);
        }

        // GET: Admin/GioHangs/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            GioHang gioHang = db.GioHangs.Find(id);
            if (gioHang == null)
            {
                return HttpNotFound();
            }
            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet", gioHang.ChiTietSanPham);
            ViewBag.TaiKhoan = new SelectList(db.TaiKhoans, "TenTaiKhoan", "MatKhau", gioHang.TaiKhoan);
            return View(gioHang);
        }

        // POST: Admin/GioHangs/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,TaiKhoan,ChiTietSanPham,TenChiTiet,GiaBan,SoLuong")] GioHang gioHang)
        {
            if (ModelState.IsValid)
            {
                db.Entry(gioHang).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet", gioHang.ChiTietSanPham);
            ViewBag.TaiKhoan = new SelectList(db.TaiKhoans, "TenTaiKhoan", "MatKhau", gioHang.TaiKhoan);
            return View(gioHang);
        }

        // GET: Admin/GioHangs/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            GioHang gioHang = db.GioHangs.Find(id);
            if (gioHang == null)
            {
                return HttpNotFound();
            }
            return View(gioHang);
        }

        // POST: Admin/GioHangs/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            GioHang gioHang = db.GioHangs.Find(id);
            db.GioHangs.Remove(gioHang);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
