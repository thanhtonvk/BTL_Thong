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
        public ActionResult Index(int id)
        {
            var chiTietDonHangs = db.ChiTietDonHangs.Where(x=>x.DonHang==id);
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
            var chiTietSanPhams = db.ChiTietSanPhams.Where(x => x.ID==chiTietDonHang.ChiTietSanPham);
            var donHangs = db.DonHangs.Where(x => x.ID == chiTietDonHang.DonHang);
            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet", chiTietSanPhams);
            ViewBag.DonHang = new SelectList(db.DonHangs, "ID", "TaiKhoan", donHangs);
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
            var chiTietSanPhams = db.ChiTietSanPhams.Where(x => x.ID == chiTietDonHang.ChiTietSanPham);
            var donHangs = db.DonHangs.Where(x => x.ID == chiTietDonHang.DonHang);
            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet", chiTietSanPhams);
            ViewBag.DonHang = new SelectList(db.DonHangs, "ID", "TaiKhoan", donHangs);
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
            var chiTietSanPhams = db.ChiTietSanPhams.Where(x => x.ID == chiTietDonHang.ChiTietSanPham);
            var donHangs = db.DonHangs.Where(x => x.ID == chiTietDonHang.DonHang);
            ViewBag.ChiTietSanPham = new SelectList(db.ChiTietSanPhams, "ID", "TenChiTiet", chiTietSanPhams);
            ViewBag.DonHang = new SelectList(db.DonHangs, "ID", "TaiKhoan", donHangs);
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
