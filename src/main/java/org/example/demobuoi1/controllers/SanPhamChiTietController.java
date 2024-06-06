package org.example.demobuoi1.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.demobuoi1.contants.Status;
import org.example.demobuoi1.entity.*;
import org.example.demobuoi1.repositories.asm2.KichThuocRepository2;
import org.example.demobuoi1.repositories.asm2.MauSacRepository2;
import org.example.demobuoi1.repositories.asm2.SanPhamChiTietRepository2;
import org.example.demobuoi1.repositories.asm2.SanPhamRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("san-pham-chi-tiet")
@RequiredArgsConstructor
public class SanPhamChiTietController {
    private final SanPhamChiTietRepository2 sanPhamChiTietRepository;
    private final SanPhamRepository2 sanPhamRepository;
    private final MauSacRepository2 mauSacRepository;
    private final KichThuocRepository2 kichThuocRepository;

    @GetMapping("index")
    public String listSanPhamChiTiet(@RequestParam(name = "limit", defaultValue = "10") int pageSize,
                                @RequestParam(name = "page", defaultValue = "1") int pageNum,
                                @RequestParam(name = "valueSearch", required = false) String valueSearch,
                                @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                                Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPhamChiTiet> page;
        if (valueSearch != null && searchStatus != null) {
            page = sanPhamChiTietRepository.search(valueSearch, searchStatus, pageable);
        } else if (valueSearch != null) {
            page = sanPhamChiTietRepository.search(valueSearch, null, pageable);
        } else if (searchStatus != null) {
            page = sanPhamChiTietRepository.search("", searchStatus, pageable);
        } else {
            page = sanPhamChiTietRepository.findAll(pageable);
        }
        model.addAttribute("listSanPhamChiTiet", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "san_pham_chi_tiet/index";
    }

    @GetMapping("tim-kiem")
    public String search(@RequestParam(name = "valueSearch", required = false) String valueSearch,
                         @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                         @RequestParam(name = "limit", defaultValue = "10") int pageSize,
                         @RequestParam(name = "page", defaultValue = "1") int pageNum,
                         Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPhamChiTiet> page = sanPhamChiTietRepository.search(valueSearch, searchStatus, pageable);
        model.addAttribute("listSanPhamChiTiet", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "san_pham_chi_tiet/index";
    }
    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

    @GetMapping("create")
    public String create(Model model, @ModelAttribute("spct") SanPhamChiTiet spct) {
        List<SanPham> listSP = sanPhamRepository.findAllByTrangThai(Status.ACTIVE);
        model.addAttribute("listSanPham" , listSP);
        List<MauSac> listMS = mauSacRepository.findAllByTrangThai(Status.ACTIVE);
        model.addAttribute("listMauSac", listMS);
        List<KichThuoc> listKT = kichThuocRepository.findAllByTrangThai(Status.ACTIVE);
        model.addAttribute("listKichThuoc", listKT);
        return "san_pham_chi_tiet/create";
    }
    @PostMapping("store")
    public String store(Model model, @Valid SanPhamChiTiet sanPhamChiTiet, BindingResult validate){
        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "san_pham_chi_tiet/create";
        }
        List<SanPham> listSP = sanPhamRepository.findAllByTrangThai(Status.ACTIVE);
        model.addAttribute("listSanPham" , listSP);
        List<MauSac> listMS = mauSacRepository.findAllByTrangThai(Status.ACTIVE);
        model.addAttribute("listMauSac", listMS);
        List<KichThuoc> listKT = kichThuocRepository.findAllByTrangThai(Status.ACTIVE);
        model.addAttribute("listKichThuoc", listKT);

        this.sanPhamChiTietRepository.save(sanPhamChiTiet);
        return "redirect:/san-pham-chi-tiet/index";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        this.sanPhamChiTietRepository.deleteById(id);
        return "redirect:/san-pham-chi-tiet/index";
    }
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        SanPhamChiTiet spct = this.sanPhamChiTietRepository.findById(id).get();
        model.addAttribute("data", spct);
        List<SanPham> listSP = sanPhamRepository.findAllByTrangThai(Status.ACTIVE);
        model.addAttribute("listSanPham" , listSP);
        List<MauSac> listMS = mauSacRepository.findAllByTrangThai(Status.ACTIVE);
        model.addAttribute("listMauSac", listMS);
        List<KichThuoc> listKT = kichThuocRepository.findAllByTrangThai(Status.ACTIVE);
        model.addAttribute("listKichThuoc", listKT);
        return "san_pham_chi_tiet/edit";
    }
    @PostMapping("update/{id}")
    public String update( SanPhamChiTiet sanPhamChiTiet){
        this.sanPhamChiTietRepository.save(sanPhamChiTiet);
        return "redirect:/san-pham-chi-tiet/index";
    }

}
