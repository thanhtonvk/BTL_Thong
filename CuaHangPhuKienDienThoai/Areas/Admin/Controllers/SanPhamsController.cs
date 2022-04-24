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
    public class SanPhamsController : Controller
    {
        private DBContext db = new DBContext();

        // GET: Admin/SanPhams
        public ActionResult Index()
        {
            var sanPhams = db.SanPhams.Include(s => s.HangSanXuat1).Include(s => s.LoaiSanPham1);
            return View(sanPhams.ToList());
        }

        // GET: Admin/SanPhams/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            SanPham sanPham = db.SanPhams.Find(id);
            if (sanPham == null)
            {
                return HttpNotFound();
            }
            return View(sanPham);
        }

        // GET: Admin/SanPhams/Create
        public ActionResult Create()
        {
            ViewBag.HangSanXuat = new SelectList(db.HangSanXuats, "ID", "TenHang");
            ViewBag.LoaiSanPham = new SelectList(db.LoaiSanPhams, "ID", "TenLoai");
            return View();
        }

        // POST: Admin/SanPhams/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,TenSP,HinhAnh,MoTa,GiaBan,LoaiSanPham,HangSanXuat,SPMoi,GiamGia,IsActive")] SanPham sanPham)
        {
            if (ModelState.IsValid)
            {
                db.SanPhams.Add(sanPham);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.HangSanXuat = new SelectList(db.HangSanXuats, "ID", "TenHang", sanPham.HangSanXuat);
            ViewBag.LoaiSanPham = new SelectList(db.LoaiSanPhams, "ID", "TenLoai", sanPham.LoaiSanPham);
            return View(sanPham);
        }

        // GET: Admin/SanPhams/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            SanPham sanPham = db.SanPhams.Find(id);
            if (sanPham == null)
            {
                return HttpNotFound();
            }
            ViewBag.HangSanXuat = new SelectList(db.HangSanXuats, "ID", "TenHang", sanPham.HangSanXuat);
            ViewBag.LoaiSanPham = new SelectList(db.LoaiSanPhams, "ID", "TenLoai", sanPham.LoaiSanPham);
            return View(sanPham);
        }

        // POST: Admin/SanPhams/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,TenSP,HinhAnh,MoTa,GiaBan,LoaiSanPham,HangSanXuat,SPMoi,GiamGia,IsActive")] SanPham sanPham)
        {
            if (ModelState.IsValid)
            {
                db.Entry(sanPham).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.HangSanXuat = new SelectList(db.HangSanXuats, "ID", "TenHang", sanPham.HangSanXuat);
            ViewBag.LoaiSanPham = new SelectList(db.LoaiSanPhams, "ID", "TenLoai", sanPham.LoaiSanPham);
            return View(sanPham);
        }

        // GET: Admin/SanPhams/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            SanPham sanPham = db.SanPhams.Find(id);
            if (sanPham == null)
            {
                return HttpNotFound();
            }
            return View(sanPham);
        }

        // POST: Admin/SanPhams/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            SanPham sanPham = db.SanPhams.Find(id);
            db.SanPhams.Remove(sanPham);
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
