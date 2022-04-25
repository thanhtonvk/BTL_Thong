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
    public class ChiTietSanPhamsController : Controller
    {
        private DBContext db = new DBContext();

        // GET: Admin/ChiTietSanPhams
        public ActionResult Index(int id)
        {
            var chiTietSanPhams = db.ChiTietSanPhams.Where(x=>x.SanPham==id);
            return View(chiTietSanPhams.ToList());
        }

        // GET: Admin/ChiTietSanPhams/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ChiTietSanPham chiTietSanPham = db.ChiTietSanPhams.Find(id);
            if (chiTietSanPham == null)
            {
                return HttpNotFound();
            }
            return View(chiTietSanPham);
        }

        // GET: Admin/ChiTietSanPhams/Create
        public ActionResult Create()
        {
            ViewBag.SanPham = new SelectList(db.SanPhams, "ID", "TenSP");
            return View();
        }

        // POST: Admin/ChiTietSanPhams/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ID,TenChiTiet,HinhAnh,GiaBan,SanPham,IsActive")] ChiTietSanPham chiTietSanPham)
        {
            if (ModelState.IsValid)
            {
                db.ChiTietSanPhams.Add(chiTietSanPham);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            var SanPhams = db.SanPhams.Find(chiTietSanPham.SanPham);
            ViewBag.SanPham = new SelectList(db.SanPhams, "ID", "TenSP", SanPhams);
            return View(chiTietSanPham);
        }

        // GET: Admin/ChiTietSanPhams/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ChiTietSanPham chiTietSanPham = db.ChiTietSanPhams.Find(id);
            if (chiTietSanPham == null)
            {
                return HttpNotFound();
            }
            var SanPhams = db.SanPhams.Find(chiTietSanPham.SanPham);
            ViewBag.SanPham = new SelectList(db.SanPhams, "ID", "TenSP", SanPhams);
            return View(chiTietSanPham);
        }

        // POST: Admin/ChiTietSanPhams/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ID,TenChiTiet,HinhAnh,GiaBan,SanPham,IsActive")] ChiTietSanPham chiTietSanPham)
        {
            if (ModelState.IsValid)
            {
                db.Entry(chiTietSanPham).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            var SanPhams = db.SanPhams.Find(chiTietSanPham.SanPham);
            ViewBag.SanPham = new SelectList(db.SanPhams, "ID", "TenSP", SanPhams);
            return View(chiTietSanPham);
        }

        // GET: Admin/ChiTietSanPhams/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ChiTietSanPham chiTietSanPham = db.ChiTietSanPhams.Find(id);
            if (chiTietSanPham == null)
            {
                return HttpNotFound();
            }
            return View(chiTietSanPham);
        }

        // POST: Admin/ChiTietSanPhams/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            ChiTietSanPham chiTietSanPham = db.ChiTietSanPhams.Find(id);
            db.ChiTietSanPhams.Remove(chiTietSanPham);
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
