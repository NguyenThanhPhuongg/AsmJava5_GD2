package org.example.demobuoi1.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.demobuoi1.entity.KichThuoc;
import org.example.demobuoi1.entity.MauSac;
import org.example.demobuoi1.repositories.asm2.KichThuocRepository2;
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
@Slf4j
@RequestMapping("kich-thuoc")
public class KichThuocController {
    @Autowired
    private KichThuocRepository2 ktRepo;

    @GetMapping("index")
    public String listKichThuoc(@RequestParam(name = "limit", defaultValue = "10") int pageSize,
                             @RequestParam(name = "page", defaultValue = "1") int pageNum,
                             @RequestParam(name = "valueSearch", required = false) String valueSearch,
                             @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                             Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KichThuoc> page;
        if (valueSearch != null && searchStatus != null) {
            page = ktRepo.search(valueSearch, searchStatus, pageable);
        } else if (valueSearch != null) {
            page = ktRepo.search(valueSearch, null, pageable);
        } else if (searchStatus != null) {
            page = ktRepo.search("", searchStatus, pageable);
        } else {
            page = ktRepo.findAll(pageable);
        }
        model.addAttribute("listKichThuoc", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "kich_thuoc/index";
    }

    @GetMapping("tim-kiem")
    public String search(@RequestParam(name = "valueSearch", required = false) String valueSearch,
                         @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                         @RequestParam(name = "limit", defaultValue = "10") int pageSize,
                         @RequestParam(name = "page", defaultValue = "1") int pageNum,
                         Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<KichThuoc> page = ktRepo.search(valueSearch, searchStatus, pageable);
        model.addAttribute("listKichThuoc", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "kich_thuoc/index";
    }
    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
    @GetMapping("create")
    public String create(@ModelAttribute("kichThuoc") KichThuoc kichThuoc){
        return "kich_thuoc/create";
    }
    @PostMapping("store")
    public String store(Model model, @Valid KichThuoc kichThuoc, BindingResult validate){
        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "kich_thuoc/create";
        }
        this.ktRepo.save(kichThuoc);
        return "redirect:/kich-thuoc/index";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        this.ktRepo.deleteById(id);
        return "redirect:/kich-thuoc/index";
    }
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model, @ModelAttribute("data") KichThuoc kichThuoc, HttpSession session) {
        KichThuoc kt = this.ktRepo.findById(id).get();
        session.setAttribute("id", kt.getId());
        model.addAttribute("data", kt);
        return "kich_thuoc/edit";
    }
    @PostMapping("update/{id}")
    public String update(Model model, @Valid KichThuoc kichThuoc, BindingResult validate,
                         HttpSession session){
        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "kich_thuoc/edit";
        }
        this.ktRepo.save(kichThuoc);
        session.removeAttribute("kichThuoc");
        return "redirect:/kich-thuoc/index";
    }
}
