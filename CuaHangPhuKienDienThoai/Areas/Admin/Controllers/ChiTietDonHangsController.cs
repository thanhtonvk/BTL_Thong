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
    public class ChiTietDonHangsController : Controller
    {
        private DBContext db = new DBContext();

        // GET: Admin/ChiTietDonHangs
        public ActionResult Index()
        {
            var chiTietDonHangs = db.ChiTietDonHangs.Include(c => c.ChiTietSanPham1).Include(c => c.DonHang1);
            return View(chiTietDonHangs.ToList());
        }

        // GET: Admin/ChiTietDonHangs/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ChiTietDonHang chiTietDonHang = db.ChiTietDonHangs.Find(id);
            if (chiTietDonHang == null)
            {
                return HttpNotFound();
            }
            return View(chiTietDonHang);
        }

        // GET: Admin/ChiTietDonHangs/Create
        public ActionResult Create()
        {
            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet");
            ViewBag.DonHang = new SelectList(db.DonHangs, "ID", "TaiKhoan");
            return View();
        }

        // POST: Admin/ChiTietDonHangs/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,DonHang,ChiTietSanPham,TenChiTiet,GiaBan,SoLuong")] ChiTietDonHang chiTietDonHang)
        {
            if (ModelState.IsValid)
            {
                db.ChiTietDonHangs.Add(chiTietDonHang);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet", chiTietDonHang.ChiTietSanPham);
            ViewBag.DonHang = new SelectList(db.DonHangs, "ID", "TaiKhoan", chiTietDonHang.DonHang);
            return View(chiTietDonHang);
        }

        // GET: Admin/ChiTietDonHangs/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ChiTietDonHang chiTietDonHang = db.ChiTietDonHangs.Find(id);
            if (chiTietDonHang == null)
            {
                return HttpNotFound();
            }
            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet", chiTietDonHang.ChiTietSanPham);
            ViewBag.DonHang = new SelectList(db.DonHangs, "ID", "TaiKhoan", chiTietDonHang.DonHang);
            return View(chiTietDonHang);
        }

        // POST: Admin/ChiTietDonHangs/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,DonHang,ChiTietSanPham,TenChiTiet,GiaBan,SoLuong")] ChiTietDonHang chiTietDonHang)
        {
            if (ModelState.IsValid)
            {
                db.Entry(chiTietDonHang).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet", chiTietDonHang.ChiTietSanPham);
            ViewBag.DonHang = new SelectList(db.DonHangs, "ID", "TaiKhoan", chiTietDonHang.DonHang);
            return View(chiTietDonHang);
        }

        // GET: Admin/ChiTietDonHangs/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ChiTietDonHang chiTietDonHang = db.ChiTietDonHangs.Find(id);
            if (chiTietDonHang == null)
            {
                return HttpNotFound();
            }
            return View(chiTietDonHang);
        }

        // POST: Admin/ChiTietDonHangs/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            ChiTietDonHang chiTietDonHang = db.ChiTietDonHangs.Find(id);
            db.ChiTietDonHangs.Remove(chiTietDonHang);
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
