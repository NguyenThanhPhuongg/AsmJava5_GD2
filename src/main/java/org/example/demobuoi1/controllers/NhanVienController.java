package org.example.demobuoi1.controllers;

import jakarta.validation.Valid;
import org.example.demobuoi1.entity.MauSac;
import org.example.demobuoi1.entity.NhanVien;
import org.example.demobuoi1.entity.SanPham;
import org.example.demobuoi1.repositories.asm2.NhanVienRepository2;
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
@RequestMapping("nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienRepository2 nvRepo;

    @GetMapping("index")
    public String listNhanVien(@RequestParam(name = "limit", defaultValue = "10") int pageSize,
                              @RequestParam(name = "page", defaultValue = "1") int pageNum,
                              @RequestParam(name = "valueSearch", required = false) String valueSearch,
                              @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                              Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page;
        if (valueSearch != null && searchStatus != null) {
            page = nvRepo.search(valueSearch, searchStatus, pageable);
        } else if (valueSearch != null) {
            page = nvRepo.search(valueSearch, null, pageable);
        } else if (searchStatus != null) {
            page = nvRepo.search("", searchStatus, pageable);
        } else {
            page = nvRepo.findAll(pageable);
        }
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "nhan_vien/index";
    }

    @GetMapping("tim-kiem")
    public String search(@RequestParam(name = "valueSearch", required = false) String valueSearch,
                         @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                         @RequestParam(name = "limit", defaultValue = "10") int pageSize,
                         @RequestParam(name = "page", defaultValue = "1") int pageNum,
                         Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<NhanVien> page = nvRepo.search(valueSearch, searchStatus, pageable);
        model.addAttribute("listNhanVien", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "nhan_vien/index";
    }

    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
    @GetMapping("create")
    public String create(@ModelAttribute("nhanVien") NhanVien nhanVien){
        return "nhan_vien/create";
    }
    @PostMapping("store")
    public String store(Model model, @Valid NhanVien nhanVien, BindingResult validate){
        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "nhan_vien/create";
        }
        nvRepo.save(nhanVien);
        return "redirect:/nhan-vien/index";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        nvRepo.deleteById(id);
        return "redirect:/nhan-vien/index";
    }
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model) {
        NhanVien nhanVien = nvRepo.findById(id).get();
        model.addAttribute("data", nhanVien);
        return "nhan_vien/edit";
    }
    @PostMapping("update/{id}")
    public String update(NhanVien nhanVien){
        nvRepo.save(nhanVien);
        return "redirect:/nhan-vien/index";
    }
}
