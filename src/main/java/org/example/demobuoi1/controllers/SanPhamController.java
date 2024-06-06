package org.example.demobuoi1.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.demobuoi1.entity.KhachHang;
import org.example.demobuoi1.entity.SanPham;
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
import java.util.Map;

@Controller
@RequestMapping("san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamRepository2 spRepo;

    @GetMapping("index")
    public String listSanPham(@RequestParam(name = "limit", defaultValue = "10") int pageSize,
                                @RequestParam(name = "page", defaultValue = "1") int pageNum,
                                @RequestParam(name = "valueSearch", required = false) String valueSearch,
                                @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                                Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page;
        if (valueSearch != null && searchStatus != null) {
            page = spRepo.search(valueSearch, searchStatus, pageable);
        } else if (valueSearch != null) {
            page = spRepo.search(valueSearch, null, pageable);
        } else if (searchStatus != null) {
            page = spRepo.search("", searchStatus, pageable);
        } else {
            page = spRepo.findAll(pageable);
        }
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "san_pham/index";
    }

    @GetMapping("tim-kiem")
    public String search(@RequestParam(name = "valueSearch", required = false) String valueSearch,
                         @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                         @RequestParam(name = "limit", defaultValue = "10") int pageSize,
                         @RequestParam(name = "page", defaultValue = "1") int pageNum,
                         Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = spRepo.search(valueSearch, searchStatus, pageable);
        model.addAttribute("listSanPham", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "san_pham/index";
    }

    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

    @RequestMapping("create")
    public String create(@ModelAttribute("sanPham") SanPham sanPham) {
        return "san_pham/create";
    }

    @PostMapping("/store")
    public String store(Model model, @Valid SanPham sanPham, BindingResult validate) {
        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "san_pham/create";
        }
        spRepo.save(sanPham);
        return "redirect:/san-pham/index";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        this.spRepo.deleteById(id);
        return "redirect:/san-pham/index";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model, @ModelAttribute("data") SanPham sanPham, HttpSession session) {
        SanPham sp = spRepo.findById(id).get();
        session.setAttribute("id", sp.getId());
        model.addAttribute("data", sp);
        return "san_pham/edit";
    }
    @PostMapping("update/{id}")
    public String update(Model model, @Valid SanPham sanPham, BindingResult validate,
                         HttpSession session) {

        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "san_pham/create";
        }
        spRepo.save(sanPham);
        session.removeAttribute("sanPham");
        return "redirect:/san-pham/index";
    }

}
