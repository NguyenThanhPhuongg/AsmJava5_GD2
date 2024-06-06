package org.example.demobuoi1.controllers;

import lombok.RequiredArgsConstructor;
import org.example.demobuoi1.entity.HoaDonChiTiet;
import org.example.demobuoi1.repositories.asm2.HoaDonChiTietRepository2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hoa-don-chi-tiet")
public class HoaDonChiTietController {
    private final HoaDonChiTietRepository2 hdRepo;
    @RequestMapping("/index")
    public String index(Model model){
        List<HoaDonChiTiet> list = hdRepo.findAll();
        model.addAttribute("listHoaDonChiTiet", list);
        return "hoa_don_chi_tiet/index";
    }
}
