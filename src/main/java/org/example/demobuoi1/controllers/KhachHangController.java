package org.example.demobuoi1.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.demobuoi1.entity.KhachHang;
import org.example.demobuoi1.entity.KichThuoc;
import org.example.demobuoi1.entity.SanPham;
import org.example.demobuoi1.repositories.asm2.KhachHangRepository2;
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
@RequestMapping("khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangRepository2 khRepo;

    @GetMapping("index")
    public String listKhachHang(@RequestParam(name = "limit", defaultValue = "10") int pageSize,
                                @RequestParam(name = "page", defaultValue = "1") int pageNum,
                                @RequestParam(name = "valueSearch", required = false) String valueSearch,
                                @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                                Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page;
        if (valueSearch != null && searchStatus != null) {
            page = khRepo.search(valueSearch, searchStatus, pageable);
        } else if (valueSearch != null) {
            page = khRepo.search(valueSearch, null, pageable);
        } else if (searchStatus != null) {
            page = khRepo.search("", searchStatus, pageable);
        } else {
            page = khRepo.findAll(pageable);
        }
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "khach_hang/index";
    }

    @GetMapping("tim-kiem")
    public String search(@RequestParam(name = "valueSearch", required = false) String valueSearch,
                         @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                         @RequestParam(name = "limit", defaultValue = "10") int pageSize,
                         @RequestParam(name = "page", defaultValue = "1") int pageNum,
                         Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KhachHang> page = khRepo.search(valueSearch, searchStatus, pageable);
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "khach_hang/index";
    }
    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

    @GetMapping("create")
    public String create(@ModelAttribute("khachHang") KhachHang khachHang){
        return "khach_hang/create";
    }
    @PostMapping("store")
    public String store(Model model, @Valid KhachHang khachHang, BindingResult validate){
        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "khach_hang/create";
        }
        khRepo.save(khachHang);
        return "redirect:/khach-hang/index";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        khRepo.deleteById(id);
        return "redirect:/khach-hang/index";
    }
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model, @ModelAttribute("data") KhachHang khachHang, HttpSession session){
        KhachHang kh = khRepo.findById(id).get();
        session.setAttribute("id", kh.getId());
        model.addAttribute("data", kh);
        return "khach_hang/edit";
    }
    @PostMapping("update/{id}")
    public String update(Model model, @Valid KhachHang khachHang, BindingResult validate,
                         HttpSession session){
        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "khach_hang/edit";
        }
        khRepo.save(khachHang);
        session.removeAttribute("khachHang");
        return "redirect:/khach-hang/index";
    }



}
