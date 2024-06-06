package org.example.demobuoi1.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demobuoi1.entity.HoaDon;
import org.example.demobuoi1.entity.KhachHang;
import org.example.demobuoi1.entity.NhanVien;
import org.example.demobuoi1.entity.SanPham;
import org.example.demobuoi1.repositories.asm2.HoaDonRepository2;
import org.example.demobuoi1.repositories.asm2.KhachHangRepository2;
import org.example.demobuoi1.repositories.asm2.NhanVienRepository2;
import org.example.demobuoi1.request.HoaDonRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hoa-don")
@Slf4j
public class HoaDonController {
    private final NhanVienRepository2 nhanVienRepository2;
    private final KhachHangRepository2 khachHangRepository2;
    private final HoaDonRepository2 hoaDonRepository2;

    @GetMapping("index")
    public String listHoaDon(@RequestParam(name = "limit", defaultValue = "10") int pageSize,
                                @RequestParam(name = "page", defaultValue = "1") int pageNum,
                                @RequestParam(name = "valueSearch", required = false) String valueSearch,
                                @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                                Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<HoaDon> page;
        if (valueSearch != null && searchStatus != null) {
            page = hoaDonRepository2.search(valueSearch, searchStatus, pageable);
        } else if (valueSearch != null) {
            page = hoaDonRepository2.search(valueSearch, null, pageable);
        } else if (searchStatus != null) {
            page = hoaDonRepository2.search("", searchStatus, pageable);
        } else {
            page = hoaDonRepository2.findAll(pageable);
        }
        model.addAttribute("listHoaDon", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "hoa_don/index";
    }

    @GetMapping("tim-kiem")
    public String search(@RequestParam(name = "valueSearch", required = false) String valueSearch,
                         @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                         @RequestParam(name = "limit", defaultValue = "10") int pageSize,
                         @RequestParam(name = "page", defaultValue = "1") int pageNum,
                         Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<HoaDon> page = hoaDonRepository2.search(valueSearch, searchStatus, pageable);
        model.addAttribute("listHoaDon", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "hoa_don/index";
    }
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        HoaDon hoaDon = hoaDonRepository2.findById(id).get();
        model.addAttribute("data", hoaDon);
        model.addAttribute("listNhanVien", nhanVienRepository2.findAll());
        model.addAttribute("listKhachHang", khachHangRepository2.findAll());

        return "hoa_don/edit";
    }
    @PostMapping("update/{id}")
    public String update(HoaDonRequest hoaDonRequest) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm a", Locale.ENGLISH); // Adjust the format
        HoaDon hoaDon = hoaDonRepository2.findById(hoaDonRequest.getId()).get();
        NhanVien nv = nhanVienRepository2.findById(hoaDonRequest.getIdNhanVien()).get();
        KhachHang khachHang = khachHangRepository2.findById(hoaDonRequest.getIdKhachHang()).get();
        try {
            Date ngayMuaHang = formatter.parse(String.valueOf(hoaDonRequest.getNgayMuaHang()));
            hoaDon.setNgayMuaHang(ngayMuaHang);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hoaDon.setId(hoaDon.getId());
        hoaDon.setNhanVien(nv);
        hoaDon.setKhachHang(khachHang);
        hoaDon.setTrangThai(hoaDonRequest.getTrangThai());
        this.hoaDonRepository2.save(hoaDon);
        return "redirect:/hoa-don/index";
    }

}
