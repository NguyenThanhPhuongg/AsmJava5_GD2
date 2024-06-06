package org.example.demobuoi1.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.demobuoi1.contants.StatusHoaDon;
import org.example.demobuoi1.entity.HoaDon;
import org.example.demobuoi1.entity.KhachHang;
import org.example.demobuoi1.entity.NhanVien;
import org.example.demobuoi1.entity.SanPhamChiTiet;
import org.example.demobuoi1.repositories.asm2.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ban-hang")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BanHangController {

    NhanVienRepository2 nhanVienRepository2;
    HoaDonChiTietRepository2 hoaDonChiTietRepository2;
    KhachHangRepository2 khachHangRepository2;
    HoaDonRepository2 hoaDonRepository2;
    SanPhamChiTietRepository2 sanPhamChiTietRepository2;

    @GetMapping("/index")
    public String index(@RequestParam(name = "limit", defaultValue = "5") int pageSize,
                        @RequestParam(name = "page", defaultValue = "1") int pageNum,
                        @RequestParam(name = "valueSearch", required = false) String valueSearch,
                        @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                        Model model) {
        List<HoaDon> listHD = hoaDonRepository2.findAllByTrangThai(StatusHoaDon.PENDING);
        model.addAttribute("listHD" ,listHD );
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPhamChiTiet> page;
        if (valueSearch != null && searchStatus != null) {
            page = sanPhamChiTietRepository2.search(valueSearch, searchStatus, pageable);
        } else if (valueSearch != null) {
            page = sanPhamChiTietRepository2.search(valueSearch, null, pageable);
        } else if (searchStatus != null) {
            page = sanPhamChiTietRepository2.search("", searchStatus, pageable);
        } else {
            page = sanPhamChiTietRepository2.findAll(pageable);
        }
        model.addAttribute("listSPCT", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "ban_hang/index";
    }

    @GetMapping("tim-kiem")
    public String search(@RequestParam(name = "valueSearch", required = false) String valueSearch,
                         @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                         @RequestParam(name = "limit", defaultValue = "5") int pageSize,
                         @RequestParam(name = "page", defaultValue = "1") int pageNum,
                         Model model) {
        List<HoaDon> listHD = hoaDonRepository2.findAllByTrangThai(StatusHoaDon.PENDING);
        model.addAttribute("listHD" ,listHD );
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPhamChiTiet> page = sanPhamChiTietRepository2.search(valueSearch, searchStatus, pageable);
        model.addAttribute("listSPCT", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "ban_hang/index";
    }
    @GetMapping("/taoHoaDon")
    public String taoHoaDon(HttpSession session) {
        String tenNhanVien = (String) session.getAttribute("userName");
        KhachHang kh = (KhachHang) session.getAttribute("khachHang");

        NhanVien nhanVien = nhanVienRepository2.findByTen(tenNhanVien);
        if (tenNhanVien != null && kh != null) {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setNgayMuaHang(new Date());
            hoaDon.setNhanVien(nhanVien);
            hoaDon.setKhachHang(kh);
            hoaDon.setTongTien(0f);
            hoaDon.setTrangThai(StatusHoaDon.PENDING);
            hoaDonRepository2.save(hoaDon);
        }
        return "redirect:/ban-hang/index";
    }

    @GetMapping("delete/{id}")
    public String delete(HoaDon hoaDon) {
        hoaDonRepository2.delete(hoaDon);
        return "redirect:/ban-hang/index";
    }
    @GetMapping("listKhachHang")
    public String listKhachHang(Model model) {
        List<KhachHang> list = khachHangRepository2.findAll();
        model.addAttribute("listKhachHang", list);
        return "ban_hang/chonKhachHang";
    }

    @GetMapping("chonKhachHang/{id}")
    public String chonKhachHang(@PathVariable("id") Integer id, HttpSession session) {
        KhachHang kh = khachHangRepository2.findById(id).get();
        if (kh != null) {
            session.setAttribute("khachHang", kh);
        }

        return "redirect:/ban-hang/index";
    }
    @GetMapping("chonHoaDon/{id}")
    public String chonHoaDon(@PathVariable("id") Integer id, HttpSession session) {
        HoaDon hoaDon = hoaDonRepository2.findById(id).get();
        if (hoaDon != null) {
            session.setAttribute("hoaDon", hoaDon);
        }

        return "redirect:/ban-hang/index";
    }

    @GetMapping("themKhachHang")
    public String create(@ModelAttribute("khachHang") KhachHang khachHang){
        return "ban_hang/themKhachHang";
    }

    @PostMapping("store")
    public String store(Model model, @Valid KhachHang khachHang, BindingResult validate){
        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "ban_hang/themKhachHang";
        }
        khachHangRepository2.save(khachHang);
        return "redirect:/ban-hang/listKhachHang";
    }
    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}