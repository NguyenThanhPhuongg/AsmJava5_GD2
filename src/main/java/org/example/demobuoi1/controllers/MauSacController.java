package org.example.demobuoi1.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.demobuoi1.entity.MauSac;
import org.example.demobuoi1.entity.SanPham;
import org.example.demobuoi1.repositories.asm2.MauSacRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("mau-sac")
public class MauSacController {
    @Autowired
    private MauSacRepository2 msRepo;

    @GetMapping("index")
    public String listMauSac(@RequestParam(name = "limit", defaultValue = "10") int pageSize,
                                @RequestParam(name = "page", defaultValue = "1") int pageNum,
                                @RequestParam(name = "valueSearch", required = false) String valueSearch,
                                @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                                Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<MauSac> page;
        if (valueSearch != null && searchStatus != null) {
            page = msRepo.search(valueSearch, searchStatus, pageable);
        } else if (valueSearch != null) {
            page = msRepo.search(valueSearch, null, pageable);
        } else if (searchStatus != null) {
            page = msRepo.search("", searchStatus, pageable);
        } else {
            page = msRepo.findAll(pageable);
        }
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "mau_sac/index";
    }

    @GetMapping("tim-kiem")
    public String search(@RequestParam(name = "valueSearch", required = false) String valueSearch,
                         @RequestParam(name = "searchStatus", required = false) Integer searchStatus,
                         @RequestParam(name = "limit", defaultValue = "10") int pageSize,
                         @RequestParam(name = "page", defaultValue = "1") int pageNum,
                         Model model) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<MauSac> page = msRepo.search(valueSearch, searchStatus, pageable);
        model.addAttribute("listMauSac", page.getContent());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("valueSearch", valueSearch);
        model.addAttribute("searchStatus", searchStatus);
        return "mau_sac/index";
    }
    public  static Map<String , String> getErrorMessages(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

    @GetMapping("create")
    public String create(@ModelAttribute("mauSac") MauSac mauSac) {
        return "mau_sac/create";
    }

    @PostMapping("store")
    public String store(Model model, @Valid MauSac mauSac, BindingResult validate) {
        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "mau_sac/create";
        }
        this.msRepo.save(mauSac);
        return "redirect:/mau-sac/index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        this.msRepo.deleteById(id);
        return "redirect:/mau-sac/index";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model, @ModelAttribute("data") MauSac mauSac, HttpSession session) {
        MauSac ms = this.msRepo.findById(id).get();
        session.setAttribute("id", ms.getId());
        model.addAttribute("data", ms);
        return "mau_sac/edit";
    }

    @PostMapping("update/{id}")
    public String update(Model model, @Valid MauSac mauSac, BindingResult validate,
                         HttpSession session) {
        if(validate.hasErrors()) {
            model.addAttribute("errors", getErrorMessages(validate));
            return "mau_sac/create";
        }
        msRepo.save(mauSac);
        session.removeAttribute("mauSac");
        return "redirect:/mau-sac/index";
    }

}

